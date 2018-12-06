import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Account {

    public static int totalAccounts = 0;
    private int accountID;
    private int botFollowerCount = 0;
    private int humanFollowerCount = 0;
    private int tweetCount = 0;
    private double tweetProbabilty;
    private double avgTweetQuality;
    private double avgEngagementRate;
    private double populatiryMultiplier;
    private long totalImpressions = 0;
    private int totalEngagements = 0;
    private double influenceScore = 0;

    private List<Tweet> tweets = new ArrayList<Tweet>();

    public Account(double avgTweetQuality, double avgEngagementRate, double tweetProbability, double popularityMultiplier, int baseHumanFollowerCount, int baseBotFollowerCount) {
        setID();
        setAvgTweetQuality(avgTweetQuality);
        setAvgEngagementRate(avgEngagementRate);
        setTweetProbability(tweetProbability);
        setPopularityMult(popularityMultiplier);
        setInitHumanFollowers(baseHumanFollowerCount);
        setInitBotFollowers(baseBotFollowerCount);
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

    private void setAvgEngagementRate(double avgEngagementRate) {
        this.avgEngagementRate = avgEngagementRate;
    }

    public double getAvgEngagementRate() {
        return this.avgEngagementRate;
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

    public Tweet getMostRecentTweet() {
        return tweets.get(tweets.size()-1);
    }

    public void setInitHumanFollowers(int baseHumanFollowerCount) {
        this.humanFollowerCount = baseHumanFollowerCount; //(int) (baseHumanFollowerCount * this.populatiryMultiplier);
    }

    public void setInitBotFollowers(int baseBotFollowerCount) {
        this.botFollowerCount = baseBotFollowerCount;
    }

    public void setInfluenceScore(double influenceScore) {
        this.influenceScore = influenceScore;
    }

    public double getInfluenceScore() {
        return this.influenceScore;
    }

    public void incrementTotalImpressions(int tweetImpressions) {
        this.totalImpressions += (long)tweetImpressions;
    }

    public void incrementTotalEngagements(int tweetEngagements) {
        this.totalEngagements += tweetEngagements;
    }

    public long getTotalImpressions() {
        return this.totalImpressions;
    }

    public int getTotalEngagements() {
        return this.totalEngagements;
    }

    public void incrementFollowers(int newFollowers){
        this.humanFollowerCount += newFollowers;
    }

    @Override
    public String toString() {
        String tweetList = "";

        for (int i = 0; i < tweets.size(); i++) {
            tweetList += tweets.get(i);
        }
        return String.format("Account " + getID() + "\n"+
                             "  Influence Score --------> " + getInfluenceScore() + "\n"+
                             "  Total Impressions ------> " + getTotalImpressions() + "\n"+
                             "  Total Engagements ------> " + getTotalEngagements() + "\n"+
                             "  Human Follower Count ---> " + getHumanFollowerCount() + "\n"+
                             "  Bot Follower Count -----> " + getBotFollowerCount() + "\n"+
                             "  Tweet Count ------------> " + getTweetCount() + "\n"+
                             "  Avg Tweet Quality ------> " + getAvgTweetQuality() + "\n"+
                             "  Avg Engagement Rate ----> " + getAvgEngagementRate() + "\n"+
                             "  Tweet Probability ------> " + getTweetProbability() + "\n"+
                             "  Popularity Multiplier --> " + (1+getPopularityMult()) + "\n"
                             //+"  Tweet List\n"+tweetList
                             );
    }

}