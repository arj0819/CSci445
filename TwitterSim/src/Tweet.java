public class Tweet {
    
    public static int totalTweets = 0;
    private int tweetID;
    private int tweetedByID;
    private double quality;

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
        this.quality = quality;
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

    @Override
    public String toString() {
        return String.format("    Tweet " + getID() + "\n"+
                             "      Tweeted By --> Account "+getTweetedByID()+"\n"+
                             "      Quality -----> " + getQuality() + "\n");
    }

}