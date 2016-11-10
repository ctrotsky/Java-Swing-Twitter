/***************************************************************
* file: UserView.java
* author: Colin Trotter
* class: CS 356 – Object-Oriented Design and Programming
*
* assignment: Assignment 2 - Twitter
* date last modified: 11/7/2016
*
* purpose: GUI to represent the data of a User object. Has buttons and controls to follow other Users, and
* see their tweets, as well as to post tweets for this User.
****************************************************************/ 
package twitter;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class UserView extends TwitterForm {
    
    private User user;                              //the @User object this UserView represents.
    
    private JLabel formTitle;                       //title displayed at the top of the form.
    private JLabel userIDLabel;                     //userID of the User this UserView represents.
    
    private JPanel followingPanel;                  //panel containing Components related to following Users
    private JScrollPane followingScrollPane;        //scroll pane to contain the Following list
    private JList followingList;                    //List to contain the uniqueID's of the Users this user is following
    private DefaultListModel followingListModel;    //List model for the Following list
    private JButton followUser;                     //Button that will follow the user with the uniqueID given
    private JTextField followUserIDTextField;       //TextField to type the uniqueID of the User you wish to follow
    private JLabel followUserIDLabel;               //Label marking the followUserTextField
    private JLabel followingTitle;                  //Title for this panel
    
    private JPanel newsFeedPanel;                   //panel containing components related to the news feed
    private JScrollPane newsFeedScrollPane;         //scroll pane to contain the news feed list
    private DefaultListModel newsFeedListModel;     //list model for the news feed
    private JList newsFeedList;                     //list representing tweets posted by the users this user is following
    private JButton postTweet;                      //button to post the tweet in tweetTextField
    private JTextField tweetTextField;              //text field to type a tweet to post
    private JLabel newsFeedTitle;                   //title of this panel
    private JLabel tweetLabel;                      //label for tweetTextField
    
    public UserView(User user){
        this.user = user;
    }
    
    /*
    * FUNCTION: init()
    * 
    * Sets up the form's layout and initializes all components.
    */
    public void init(){
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Twitter [User View: " + user.getUniqueID() + "]");
        setSize(400, 575);
        setLocationRelativeTo(null);
        
        formTitle = new JLabel("Twitter");
        formTitle.setHorizontalAlignment(SwingConstants.CENTER);
        formTitle.setBounds(200,5,200,50);
        formTitle.setForeground(new Color(98, 190, 253));
        formTitle.setFont(new Font("SANS_SERIF", Font.BOLD + Font.ITALIC, 36));
        add(formTitle);
        
        userIDLabel = new JLabel(user.getUniqueID());
        userIDLabel.setBounds(10,5,200,50);
        userIDLabel.setFont(new Font("SANS_SERIF", Font.BOLD, 36));
        add(userIDLabel);
        
        /////////////////////////////////////////////
        // Following Panel
        // =========================================
        // Panes: followingScrollPane
        // Lists: followingList
        // Labels: followUserIDLabel, followingTitle
        // Buttons: followUser
        // Text Fields: followUserIDTextField
        /////////////////////////////////////////////
        followingPanel = new JPanel();
        stylePanel(followingPanel, 10, 60, 375, 210);
        
        followUser = new JButton("Follow User");
        styleButton(followUser, 205, 50, 160, 35);
        followingPanel.add(followUser);
        
        followUserIDLabel = new JLabel("User ID:");
        followUserIDLabel.setBounds(10, 36, 160, 15);
        followingPanel.add(followUserIDLabel);
        
        followingTitle = new JLabel("Following:");
        styleTitleLabel(followingTitle, 0, 5, 365, 30);
        followingPanel.add(followingTitle);
        
        followUserIDTextField = new JTextField();
        followUserIDTextField.setBounds(10, 50, 185, 35);
        followingPanel.add(followUserIDTextField);
        
        followingListModel = new DefaultListModel();
        followingList = new JList(followingListModel);
        
        followingScrollPane = new JScrollPane(followingList);
        followingScrollPane.setBounds(10, 100, 355, 100);
        followingPanel.add(followingScrollPane);
        
        /////////////////////////////////////////////
        // News Feed Panel
        // =========================================
        // Panes: newsFeedScrollPane
        // Lists: newsFeedList
        // Labels: newsFeedTitle, tweetLabel
        // Buttons: followUser
        // TextFields: tweetTextField
        /////////////////////////////////////////////
        newsFeedPanel = new JPanel();
        stylePanel(newsFeedPanel, 10, 280, 375, 260);
        
        postTweet = new JButton("Post Tweet");
        styleButton(postTweet, 205, 50, 160, 35);
        newsFeedPanel.add(postTweet);
        
        tweetLabel = new JLabel("New Tweet:");
        tweetLabel.setBounds(10, 36, 160, 15);
        newsFeedPanel.add(tweetLabel);
        
        newsFeedTitle = new JLabel("News Feed:");
        styleTitleLabel(newsFeedTitle, 0, 5, 365, 30);
        newsFeedPanel.add(newsFeedTitle);
        
        tweetTextField = new JTextField();
        tweetTextField.setBounds(10, 50, 185, 35);
        newsFeedPanel.add(tweetTextField);
        
        newsFeedListModel = new DefaultListModel();
        newsFeedList = new JList(newsFeedListModel);
        
        newsFeedScrollPane = new JScrollPane(newsFeedList);
        newsFeedScrollPane.setBounds(10, 100, 355, 150);
        newsFeedPanel.add(newsFeedScrollPane);
        
        System.out.println("User View Initialized");
    }

    /*
    * FUNCTION: actionPerformed()
    * 
    * Get input from each of the buttons, and run the corresponding method.
    */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == postTweet) {
           postTweet();
        } 
        else if (ae.getSource() == followUser){
            followUser();
        }
    }
    
    /*
    * FUNCTION: addSTweetToNewsFeed()
    * 
    * Adds the string containing a tweet to the news feed list.
    */
    public void addTweetToNewsFeed(String tweet){
        newsFeedListModel.add(0, tweet);
    }
    
    /*
    * FUNCTION: postTweet()
    * 
    * Posts the tweet written in tweetTextField. Users following this user will be notified of this tweet.
    */
    public void postTweet(){
        //if the user has typed in a tweet, post it, and empty the tweet text field
        if (!tweetTextField.getText().equals("")){
            user.postTweet(tweetTextField.getText());
            tweetTextField.setText("");
        }
    }
    
    /*
    * FUNCTION: followUser()
    * 
    * Follows the user whose uniqueID is written in followUserIDTextField. This user will be displayed
    * in the followingList, and this UserView's user will be able to see tweets posted by this user.
    */
    public void followUser(){
        String followUserID = followUserIDTextField.getText();
        if (!followUserID.equals("")){
            if(user.followUser(followUserID)){
                System.out.println("User succesfully followed");
                followingListModel.add(0, followUserID);
            }
            else {
                errorMessage("Follow Failed",  "Error: Could not follow User " + followUserID + ".");
            }
            followUserIDTextField.setText("");
        }
    }
    
    
    
    //TODO count of followers and following at top of form
    
}
