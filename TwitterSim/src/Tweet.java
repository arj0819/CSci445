public class Tweet {
    
    public static int totalTweets = 0;
    private int tweetID;

    public Tweet() {
        setID();
    }

    private void setID() {
        this.tweetID = ++totalTweets;
    }

}