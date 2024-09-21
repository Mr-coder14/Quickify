package Classes;

public class User {
    private String name;
    private String email;
    private String userid;
    private String pass,phno;

    public User(String name, String email, String pass, String phno,String userid) {
        this.name = name;
        this.email = email;
        this.userid=userid;
        this.pass = pass;
        this.phno = phno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }
}
