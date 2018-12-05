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
                                    NormalRNG(0.25,0.5), //avgTweetQuality
                                    NormalRNG(0.25,0.5), //tweetProbability
                                    exponRNG(2.0),       //popularityMultiplier
                                    200,                 //baseHumanFollowerCount
                                    0                    //baseBotFollowerCount
                            ));
        }
        //Print all initial account stats
        // System.out.println("INITIAL STATISTICS\n\n"+
        //                    "Total Accounts --> " + Account.totalAccounts +"\n"+
        //                    "Total Tweets ----> " + Tweet.totalTweets + "\n");

        // for (int i = 0; i < allAccounts.size(); i++) {
        //     System.out.println(allAccounts.get(i));
        // }

        while (Tweet.totalTweets <= 200) {
            for (int i = 0; i < allAccounts.size(); i++) {
                double tweetProbThreshold = rand.nextDouble();
                Account currentAccount = allAccounts.get(i);
                double currentTweetProbability = currentAccount.getTweetProbability();
                double currentAvgTweetQuality = exponRNG(currentAccount.getAvgTweetQuality());
                int currentHumanFollowers = currentAccount.getHumanFollowerCount();
                double currentProbabilityMult = currentAccount.getPopularityMult();
    
                if (currentTweetProbability >= tweetProbThreshold) {
                    currentAccount.generateTweet(currentAvgTweetQuality, currentAccount.getID());
                    Tweet currentTweet = currentAccount.getMostRecentTweet();

                    int totalTweetImpressions = genImpressions(currentHumanFollowers, currentProbabilityMult, currentTweet.getQuality());
                    currentTweet.setImpressions(totalTweetImpressions);
                    currentAccount.incrementTotalImpressions(totalTweetImpressions);

                }
            }
        }
        calculateInfluenceScores();

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

    public static int binomTest(int numTests, double prob){
        int x = 0;//the number of successes
        for (int i = 0; i < numTests; i++){
            if(Math.random() < prob)
            x++;
        }
        return x;
    }

    public static int genImpressions(int followers, double popularityMultiplier, double tweetQuality){
        int impressions = 0;
        double mean = followers * ((1 + popularityMultiplier) * (1 + tweetQuality));
        impressions = (int)exponRNG(mean);
        return impressions;
    }

    public static int genRetweets(int impressions, double rate){
        int retweets = 0;
        retweets = binomTest(impressions, rate);
        return retweets;
    }

    public static int genNewFollowers(int impressions, double rate){
        int followers = 0;
        followers = binomTest(impressions, rate);
        return followers;
    }

    // public static double updateInfluence(int newFollows,int currentInfluence){
    //     for(int i = 0; i < newFollows; i++){
    //         double inc = 0;
    //         inc = exponRNG(.01);
    //     }
    //     return inc;
    // }



    //influence score is just each account's percentage of the total impressions.
    //This calculation needs more thought and more needs to be considered to 
    //determine the influence score
    public static void calculateInfluenceScores() {
        long overallTotalImpressions = 0;
        for (int i = 0; i < allAccounts.size(); i++) {
            Account currentAccount = allAccounts.get(i); 
            long accountTotalImpressions = currentAccount.getTotalImpressions();
            overallTotalImpressions += accountTotalImpressions;
        }
        for (int i = 0; i < allAccounts.size(); i++) {
            Account currentAccount = allAccounts.get(i); 
            long accountTotalImpressions = currentAccount.getTotalImpressions();
            double influenceScore = ((double)accountTotalImpressions/overallTotalImpressions)*100;
            currentAccount.setInfluenceScore(influenceScore);
        }
    }
}