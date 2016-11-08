/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 *
 * @author Colin
 */
public class UserView extends TwitterForm {
    
    private User user;
    
    private JLabel formTitle;
    private JLabel userIDLabel;
    
    private JPanel followingPanel;
    private JScrollPane followingScrollPane;
    private JList followingList;
    private DefaultListModel followingListModel;
    private JButton followUser;
    private JTextField followUserIDTextField;
    private JLabel followUserIDLabel;
    private JLabel followingTitle;
    
    private JPanel newsFeedPanel;
    private JScrollPane newsFeedScrollPane;
    private DefaultListModel newsFeedListModel;
    private JList newsFeedList;
    private JButton postTweet;
    private JTextField tweetTextField;
    private JLabel newsFeedTitle;
    private JLabel tweetLabel;
    
    public UserView(User user){
        this.user = user;
    }
    
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
        panelLayout(followingPanel, 10, 60, 375, 210);
        
        followUser = new JButton("Follow User");
        buttonLayout(followUser, 205, 50, 160, 35, followingPanel);
        
        followUserIDLabel = new JLabel("User ID:");
        followUserIDLabel.setBounds(10, 36, 160, 15);
        followingPanel.add(followUserIDLabel);
        
        followingTitle = new JLabel("Following:");
        titleLayout(followingTitle, 0, 5, 365, 30);
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
        panelLayout(newsFeedPanel, 10, 280, 375, 260);
        
        postTweet = new JButton("Post Tweet");
        buttonLayout(postTweet, 205, 50, 160, 35, newsFeedPanel);
        
        tweetLabel = new JLabel("New Tweet:");
        tweetLabel.setBounds(10, 36, 160, 15);
        newsFeedPanel.add(tweetLabel);
        
        newsFeedTitle = new JLabel("News Feed:");
        titleLayout(newsFeedTitle, 0, 5, 365, 30);
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

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == postTweet) {
           postTweet();
        } 
        else if (ae.getSource() == followUser){
            followUser();
        }
    }
    
    public void addTweetToNewsFeed(String tweet){
        newsFeedListModel.add(0, tweet);
    }
    
    public void postTweet(){
        //if the user has typed in a tweet, post it, and empty the tweet text field
        if (!tweetTextField.getText().equals("")){
            user.postTweet(tweetTextField.getText());
            tweetTextField.setText("");
        }
    }
    
    public void followUser(){
        String followUserID = followUserIDTextField.getText();
        if (!followUserID.equals("")){
            if(user.followUser(followUserID)){
                System.out.println("User succesfully followed");
                followingListModel.add(0, followUserID);
            }
            else {
                JOptionPane.showMessageDialog(this, "Error: Could not follow User " + followUserID + ".", "Follow Failed", JOptionPane.ERROR_MESSAGE);
            }
            followUserIDTextField.setText("");
        }
    }
    
    //TODO count of followers and following at top of form
    
}
