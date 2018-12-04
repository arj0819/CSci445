public class Tweet {
    
    private static int totalTweets = 0;
    private int tweetID;

    public Tweet() {

    }

    private void setID() {
        this.tweetID = ++totalTweets;
    }

}