package cn.hello.ctr;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author ziyu.wei
 * <p>
 * 2024/4/26 7:04
 */
public class T {
    public static void main(String[] args) {
//        String a = "1234";
//        char[] chars = a.toCharArray();
//        for (int i=0; i<chars.length; i++) {
//            System.out.println(i + "\t" + (chars[i] == '1'));
//        }
        test1();
    }

    private static void test() {
        String s= "`tenant_id` varchar(32) DEFAULT NULL COMMENT '租户id',";
        String comment = "COMMENT ";
        int startIndex = s.indexOf(comment);
        int endIndex = s.lastIndexOf(",");
        System.out.println(s.substring(startIndex + comment.length(), endIndex));
    }

    private static void test1() {
        Map<String, String> tableNamePKMap = new HashMap<>();
        tableNamePKMap.put("sys_area", "id");

        String s= "INSERT INTO scc_platform_sys.sys_area (id, name, parent_id, value) VALUES('510186', '高新区', '5101', NULL);";

        s = s.trim();

        if (s.contains(" select ") || s.contains(" SELECT ")) {
            return;
        }

        int fristKHIndex = s.indexOf("(");
        String databaseTableName = s.substring("insert into ".length(), fristKHIndex);

        if (tableNamePKMap.containsKey(databaseTableName)) {

        }

        String tmpStr = s.replaceAll(" ", "");
        int startIndex = tmpStr.indexOf("VALUES(");
        if (startIndex <= 1) {
            startIndex = tmpStr.indexOf("values(");
        }

        if (startIndex > 1) {
            startIndex = startIndex + "values(".length();
        }

        String subString = tmpStr.substring(startIndex, tmpStr.length());
        String[] array = subString.split(",");
        System.out.println(array[0]);

        String sql = "delete from "+databaseTableName+" where id =" + array[0];
        System.out.println(sql);


    }

}
