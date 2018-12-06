import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Main {
 
    private static List<Account> allAccounts = new ArrayList<Account>();
    private static Random rand = new Random();

    public static void main(String[] args) {
        //Create initial accounts - # of initial Accounts should be an initial parameter
	    double newFollowerProbability = .005;
	    double retweetProbability = .01;
        for (int i = 0; i < 10; i++) {
            allAccounts.add(new Account(
                                    0.1,                  //NormalRNG(0.25,0.5),  //avgTweetQuality
                                    0.05,                 //exponRNG(0.05),       //avgEngagementRate
                                    NormalRNG(0,1),                               //tweetProbability
                                    0.01,                 //exponRNG(2.0),        //popularityMultiplier
                                    (int)NormalRNG(0,100),                        //baseHumanFollowerCount
                                    i*100                                         //baseBotFollowerCount
                            ));
        }
        //Print all initial account stats
        System.out.println("INITIAL STATISTICS\n\n"+
                           "Total Accounts --> " + Account.totalAccounts +"\n"+
                           "Total Tweets ----> " + Tweet.totalTweets + "\n");

        for (int i = 0; i < allAccounts.size(); i++) {
            System.out.println(allAccounts.get(i));
        }

        while (Tweet.totalTweets < 10000) {
            for (int i = 0; i < allAccounts.size(); i++) {
                double tweetProbThreshold = rand.nextDouble();

                Account currentAccount = allAccounts.get(i);
                double currentTweetProbability = currentAccount.getTweetProbability();
                double currentTweetQuality = NormalRNG(0,currentAccount.getAvgTweetQuality()); //std of tweet quality should be parameter
                double currentTweetEngagementRate = currentAccount.getAvgEngagementRate();
                int currentHumanFollowers = currentAccount.getHumanFollowerCount();
                double currentProbabilityMult = currentAccount.getPopularityMult();
    
                if (currentTweetProbability >= tweetProbThreshold) {
                    currentAccount.generateTweet(currentTweetQuality, currentAccount.getID());
                    Tweet currentTweet = currentAccount.getMostRecentTweet();

                    int totalTweetImpressions = genImpressions(currentHumanFollowers, currentProbabilityMult, currentTweetQuality);
                    totalTweetImpressions += currentAccount.getBotFollowerCount();
    
                    int retweets = genRetweets(totalTweetImpressions,retweetProbability);
                    retweets += currentAccount.getBotFollowerCount();
    
                    totalTweetImpressions += retweets*exponRNG(1);
                    int newFollowers = genNewFollowers(totalTweetImpressions-currentHumanFollowers,newFollowerProbability);
    
                    currentAccount.setPopularityMult(currentAccount.getPopularityMult()+popUpdate(newFollowers,.01));
                    currentAccount.incrementFollowers(newFollowers);

                    currentTweet.setImpressions(totalTweetImpressions);
                    currentAccount.incrementTotalImpressions(totalTweetImpressions);
                    currentAccount.incrementTotalEngagements((int)(totalTweetImpressions*currentTweetEngagementRate));

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

    public static int genRetweets(int impressions, double retweetProb){
        int retweets = 0;
        retweets = binomTest(impressions, retweetProb);
        return retweets;
    }

    public static int genNewFollowers(int impressions, double newFollowerProb){
        int newFollowers = 0;
        newFollowers = binomTest(impressions, newFollowerProb);
        return newFollowers;
    }

    public static double popUpdate(int newFollowers, double popRate){
        int x = 0;
        x = binomTest(newFollowers,popRate);
        return x*.001;
    }


    public static void calculateInfluenceScores() {
        long overallTotalImpressions = 0;
        int overallTotalEngagements = 0;
        for (int i = 0; i < allAccounts.size(); i++) {
            Account currentAccount = allAccounts.get(i); 
            long accountTotalImpressions = currentAccount.getTotalImpressions();
            int accountTotalEngagements = currentAccount.getTotalEngagements();
            overallTotalImpressions += accountTotalImpressions;
            overallTotalEngagements += accountTotalEngagements;
        }
        for (int i = 0; i < allAccounts.size(); i++) {
            Account currentAccount = allAccounts.get(i); 
            long accountTotalImpressions = currentAccount.getTotalImpressions();
            int accountTotalEngagements = currentAccount.getTotalEngagements();
            double influenceScore = ((double)accountTotalImpressions/overallTotalImpressions)*100;
            influenceScore = ((double)accountTotalEngagements/overallTotalEngagements)*100;
            currentAccount.setInfluenceScore(influenceScore);
        }
    }
}