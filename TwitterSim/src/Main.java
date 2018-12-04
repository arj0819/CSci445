import java.util.List;
import java.util.ArrayList;

public class Main {
 
    private static List<Account> allAccounts = new ArrayList<Account>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            if (i%2==0) {
                allAccounts.add(new Account(false));
            } else {
                allAccounts.add(new Account(true));
            }
        }

        for (int i = 0; i < allAccounts.size(); i++) {
            System.out.println("Is Account " + allAccounts.get(i).getID() + " a bot? " + allAccounts.get(i).isBot());
        }
    }

}