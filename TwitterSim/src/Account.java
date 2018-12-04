import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class Account {

    private static Random rand = new Random();

    private static int totalAccounts = 0;
    private int accountID;
    private int followerCount = 0;
    private int tweetCount = 0;
    private int populatiryMultiplier = 1;

    private List<Tweet> tweets = new ArrayList<Tweet>();
    private List<Account> followers = new ArrayList<Account>();

    private boolean isBot;

    private double avgTweetQuality;

    public Account(boolean isBot) {
        setID();
        setAccountType(isBot);
        generateAvgTweetQuality();
    }

    private void setID() {
        this.accountID = ++totalAccounts;
    }

    public int getID() {
        return this.accountID;
    }

    private void setAccountType(boolean isBot) {
        this.isBot = isBot;
    }

    public boolean isBot() {
        return isBot;
    }

    private void generateAvgTweetQuality() {
        if(this.isBot()) {
            //It's a bot, so it's avg tweet quality is between 0.0 and 0.7.
            //Ideally, this should be based on some distribution.
            this.avgTweetQuality = rand.nextDouble() * 0.7;
        } else {
            //It's a human, so it's avg tweet quality is between 0.3 and 1.0.
            //Ideally, this should be based on some distribution.
            this.avgTweetQuality = (rand.nextDouble() * 0.7) + 0.3;

            //Human accounts get a random base popularity multiplier to simulate
            //celebrities or well known entities that are more likely to have a 
            //higher initial popularity.
            //Ideally, this should be based on some distribution.
            setPopularityMult(rand.nextInt(3)+1);
        }
    }

    public double getAvgTweetQuality() {
        return this.avgTweetQuality;
    }

    public void setPopularityMult(int popularityMultiplier) {
        this.populatiryMultiplier = popularityMultiplier;
    }

    public int getPopularityMult() {
        return this.populatiryMultiplier;
    }

    public void follow(Account followee) {
        if (!followers.contains(followee)) {
            followers.add(followee);
        }
    }

    public void generateTweet() {
        tweets.add(new Tweet());
    }



}