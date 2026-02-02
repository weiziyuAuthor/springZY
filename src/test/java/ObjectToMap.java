import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author ziyu.wei
 * <p>
 * 2024/9/24 16:19
 */
public class ObjectToMap {

    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }

    public static void main(String[] args) {
        try {
            Person person = new Person();
            person.setAge(1);
            person.setName("z");
            person.setInitData(true);


            objectToMap(person).forEach((a, b) -> {
                System.out.println(b instanceof Boolean);
                System.out.println(a + "\t" + b);
            });
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
