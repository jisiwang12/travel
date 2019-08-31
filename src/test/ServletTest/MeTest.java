package ServletTest;

public class MeTest extends myTest {
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return super.toString()+"        MeTest{" +
                "sex='" + sex + '\'' +
                '}';
    }
}
