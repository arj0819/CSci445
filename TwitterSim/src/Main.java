import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Main {
 
    private static List<Account> allAccounts = new ArrayList<Account>();
    private static Random rand = new Random();

    public static void main(String[] args) {
        //Create initial accounts - # of initial Accounts should be an initial parameter
        for (int i = 0; i < 10; i++) {
            allAccounts.add(new Account(
                                    NormalRNG(0.25,0.5),
                                    NormalRNG(0.25,0.5),
                                    exponRNG(2.0)
                            ));
        }
        //Print all initial account stats
        System.out.println("INITIAL STATISTICS\n\n"+
                           "Total Accounts --> " + Account.totalAccounts +"\n"+
                           "Total Tweets ----> " + Tweet.totalTweets + "\n");

        for (int i = 0; i < allAccounts.size(); i++) {
            System.out.println(allAccounts.get(i));
        }

        while (Tweet.totalTweets <= 100) {
            for (int i = 0; i < allAccounts.size(); i++) {
                double tweetProbThreshold = rand.nextDouble();
                Account currentAccount = allAccounts.get(i);
                double currentTweetProbability = currentAccount.getTweetProbability();
                double currentAvgTweetQuality = exponRNG(currentAccount.getAvgTweetQuality());
    
                if (currentTweetProbability >= tweetProbThreshold) {
                    currentAccount.generateTweet(currentAvgTweetQuality, currentAccount.getID());
                }
            }
        }

        //Print all final account stats
        System.out.println("FINAL STATISTICS\n\n"+
                           "Total Accounts --> " + Account.totalAccounts +"\n"+
                           "Total Tweets ----> " + Tweet.totalTweets + "\n");

        for (int i = 0; i < allAccounts.size(); i++) {
            Account currentAccount = allAccounts.get(i);
            System.out.println(currentAccount);
        }
    }


    public static double NormalRNG(int stdDev, int mean) {
        double value = 0;
        value = (rand.nextGaussian()*stdDev)+mean;
        return value;
    }

    public static double NormalRNG(double stdDev, double mean) {
        double value = 0;
        value = (rand.nextGaussian()*stdDev)+mean;
        if (value > 1.0) {
            value = 1.0;
        } else if (value < 0.0) {
            value = 0.01;
        }
        return value;
    }

    public static double exponRNG(double mean) {
        double lambda = 1/mean;
        double value = 0;
        value = Math.log(1-rand.nextDouble())/(-lambda);
        return value;
    }

}