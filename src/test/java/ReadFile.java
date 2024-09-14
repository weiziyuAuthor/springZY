import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author ziyu.wei
 * <p>
 * 2023/10/25 14:50
 */
public class ReadFile {
    public static void main(String[] args) {
        read();
    }

    public static void read() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "D:\\tmp\\emun.txt"));
            String line = reader.readLine();
            int count = 0;
            while (line != null) {
                count++;
//                System.out.println(line);
                if (isTarget(line)) {
                    System.out.println(line);
                }
                line = reader.readLine();
            }
            reader.close();
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isTarget(String s) {
        int count = 0;
        if (s != null && !"".equals(s)) {
            if (s.contains("删除标记") || s.contains("是否删除")) {
                return false;
            }
            if (s.contains("/")) {
                return true;
            }
            if (s.contains("0") ) {
                count++;
            }
            if (s.contains("1")) {
                count++;
            }
            if (s.contains("2")) {
                count++;
            }

            if (count > 1) {
                return  true;
            }
        }
        return false;
    }
}
