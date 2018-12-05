import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class Account {

    private static Random rand = new Random();

    public static int totalAccounts = 0;
    private int accountID;
    private int botFollowerCount = 0;
    private int humanFollowerCount = 0;
    private int tweetCount = 0;
    private double tweetProbabilty;
    private double avgTweetQuality;
    private double populatiryMultiplier;

    private List<Tweet> tweets = new ArrayList<Tweet>();


    public Account(double avgTweetQuality, double tweetProbability, double popularityMultiplier) {
        setID();
        setAvgTweetQuality(avgTweetQuality);
        setTweetProbability(tweetProbability);
        setPopularityMult(popularityMultiplier);
    }

    private void setID() {
        this.accountID = ++totalAccounts;
    }

    public int getID() {
        return this.accountID;
    }

    private void setAvgTweetQuality(double avgTweetQuality) {
        this.avgTweetQuality = avgTweetQuality;
    }

    public double getAvgTweetQuality() {
        return this.avgTweetQuality;
    }

    private void setTweetProbability(double tweetProbabilty) {
        this.tweetProbabilty = tweetProbabilty;
    }

    public double getTweetProbability() {
        return this.tweetProbabilty;
    }

    public void setPopularityMult(double popularityMultiplier) {
        this.populatiryMultiplier = popularityMultiplier;
    }

    public double getPopularityMult() {
        return this.populatiryMultiplier;
    }

    public void generateTweet(double tweetQuality, int tweetedByID) {
        this.tweets.add(new Tweet(tweetQuality, tweetedByID));
        this.tweetCount++;
    }

    public int getBotFollowerCount() {
        return this.botFollowerCount;
    }

    public int getHumanFollowerCount() {
        return this.humanFollowerCount;
    }

    public int getTweetCount() {
        return this.tweetCount;
    }

    public List<Tweet> getTweets() {
        return this.tweets;
    }

    @Override
    public String toString() {
        String tweetList = "";

        for (int i = 0; i < tweets.size(); i++) {
            tweetList += tweets.get(i);
        }
        return String.format("Account " + getID() + "\n"+
                             "  Human Follower Count ---> " + getHumanFollowerCount() + "\n"+
                             "  Bot Follower Count -----> " + getBotFollowerCount() + "\n"+
                             "  Tweet Count ------------> " + getTweetCount() + "\n"+
                             "  Avg Tweet Quality ------> " + getAvgTweetQuality() + "\n"+
                             "  Tweet Probability ------> " + getTweetProbability() + "\n"+
                             "  Popularity Multiplier --> " + getPopularityMult() + "\n"+
                             "  Tweet List\n"+tweetList);
    }

}