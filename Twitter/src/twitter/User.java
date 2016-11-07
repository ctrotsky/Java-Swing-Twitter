/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter;

import Visitor.Visitor;
import java.util.ArrayList;

/**
 *
 * @author Colin
 */
public class User extends Subject implements Observer, UserElement{
    private UserElementTreeModel treeModel;
    private String uniqueID;
    private ArrayList<String> tweets;
    private ArrayList<String> newsFeed;
    private UserView userView;
    
    public User(UserElementTreeModel treeModel, String uniqueID){
        this.treeModel = treeModel;
        this.uniqueID = uniqueID;
        attach(this);
        userView = new UserView(this);
        tweets = new ArrayList();
        newsFeed = new ArrayList();
        userView.init();
    }
    
    public String getLatestTweet(){
        return tweets.get(tweets.size() - 1);
    }
    
    public ArrayList<String> getTweets(){
        return tweets;
    }

    @Override
    public void update(Subject subject) {
        if (subject instanceof User) {
            System.out.println("subject: " + ((User) subject).getUniqueID());
            postToNewsFeed(((User) subject).getUniqueID() + ": " + ((User) subject).getLatestTweet());
        }
    }
    
    public void postToNewsFeed(String tweet){
        System.out.println("tweet:" + tweet);
        newsFeed.add(tweet);
        userView.addTweetToNewsFeed(tweet);
    }
    
    public void postTweet(String tweet){
        tweets.add(tweet);
        notifyObservers();
    }
    
    //returns if user was successfully followed
    public boolean followUser(String userID){
        User followUser = (User) treeModel.findUserByID((UserElement) treeModel.getRoot(), userID);
        
        if (followUser != null){
            followUser.attach(this);
            return true;
        }
        else {
            return false;
        }
        
    }
    
    public void openUserView(){
        userView.setVisible(true);
    }
    
    
    @Override
    public String toString(){
        return uniqueID;
    }   
    
    /////////////////////////////////////////////
    // Implementation of UserElement methods
    /////////////////////////////////////////////
    
    @Override
    public String getUniqueID(){
        return uniqueID;
    }

    @Override
    public void add(UserElement elem) {
        //Users are leaf nodes, so this method is not applicable to this class.
        throw new UnsupportedOperationException("Cannot add children to User");
    }

    @Override
    public UserElement getChild(int i) {
        //Users are leaf nodes, so this method is not applicable to this class.
        return null;
    }

    @Override
    public int getIndexOfChild(UserElement elem) {
        //Users are leaf nodes, so this method is not applicable to this class.
        return -1;
    }

    @Override
    public int getChildCount() {
        //Users are leaf nodes, so this method is not applicable to this class.
        return 0;
    }

    @Override
    public void accept(Visitor vis) {
        vis.atUser(this);
    }
    
    
    
    
    
}
