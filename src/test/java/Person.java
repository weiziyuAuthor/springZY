/**
 * @Author ziyu.wei
 * <p>
 * 2024/9/24 16:22
 */
public class Person {
    private int age;
    private String name;

    private byte gender;

    private Boolean isInitData;

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public Boolean getInitData() {
        return isInitData;
    }

    public void setInitData(Boolean initData) {
        isInitData = initData;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
