/***************************************************************
* file: User.java
* author: Colin Trotter
* class: CS 356 – Object-Oriented Design and Programming
*
* assignment: Assignment 2 - Twitter
* date last modified: 11/7/2016
*
* purpose: Implementation of UserElement. Represents a user of the twitter program. Users can follow other users,
* post tweets, and receive updates (tweets) from the users they are following. Has a UserView object that shows
* the User's data in a GUI form. Is both a Subject and Observer of the Observer design pattern. Is a Leaf class of Composite design pattern.
*
****************************************************************/ 
package twitter;

import observer.Observer;
import observer.Subject;
import tree.UserElementTreeModel;
import visitor.Visitor;
import java.util.ArrayList;

/**
 *
 * @author Colin
 */
public class User extends Subject implements Observer, UserElement{
    private UserElementTreeModel treeModel;                         //the TreeModel this user is contained in.
    private String uniqueID;                                        //a unique ID for this User. No other users will have the same ID.
    private ArrayList<String> tweets;                               //ArrayList containing tweets posted by this User
    private ArrayList<String> newsFeed;                             //ArrayList containing tweets posted by this User and Users this User is following.
    private UserView userView;                                      //UserView object to display data of this User
    private final String iconURL = "/resources/user_icon.png";      //filepath to an image to represent a User in a UserElementTreeModel
    private ArrayList<User> following;                              //ArrayList containing Users this User is following.
    
    /*
    * Constructor: User(UserElementTreeModel treeModel, Strin uniqueID)
    * 
    * Constructs an instance with a reference to the tree model it is contained in, as well as a String containnig
    * this User's unique ID. Initializes variables, and initializes the userView. Also attaches this user as an observer
    * of itself so this User's tweets will appear on its own news feed.
    */
    public User(UserElementTreeModel treeModel, String uniqueID){
        this.treeModel = treeModel;
        this.uniqueID = uniqueID;
        
        userView = new UserView(this);
        tweets = new ArrayList();
        newsFeed = new ArrayList();
        following = new ArrayList();
        
        userView.init();
        attach(this);
        following.add(this);
    }
    
    /*
    * FUNCTION: getLatestTweet()
    * 
    * Returns the most recently posted tweet by this User.
    */
    public String getLatestTweet(){
        return tweets.get(tweets.size() - 1);
    }
    
    /*
    * FUNCTION: getTweets()
    * 
    * Returns the ArrayList of tweets posted by this User.
    */
    public ArrayList<String> getTweets(){
        return tweets;
    }

    /*
    * FUNCTION: update()
    * 
    * Receives updates from Users this User is following. When Following Users post a tweet, this user will
    * be updated, and it will be posted to their newsFeed.
    */
    @Override
    public void update(Subject subject) {
        if (subject instanceof User) {
            System.out.println("subject: " + ((User) subject).getUniqueID());
            postToNewsFeed(((User) subject).getUniqueID() + ": " + ((User) subject).getLatestTweet());
        }
    }
    
    /*
    * FUNCTION: postTweet()
    * 
    * Adds this tweet to this user's newsFeed. Will show up in the UserView as well.
    */
    public void postToNewsFeed(String tweet){
        System.out.println("tweet:" + tweet);
        newsFeed.add(tweet);
        userView.addTweetToNewsFeed(tweet);
    }
    
    /*
    * FUNCTION: postTweet()
    * 
    * Posts a tweet containing the string tweet. All observers of this User will be notified of the tweet, and it
    * will appear in their newsfeed.
    */
    public void postTweet(String tweet){
        tweets.add(tweet);
        notifyObservers();
    }
    
    /*
    * FUNCTION: followUser()
    * 
    * Attempts to follow the User with the given userID. Adds that user to the following ArrayList, and attaches
    * itself as an observer of the found user. If succesfully followed, returns true. Otherwise returns false.
    */
    public boolean followUser(String userID){
        User followUser = (User) treeModel.findUserByID((UserElement) treeModel.getRoot(), userID);
        
        if (followUser != null && !following.contains(followUser)){
            followUser.attach(this);
            following.add(followUser);
            return true;
        }
        else {
            return false;
        }
        
    }
    
    /*
    * FUNCTION: toString()
    * 
    * Returns this User's uniqueID. Needed to properly see uniqueID in JTree.
    */
    @Override
    public String toString(){
        return uniqueID;
    }   
    
    /////////////////////////////////////////////
    // Implementation of UserElement methods
    /////////////////////////////////////////////
     
    /*
    * FUNCTION: getUniqueID()
    * 
    * Returns this User's uniqueID.
    */
    @Override
    public String getUniqueID(){
        return uniqueID;
    }

    /*
    * FUNCTION: getIndexOfChild()
    * 
    * Causes the UserView to display an error message, because users cannot have children.
    */
    @Override
    public void add(UserElement elem) {
        //Users are leaf nodes, so this method is not applicable to this class.
        userView.errorMessage("Children Error", "Error: Users cannot have children.");
    }

    /*
    * FUNCTION: getChild()
    * 
    * Returns -1 because users cannot have children.
    */
    @Override
    public UserElement getChild(int i) {
        //Users are leaf nodes, so this method is not applicable to this class.
        return null;
    }

    /*
    * FUNCTION: getIndexOfChild()
    * 
    * Returns -1 because users cannot have children.
    */
    @Override
    public int getIndexOfChild(UserElement elem) {
        //Users are leaf nodes, so this method is not applicable to this class.
        return -1;
    }

    /*
    * FUNCTION: getChildCount()
    * 
    * Returns 0 because Users cannot have children.
    */
    @Override
    public int getChildCount() {
        //Users are leaf nodes, so this method is not applicable to this class.
        return 0;
    }
    
    /*
    * FUNCTION: accept()
    * 
    * Accepts a visitor and allows it to perform its operation.
    */
    @Override
    public void accept(Visitor vis) {
        vis.atUser(this);
    }   
    
    /*
    * FUNCTION: getIconURL()
    * 
    * Returns a string representing the filepath to the image for this User to use in a @UserElementTreeModel.
    */
    @Override
    public String getIconURL() {
        return iconURL;
    }
    
    /*
    * FUNCTION: openUserView()
    * 
    * Makes this User's UserView visible.
    */
    @Override
    public void openUserView(){
        userView.setVisible(true);
    }
}
