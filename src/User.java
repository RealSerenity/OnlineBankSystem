import java.util.Date;

public class User {

    Long userTC;
    String password;
    Date dob;
    Long money;


    public User(Long userTC, String password, Long money) {
        this.userTC = userTC;
        this.password = password;
        this.dob = new Date();
        this.money = money;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
