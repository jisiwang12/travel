package ServletTest;

public class myTest {
    private String name;
    private String passwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "myTest{" +
                "name='" + name + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
