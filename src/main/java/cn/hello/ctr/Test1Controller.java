package cn.hello.ctr;



import cn.hello.common.Utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author ziyu.wei
 * <p>
 * 2023/10/24 18:21
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class Test1Controller {

    private static final String CHARSET_NAME = "UTF-8";
    private static final String PRE_INSERT = "INSERT INTO";

    private static final String PRE_ALTER = "ALTER TABLE";
    private static final String PRE_UPDATE = "UPDATE ";
    private static final String PRE_DEL = "DELETE FROM ";
    private static final String PRE_CREATE_INDEX = "CREATE INDEX ";

    private static final String HAS_MODIFY = "MODIFY COLUMN";
    private static final String HAS_ADD_COLUMN = "ADD COLUMN";
    private static final String HAS_ADD_CONTRAINT = "ADD CONSTRAINT";

    private static final String url01 = "jdbc:mysql://127.0.0.1:3306/";
    private static final String url02 = "?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8";

    private static final String username = "root";
    private static final String passwd = "123456";

    @GetMapping
    public String test()
    {
        return "11111";
    }

    @GetMapping("/syncdb/{database}/{oper}/{input}/{output}/{fromHome}/{delThenInsertOrUpdate}")
    public void syncdb(@PathVariable String database,
                       @PathVariable String oper,
                       @PathVariable String input,
                       @PathVariable String output,
                       @PathVariable String fromHome,
                        @PathVariable String delThenInsertOrUpdate){
//        String database = map.get("database");
//        String inputFilePath = map.get("inputPath");
//        String outputFilePath = map.get("outPath");


        Map<String, String> tableNamePKMap = new HashMap<>();
        tableNamePKMap.put("product", "id");
//        String database = "orders_01";
//        1111, 增删改查
        char[] operChars = oper.toCharArray();
        char OPER_C = operChars[0];
        char OPER_D = operChars[1];
        char OPER_U = operChars[2];
        char OPER_Q = operChars[3];
//        String inputFilePath = "D:\\one.sql";
//        String outputFilePath = "D:\\one1.sql";

        String inputFilePath = "D:\\" + input;
        String outputFilePath = "D:\\" + output;

        createOutputFile(outputFilePath);

        Connection connection = Utils.getConn(url01 + database + url02, username, passwd);
        System.out.println("_syncdb connection: "+ connection);
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<String> fileContents = new ArrayList<>(32);
        int execCount = 0;
        PreparedStatement preparedStatement = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilePath), CHARSET_NAME))) {
            String line;
            String originalLine = "";
            int loopCount = 0;
            while ((line = br.readLine()) != null) {
                loopCount++;
                System.out.println("_syncdb loopCount: " + loopCount);

                if (line != null && !line.equals("")) {
                    originalLine = line;
                    line = line.toUpperCase().trim();

//                    如果是巡检， 不执行del
                    if (line.startsWith(PRE_DEL) && (OPER_D == '1') && (delThenInsertOrUpdate.equals("0"))) {
                        try {
                            preparedStatement = connection.prepareStatement(originalLine);
                            preparedStatement.execute();
                            execCount++;
                        } catch (Exception e) {
                            e.printStackTrace();
                            continue;
                        }
                    }

                    if (line.startsWith(PRE_CREATE_INDEX) && (OPER_C == '1')) {
                        try {
                            preparedStatement = connection.prepareStatement(originalLine);
                            preparedStatement.execute();
                            execCount++;
                        } catch (Exception e) {
                            e.printStackTrace();
                            continue;
                        }
                    }

                    if (line.startsWith(PRE_UPDATE) && (OPER_U == '1')) {
                        try {
                            preparedStatement = connection.prepareStatement(originalLine);
                            preparedStatement.execute();
                            execCount++;
                        } catch (Exception e) {
                            e.printStackTrace();
                            continue;
                        }
                    }
//                    先处理插入，更改数据长度，add column,
                    if (line.startsWith(PRE_INSERT) && (OPER_C == '1')) {
//                        巡检， 删除后再插入
                        if ("1".equals(delThenInsertOrUpdate)) {
                            String s= originalLine;
                            s = s.trim();
                            if (s.contains(" select ") || s.contains(" SELECT ")) {
                                continue;
                            }

                            int fristKHIndex = s.indexOf("(");
                            String databaseTableName = s.substring("insert into ".length(), fristKHIndex);

                            String pk = getTablePK(tableNamePKMap, databaseTableName);
                            if ("".equals(pk)) {
                                continue;
                            }

                            String tmpStr = s.replaceAll(" ", "");
                            int startIndex = tmpStr.indexOf("VALUES(");
                            if (startIndex <= 1) {
                                startIndex = tmpStr.indexOf("values(");
                            }

                            if (startIndex > 1) {
                                startIndex = startIndex + "values(".length();
                            } else {
                                continue;
                            }

                            String subString = tmpStr.substring(startIndex, tmpStr.length());
                            String[] array = subString.split(",");
                            System.out.println(array[0]);

                            String delSql = "delete from "+databaseTableName+" where " + pk + " =" + array[0];
                            System.out.println("_syncdb delSql: " + delSql);

                            try {
                                preparedStatement = connection.prepareStatement(delSql);
                                preparedStatement.execute();

//                                connection.prepareStatement(originalLine);
                                preparedStatement.execute(originalLine);

                                execCount++;
                            } catch (Exception e) {
                                e.printStackTrace();
                                continue;
                            }

                        } else {
                            try {
                                preparedStatement = connection.prepareStatement(originalLine);
                                preparedStatement.execute();
                                execCount++;
                            } catch (Exception e) {
                                e.printStackTrace();
                                continue;
                            }
                        }
                    }

                    if (line.startsWith(PRE_ALTER)) {
//                        如果是家提供的sql，不做operType的限制，直接执行
                        if ("1".equals(fromHome)) {
                            try {
                                preparedStatement = connection.prepareStatement(originalLine);
                                preparedStatement.execute();
                                execCount++;
                            } catch (Exception e) {
                                e.printStackTrace();
                                continue;
                            }
                        } else {
//                            通过navicat生成的比对文件，暂时只处理数据长度
                            //                        更新列
//                        ALTER TABLE `product` MODIFY COLUMN `PRODUCT_DESC`  varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品描述' AFTER `PRODUCT_NAME`;
                            if (line.contains(HAS_MODIFY) && (OPER_U == '1')) {
                                String sql = packSQL(line, originalLine, connection);
                                System.out.println("modify sql: " + sql);

                                fileContents.add(sql);

                            }

//                        增加列
//                        ALTER TABLE `product` ADD COLUMN `col1`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `UPDATE_TIME`;
                            if (line.contains(HAS_ADD_COLUMN) && (OPER_U == '1')) {
                                fileContents.add(originalLine);
                            }

//                        增加主键
                            if (line.contains(HAS_ADD_CONTRAINT) && (OPER_U == '1')) {
                                fileContents.add(originalLine);
                            }
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        writeFile(outputFilePath, fileContents);

        System.out.println("_syncdb execCount: " + execCount);
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Utils.closeConn(connection);
    }

    private String getTablePK(Map<String, String> tableNamePKMap, String databaseTableName) {
        for (Map.Entry<String, String> entry : tableNamePKMap.entrySet()) {
            if (databaseTableName.toUpperCase().contains(entry.getKey().toUpperCase())) {
                return entry.getValue();
            }
        }
        return "";
    }

    private void writeFile(String path, List<String> content) {
        int count = 0;
        try (FileWriter fileWriter = new FileWriter(path, true)) {
            for (String s: content) {
                fileWriter.write(s);
                fileWriter.write(System.lineSeparator());
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("_syncdb write file "+ path +" count: " + count);
    }


    private String packSQL(String s, String originalLine, Connection connection) {
//        String s = "ALTER TABLE `product` MODIFY COLUMN `PRODUCT_DESC`  varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品描述' AFTER `PRODUCT_NAME`;";
        String[] arr = originalLine.split(" ");
        String result = "";
        String tableName = arr[2];

        String targetColumn = arr[5];
        if (targetColumn != null && !"".equals(targetColumn)) {
            targetColumn = targetColumn.replaceAll("`", "");
        }
        for (int i=0; i<=6; i++) {
            result += arr[i] + " ";
        }

        String comment = getCommentByShowTable( connection, tableName, targetColumn);
        if (comment != null && !"".equals(comment)) {
            result += " COMMENT " +  comment ;
        }

        return result += ";";
    }

    private String getCommentByRemark(Connection connection, String tableName, String targetColumn) {
        ResultSet rs = null;
        try {
            DatabaseMetaData metaData = connection.getMetaData();

            rs = metaData.getColumns(null, null, tableName, null);
            String comment = "";
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                if (columnName != null && columnName.equalsIgnoreCase(targetColumn)) {
                    String columnComment = rs.getString("REMARKS");
                    System.out.println("Column Name: " + columnName);
                    System.out.println("Column Comment: " + columnComment);
                    comment = columnComment;
                    System.out.println();
                    break;
                }
            }
            if (comment != null && !"".equals(comment)) {
//                result += " COMMENT " + "'" + comment + "'";
                return comment;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return "";
    }

    private static Map<String, String> tableInfoMap = new HashMap<>();
    private String getShowTable(Connection connection, String tableNameParam) {

        if (tableInfoMap.get(tableNameParam) != null) {
            return tableInfoMap.get(tableNameParam);
        }

        PreparedStatement preparedStatement2 = null;
        ResultSet resultSet2 = null;
        try{
            preparedStatement2 = connection.prepareStatement("show create table " + tableNameParam);
            resultSet2 = preparedStatement2.executeQuery();
            while(resultSet2.next()) {
                String tableName = resultSet2.getString("Table");
                String createTable = resultSet2.getString("Create Table");

                tableInfoMap.put(tableNameParam, createTable);
//                log.info("tableName:{}", tableName);
//                log.info("createTable:");
//                System.out.println(createTable);
//                String[] createTables = createTable.split(",");
//                for (int i=0; i<createTables.length; i++) {
//                    String segment = createTables[i].trim();
//                    if (segment.contains(targetColumn)) {
//                        if (segment.contains(" COMMENT ")) {
//                            return extractComment(segment);
//                        }
//                    }
//                }
            }
        } catch (Exception e) {
            log.error("method2 error ", e);
        } finally {
            if (resultSet2 != null) {
                try {
                    resultSet2.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (preparedStatement2 != null) {
                try {
                    preparedStatement2.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return "";
    }

    private String getCommentByShowTable(Connection connection, String tableNameParam, String targetColumn) {
        String createTable = getShowTable(connection, tableNameParam);
        String[] createTables = createTable.split(",");
        for (int i=0; i<createTables.length; i++) {
            String segment = createTables[i].trim();
            if (segment.contains(targetColumn)) {
                if (segment.contains(" COMMENT ")) {
                    return extractComment(segment);
                }
            }
        }
        return "";
    }

    private String extractComment(String s) {
//        String s= "`tenant_id` varchar(32) DEFAULT NULL COMMENT '租户id',";
        String comment = "COMMENT ";
        int startIndex = s.indexOf(comment);
        int endIndex = s.length();
        return s.substring(startIndex + comment.length(), endIndex);
    }

    private static void createOutputFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
