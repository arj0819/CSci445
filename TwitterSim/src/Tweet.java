public class Tweet {
    
    public static int totalTweets = 0;
    private int tweetID;
    private int tweetedByID;
    private double quality;
    private int impressions = 0;
    private int engagements = 0;

    public Tweet(double quality, int tweetedByID) {
        setID();
        setQuality(quality);
        setTweetedByID(tweetedByID);
    }

    private void setID() {
        this.tweetID = ++totalTweets;
    }

    public int getID() {
        return this.tweetID;
    }

    private void setQuality(double quality) {
        if (quality > 1.0) {
            this.quality = 1.0;
        } else {
            this.quality = quality;
        }
    }

    public double getQuality() {
        return this.quality;
    }

    public void setTweetedByID(int tweetedByID) {
        this.tweetedByID = tweetedByID;
    }

    public int getTweetedByID() {
        return this.tweetedByID;
    }

    public void setImpressions(int impressions) {
        this.impressions = impressions;
    }

    public int getImpressions() {
        return this.impressions;
    }

    public void setEngagements(int engagements) {
        this.engagements = engagements;
    }

    public int getEngagements() {
        return this.engagements;
    }

    @Override
    public String toString() {
        return String.format("    Tweet " + getID() + "\n"+
                             "      Tweeted By ---> Account "+getTweetedByID()+"\n"+
                             "      Quality ------> " + getQuality() + "\n"+
                             "      Impressions --> " + getImpressions() + "\n"+
                             "      Engagements --> " + getEngagements() + "\n");
    }

}