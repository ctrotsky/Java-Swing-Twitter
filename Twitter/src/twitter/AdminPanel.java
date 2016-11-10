/***************************************************************
* file: AdminPanel.java
* author: Colin Trotter
* class: CS 356 – Object-Oriented Design and Programming
*
* assignment: Assignment 2 - Twitter
* date last modified: 11/7/2016
*
* purpose: GUI using the Singleton pattern that allows the user to create Users and Groups, as well as perform
* analysis functions on the Tree of users and groups.
*
****************************************************************/ 

package twitter;

import tree.*;
import visitor.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AdminPanel extends TwitterForm implements ActionListener{

    private static AdminPanel INSTANCE;     //Singleton Instance of AdminPanel
    
    private JLabel formTitle;               //Label displaying title of form
    
    private JPanel analysisPanel;           //Panel containing buttons using Visitor pattern to analize tree view
    private JButton showUserTotal;          //Button that will display the total number of users that are descendents of the selected UserElement
    private JButton showGroupTotal;         //Button that will display the total number of groups that are descendents of the selected UserElement
    private JButton showMessagesTotal;      //Button that will display the total number of messages posted by Users that are descendents of the selected UserElement
    private JButton showPositivePercentage; //Button that will display the percentage of 'positive' messages that are posted by users that are descendents of the selected UserElement
    private JLabel analysisTitle;           //Label displaying title of analysis panel
    
    private JPanel treeViewPanel;           //Panel containing UserElement tree
    private UserElement rootGroup;          //Root group of UserElement tree
    private UserElementTreeModel treeModel; //Tree model that contains UserElements and methods to manage them
    private JTree tree;                     //JTree to display treeModel
    private JButton openUserView;           //Button to open the UserView of the selected UserElement
    private JLabel treeViewTitle;           //Label displaying title of Tree View panel
    private JScrollPane treeScrollPane;     //Scroll pane to hold tree
    
    private JPanel userGroupManagementPanel;//Panel to manage adding new Users and Groups
    private JButton addUser;                //Button that will add a new User as a child of the selected UserElement
    private JButton addGroup;               //Button that will add a new Group as a child of the selected UserElement
    private JTextField userID;              //UniqueID for the new User to have
    private JTextField groupID;             //UniqueID for the new Group to have
    private JLabel userIDLabel;             //Label to describe userID text field
    private JLabel groupIDLabel;            //Label to describe groupID text field
    private JLabel userGroupManagementTitle;//Label displaying title of User/Group Management panel
    
    /*
    * CONSTRUCTOR: AdminPanel
    * 
    * Private constructor for AdminPanel. Only able to be called from within this class. Allows for Singleton
    * design pattern. This constructor can only be called one time when the program runs.
    */
    private AdminPanel(){
        System.out.println("Admin Panel Constructed");
    }
    
    /*
    * FUNCTION: getInstance()
    * 
    * Returns the singleton INSTANCE of AdminPanel. If INSTANCE has not been instantiated yet, the constructor
    * will be called. Constructor will only be called one time. This is thread safe.
    */
    public static AdminPanel getInstance(){
        if (INSTANCE == null){
            synchronized(AdminPanel.class){
                if (INSTANCE == null){
                    INSTANCE = new AdminPanel();
                }
            }
        }
        return INSTANCE;
    }
    
    /*
    * FUNCTION: init()
    * 
    * Sets up the form's layout and initializes all components.
    */
    public void init(){
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Twitter [Admin Panel]");
        setSize(600, 400);
        setLocationRelativeTo(null);
        
        formTitle = new JLabel("Twitter");
        formTitle.setHorizontalAlignment(SwingConstants.CENTER);
        formTitle.setBounds(300,5,200,50);
        formTitle.setForeground(new Color(98, 190, 253));
        formTitle.setFont(new Font("SANS_SERIF", Font.BOLD + Font.ITALIC, 36));
        add(formTitle);
        
        /////////////////////////////////////////////
        // Visitor Buttons Panel
        // =========================================
        // Buttons: showUserTotal, showGroupTotal, showMessagesTotal, showPositivePercentage
        // Labels: analysisTitle
        /////////////////////////////////////////////
        analysisPanel = new JPanel();
        stylePanel(analysisPanel, 230, 230, 350, 130);
        
        showUserTotal = new JButton("Show User Total");
        styleButton(showUserTotal, 10, 40, 160, 35);
        analysisPanel.add(showUserTotal);
        
        showGroupTotal = new JButton("Show Group Total");
        styleButton(showGroupTotal, 180, 40, 160, 35);
        analysisPanel.add(showGroupTotal);
        
        showMessagesTotal = new JButton("Show Messages Total");
        styleButton(showMessagesTotal, 10, 85, 160, 35);
        analysisPanel.add(showMessagesTotal);
        
        showPositivePercentage = new JButton("Show Positive Percentage");
        styleButton(showPositivePercentage, 180, 85, 160, 35);
        analysisPanel.add(showPositivePercentage);
        
        analysisTitle = new JLabel("Analysis Features");
        styleTitleLabel(analysisTitle, 0, 5, 350, 30);
        analysisPanel.add(analysisTitle);
        
        /////////////////////////////////////////////
        // Tree View Panel
        // =========================================
        // Panes: treeScrollPane
        // Trees: tree
        // Buttons: openUserView 
        // Labels: treeViewTitle
        /////////////////////////////////////////////  
        treeViewPanel = new JPanel();
        stylePanel(treeViewPanel, 10, 10, 210, 351);
        
        rootGroup = new Group(treeModel, "Root");
        treeModel = new UserElementTreeModel(rootGroup);
        tree = new JTree(treeModel);
        tree.setCellRenderer(new UserElementTreeCellRenderer());
        
        styleTree(tree, 0, 0, 190, 255);

        treeScrollPane = new JScrollPane(tree);
        treeScrollPane.setBounds(10, 40, 190, 255);
        treeViewPanel.add(treeScrollPane);
        
        openUserView = new JButton("Open User View");
        styleButton(openUserView, 10, 305, 190, 35);
        treeViewPanel.add(openUserView);
        
        treeViewTitle = new JLabel("Active Users");
        styleTitleLabel(treeViewTitle, 0, 5, 210, 30);
        treeViewPanel.add(treeViewTitle);
        
        
        add(analysisPanel);
        
        /////////////////////////////////////////////
        // User/Group Management Panel
        // =========================================
        // Buttons: addUser, addGroup
        // Text Fields: userID, groupID
        // Labels: userIDLabel, groupIDLabel, userGroupManagementTitle
        /////////////////////////////////////////////
        userGroupManagementPanel = new JPanel();
        stylePanel(userGroupManagementPanel, 230, 70, 350, 150);
        
        addUser = new JButton("Add User");
        styleButton(addUser, 180, 50, 160, 35);
        userGroupManagementPanel.add(addUser);
        
        addGroup = new JButton("Add Group");
        styleButton(addGroup, 180, 105, 160, 35);
        userGroupManagementPanel.add(addGroup);
        
        userID = new JTextField();
        userID.setBounds(10, 50, 160, 35);
        userGroupManagementPanel.add(userID);
        
        groupID = new JTextField();
        groupID.setBounds(10, 105, 160, 35);
        userGroupManagementPanel.add(groupID);
        
        userIDLabel = new JLabel("User ID:");
        userIDLabel.setBounds(10, 36, 160, 15);
        userGroupManagementPanel.add(userIDLabel);
        
        groupIDLabel = new JLabel("Group ID:");
        groupIDLabel.setBounds(10, 91, 160, 15);
        userGroupManagementPanel.add(groupIDLabel);
        
        userGroupManagementTitle = new JLabel("User / Group Management");
        styleTitleLabel(userGroupManagementTitle, 0, 5, 350, 30);
        userGroupManagementPanel.add(userGroupManagementTitle);
    
        setVisible(true);
        System.out.println("Admin Panel Initialized");
    }
    
    
    /*
    * FUNCTION: actionPerformed()
    * 
    * Get input from each of the buttons, and run the corresponding method.
    */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addUser) {
           addUser();
        } 
        else if (ae.getSource() == addGroup){
            addGroup();
        }
        else if (ae.getSource() == openUserView){
            openUserView();
        }
        else if (ae.getSource() == showUserTotal){
            showUserTotal();
        }
        else if (ae.getSource() == showGroupTotal){
            showGroupTotal();
        }
        else if (ae.getSource() == showMessagesTotal){
            showMessagesTotal();
        }
        else if (ae.getSource() == showPositivePercentage){
            showPositivePercentage();
        }
    }
    
    /*
    * FUNCTION: addUser()
    * 
    * Adds a new user with the uniqueID given by the userID text field. Duplicate uniqueIDs are not allowed.
    */
    private void addUser(){
        String id = userID.getText();
        if (!id.equals("")){
            UserElement parent = getSelectedUserElement();
            //search from rootGroup for duplicates, not from specified parent so there are no duplicates in entire tree
            if (treeModel.findUserByID(rootGroup, id) == null){
                treeModel.addUserElement(parent, new User(treeModel, id));
                userID.setText("");
            }
            else {
                errorMessage("User Already Exists",  "Error: That username is taken.");
            }
            
        }
    }
    
    /*
    * FUNCTION: addGroup()
    * 
    * Adds a new group with the uniqueID given by the groupID text field. Duplicate uniqueIDs are not allowed.
    */
    private void addGroup(){
        String id = groupID.getText();
        if (!id.equals("")){
            UserElement parent = getSelectedUserElement();
            if (treeModel.findGroupByID(rootGroup, id) == null){
                treeModel.addUserElement(parent, new Group(treeModel, id));
                groupID.setText("");
            }
            else {
                errorMessage("Group Already Exists",  "Error: That Group name is taken.");
            }            
        }
    }
    
    /*
    * FUNCTION: openUserView()
    *
    * Opens the UserView of the selected UserElement in the tree. If a User is selected, its UserView will be opened.
    * If a Group is selected, it will call openUserView on each of its children.
    */
    private void openUserView() {
        UserElement elem = getSelectedUserElement();
        elem.openUserView();
    }
    
    /*
    * FUNCTION: showUserTotal()
    *
    * Uses Visitors to get the total number of groups that
    * are descendents of the selected UserElement in the tree.
    */
    private void showUserTotal(){
        int result;
        UserElement start = getSelectedUserElement();
        UserTotalVisitor vis = new UserTotalVisitor();
        start.accept(vis);
        result = vis.total;
        JOptionPane.showMessageDialog(this, "Total count of Users: " + result, "Total Messages", JOptionPane.PLAIN_MESSAGE);
        System.out.println("Total users: " + result);
    }
    
    /*
    * FUNCTION: showGroupTotal()
    *
    * Uses Visitors to get the total number of groups that
    * are descendents of the selected UserElement in the tree.
    */
    private void showGroupTotal(){
        int result;
        UserElement start = getSelectedUserElement();
        GroupTotalVisitor vis = new GroupTotalVisitor();
        start.accept(vis);
        result = vis.total;
        JOptionPane.showMessageDialog(this, "Total count of Groups: " + result, "Total Groups", JOptionPane.PLAIN_MESSAGE);
        System.out.println("Total groups: " + result);
    }
    
    /*
    * FUNCTION: showMessagesTotal()
    *
    * Uses Visitors to get the total number of tweets posted by Users that
    * are descendents of the selected UserElement in the tree.
    */
    private void showMessagesTotal(){
        int result;
        UserElement start = getSelectedUserElement();
        MessagesTotalVisitor vis = new MessagesTotalVisitor();
        start.accept(vis);
        result = vis.total;
        JOptionPane.showMessageDialog(this, "Total count of Tweets: " + result, "Total Messages", JOptionPane.PLAIN_MESSAGE);
        System.out.println("Total messages: " + result);
    }
    
    /*
    * FUNCTION: showPositivePercentage()
    *
    * Uses Visitors to get a percentage representing the number of 'positive' messages posted by Users that
    * are descendents of the selected UserElement in the tree.
    */
    private void showPositivePercentage(){
        double result;
        UserElement start = getSelectedUserElement();
        PositiveMessagesTotalVisitor posTotalVis = new PositiveMessagesTotalVisitor();
        MessagesTotalVisitor messagesTotalVis = new MessagesTotalVisitor();
        start.accept(posTotalVis);
        start.accept(messagesTotalVis);
        
        result = (double)posTotalVis.total / (double)messagesTotalVis.total * 100.0;
        
        JOptionPane.showMessageDialog(this, "Percentage of Tweets containing positive messages: " + result + "%", "Positive Percentage", JOptionPane.PLAIN_MESSAGE);
        System.out.println("Positive percentage: " + result + "%");
    }
    
    /*
    * FUNCTION: getSelectedUserElement()
    *
    * Returns the UserElement that is selected in the JTree. If no UserElement is selected, returns the
    * rootGroup.
    */
    private UserElement getSelectedUserElement(){
        UserElement result = ((UserElement)tree.getLastSelectedPathComponent());
        if (result == null){
            result = rootGroup;
        }
        return result;
    }
    
    //TODO make UserManager class that does all the checking to see if user exists, then just pass new User object to addUser() method in AdminPanel or return from UserManager
}
