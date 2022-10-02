import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws ParseException {
        List<User> users = initializeUsers();
        Scanner input = new Scanner(System.in);
        Long value;
        User user = null;

    //    System.out.println("Choose an operator: \n" +
    //    "");
        boolean systemActive = true;
        while(systemActive){
            if(user == null){
                System.out.println("1: Giriş yap");
                System.out.println("2: Kayıt ol");
                char operator = input.next().charAt(0);
                switch (operator){
                    case '1':
                        System.out.print("TC: "   );
                        Long tc = (Long) input.nextLong();
                        user = getUser(users,tc);
                        if(user == null){
                            System.out.println("User doesn't exist !!!");
                            continue;
                        }
                        System.out.println();
                        System.out.print("Password : "   );
                        String password = input.next();
                        if (user.password.equals(password)){
                            System.out.println("Giriş yapıldı");
                        }else {
                            System.out.println("Wrong password!");
                            continue;
                        }
                        break;
                    case '2':
                        System.out.print("TC: "   );
                        tc = input.nextLong();
                        user = getUser(users,tc);
                        if(user != null){
                            System.out.println("User exists");
                            continue;
                        }
                        System.out.println();
                        System.out.print("Dob (format:yyyy-MM-dd): "   );
                        String dateString = input.next();
                        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        Date dob = formatter.parse(dateString);
                        System.out.print("Password : "   );
                        password = input.next();
                        if(password.contains(dob.toString())){
                            System.out.println("Password can not contain dob");
                        }
                        User userkayıt = new User(tc,password,0L);
                        userkayıt.setDob(dob);
                        users.add(userkayıt);
                        System.out.println("Kayıt yapıldı");
                        break;
                }

            }
                // other operations
            System.out.println("Operations : \n"+
                    "  0 : Hesap bilgileri \n"+
                    "  1 : Para yatır \n"+
                    "  2 : Para transfer \n" +
                    "  3 : Kredi veya kredi kartı ekstresi ödeme \n" +
                    "  4 : Hesap Çıkış \n" +
                    "  q : Programı kapat \n");
            char operation = input.next().charAt(0);
            switch (operation){
                case '0':
                    System.out.println("Hesaptaki para miktarı: " + user.money);
                    break;
                case '1':
                    System.out.print("Yatırılacak miktar: "   );
                    value = input.nextLong();
                    user.money += value;
                    break;

                case '2':
                    System.out.print("Aktarılacak tc: "   );
                    Long hedefTc = input.nextLong();
                    User hedefUser = getUser(users, hedefTc);
                    if(hedefUser == null){
                        System.out.println("User doesn't exist !!!");
                        continue;
                    }
                    System.out.print("Aktarılacak miktar: "   );
                    value = input.nextLong();
                    user.money -= value;
                    hedefUser.money += value;
                    break;
                case '3':
                    System.out.print("Kredi ödeme miktarı: "   );
                    value = input.nextLong();
                    user.money -= value;
                    break;

                case '4':
                    user = null;
                    break;

                case 'q':
                    systemActive = false;
                    break;
            }
        }

    }

    private static List<User> initializeUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User(12345678911L, "password1",0L));
        users.add(new User(12345678912L, "password2",0L));
        users.add(new User(12345678913L, "password3",0L));
        users.add(new User(12345678914L, "password4",0L));
        return users;
    }

    private static User getUser(List<User> users , Long TC){
        for(User user : users){
            if (TC.equals(user.userTC)){
                return user;
            }
        }
        return null;
    }
}
