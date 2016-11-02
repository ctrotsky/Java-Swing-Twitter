/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter;

/**
 *
 * @author Colin
 */
class User extends Subject implements Observer{
    private String uniqueID;
    private String latestTweet;
    private String newsFeed;
    private UserView userView;
    
    public User(String uniqueID){
        this.uniqueID = uniqueID;
        attach(this);
        userView = new UserView(this);
        userView.init();
    }
    
    public String getUniqueID(){
        return uniqueID;
    }
    
    public String getLatestTweet(){
        return latestTweet;
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof User) {
            System.out.println("subject: " + ((User) subject).getUniqueID());
            postToNewsFeed(((User) subject).getUniqueID() + ": " + ((User) subject).getLatestTweet());
        }
    }
    
    public void postToNewsFeed(String tweet){
        newsFeed = newsFeed + tweet + "\n";
        userView.addTweetToNewsFeed(tweet);
    }
    
    public void postTweet(String tweet){
        latestTweet = tweet;
        notifyObservers();
    }
    
    public void followUser(String userID){
        //Not implemented.
        //TODO: Find user with ID in main thingy, attach that user.
    }
    
    public void openUserView(){
        userView.setVisible(true);
    }
    
    
    
    
    
    
}
