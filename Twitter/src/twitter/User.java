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
            postToNewsFeed(((User) subject).getUniqueID() + ": " + ((User) subject).getLatestTweet());
        }
    }
    
    public void postToNewsFeed(String tweet){
        newsFeed = newsFeed + tweet + "\n";
    }
    
    public void postTweet(String tweet){
        //User should follow themself too so they can see their own tweets
        latestTweet = tweet;
        notifyObservers();
    }
    
    public void openUserView(){
        userView.setVisible(true);
    }
    
    
    
    
    
    
}
