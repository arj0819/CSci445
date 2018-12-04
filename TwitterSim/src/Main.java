import java.util.List;
import java.util.ArrayList;

public class Main {
 
    private static List<Account> allAccounts = new ArrayList<Account>();

    public static void main(String[] args) {
        //Create initial accounts - # of initial Accounts should be an initial parameter
        for (int i = 0; i < 10; i++) {
            //Every other account is a Bot. We should be able to decide how many
            //Human and Bot accounts to start with.
            if (i%2==0) {
                //Create Human account
                allAccounts.add(new Account(false));
            } else {
                //Create Bot account
                allAccounts.add(new Account(true));
            }
        }

        //Print all account stats
        System.out.println("Total # of Accounts: " + Account.totalAccounts +"\n");
        for (int i = 0; i < allAccounts.size(); i++) {
            System.out.println("Is Account " + allAccounts.get(i).getID() + " a bot? " + allAccounts.get(i).isBot());
            System.out.println("   Account " + allAccounts.get(i).getID() + " Follower Count: "+allAccounts.get(i).getFollowerCount());
            System.out.println("   Account " + allAccounts.get(i).getID() + " Tweet Count: "+allAccounts.get(i).getTweetCount());
            System.out.println("   Account " + allAccounts.get(i).getID() + " Avg Tweet Quality: "+allAccounts.get(i).getAvgTweetQuality());
            System.out.println("   Account " + allAccounts.get(i).getID() + " Popularity Multiplier: "+allAccounts.get(i).getPopularityMult()+"\n");
        }

    }

}