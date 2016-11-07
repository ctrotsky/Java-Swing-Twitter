package twitter;

import Visitor.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AdminPanel extends TwitterForm implements ActionListener{

    private static AdminPanel INSTANCE;
    
    private JLabel formTitle;
    
    private JPanel visitorButtonsPanel;
    private JButton showUserTotal;
    private JButton showGroupTotal;
    private JButton showMessagesTotal;
    private JButton showPositivePercentage;
    private JLabel visitorButtonsTitle;
    
    private JPanel treeViewPanel;
    private UserElement rootGroup;
    private UserElementTreeModel treeModel;
    private JTree tree;
    private JButton openUserView;
    private JLabel treeViewTitle;
    
    private JPanel userGroupManagementPanel;
    private JButton addUser;
    private JButton addGroup;
    private JTextField userID;
    private JTextField groupID;
    private JLabel userIDLabel;
    private JLabel groupIDLabel;
    private JLabel userGroupManagementTitle;
    
    private AdminPanel(){
        System.out.println("Admin Panel Constructed");
    }
    
    /*
    * FUNCTION: getInstance()
    * 
    * Returns the singleton INSTANCE of AdminPanel.
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
    * Sets up the form and initializes all components.
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
        // Labels: visitorButtonsTitle
        /////////////////////////////////////////////
        visitorButtonsPanel = new JPanel();
        panelLayout(visitorButtonsPanel, 230, 230, 350, 130);
        
        showUserTotal = new JButton("Show User Total");
        buttonLayout(showUserTotal, 10, 40, 160, 35, visitorButtonsPanel);
        
        showGroupTotal = new JButton("Show Group Total");
        buttonLayout(showGroupTotal, 180, 40, 160, 35, visitorButtonsPanel);
        
        showMessagesTotal = new JButton("Show Messages Total");
        buttonLayout(showMessagesTotal, 10, 85, 160, 35, visitorButtonsPanel);
        
        showPositivePercentage = new JButton("Show Positive Percentage");
        buttonLayout(showPositivePercentage, 180, 85, 160, 35, visitorButtonsPanel);
        
        visitorButtonsTitle = new JLabel("Analysis Features");
        titleLayout(visitorButtonsTitle, 0, 5, 350, 30);
        visitorButtonsPanel.add(visitorButtonsTitle);
        
        /////////////////////////////////////////////
        // Tree View Panel
        // =========================================
        // Trees: tree
        // Buttons: openUserView 
        // Labels: treeViewTitle
        /////////////////////////////////////////////
        treeViewPanel = new JPanel();
        panelLayout(treeViewPanel, 10, 10, 210, 351);
        
        treeModel = new UserElementTreeModel(rootGroup);
        rootGroup = new Group(treeModel, "Root");
        tree = new JTree(treeModel);
        
        treeLayout(tree, 10, 40, 190, 255);
        treeViewPanel.add(tree);
        
        openUserView = new JButton("Open User View");
        buttonLayout(openUserView, 10, 305, 190, 35, treeViewPanel);
        
        treeViewTitle = new JLabel("Active Users");
        titleLayout(treeViewTitle, 0, 5, 210, 30);
        treeViewPanel.add(treeViewTitle);
        
        
        add(visitorButtonsPanel);
        
        /////////////////////////////////////////////
        // User/Group Management Panel
        // =========================================
        // Buttons: addUser, addGroup
        // Text Fields: userID, groupID
        // Labels: userIDLabel, groupIDLabel, userGroupManagementTitle
        /////////////////////////////////////////////
        userGroupManagementPanel = new JPanel();
        panelLayout(userGroupManagementPanel, 230, 70, 350, 150);
        
        addUser = new JButton("Add User");
        buttonLayout(addUser, 180, 50, 160, 35, userGroupManagementPanel);
        
        addGroup = new JButton("Add Group");
        buttonLayout(addGroup, 180, 105, 160, 35, userGroupManagementPanel);
        
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
        titleLayout(userGroupManagementTitle, 0, 5, 350, 30);
        userGroupManagementPanel.add(userGroupManagementTitle);
    
        setVisible(true);
        System.out.println("Admin Panel Initialized");
    }
    
    
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
    
    private void addUser(){
        String id = userID.getText();
        if (!id.equals("")){
            UserElement parent = ((UserElement)tree.getLastSelectedPathComponent());
            //If nothing is selected in active users, create user in root group
            if (parent == null){
                parent = rootGroup;
            }
            //search from rootGroup for duplicates, not from specified parent
            if (treeModel.findUserByID(rootGroup, id) == null){
                treeModel.addUserElement(parent, new User(treeModel, id));
            }
            else {
                JOptionPane.showMessageDialog(this, "Error: That username is taken.", "User Already Exists", JOptionPane.ERROR_MESSAGE);
            }
            userID.setText("");
        }
    }
    
    private void addGroup(){
        String id = groupID.getText();
        if (!id.equals("")){
            UserElement parent = ((UserElement)tree.getLastSelectedPathComponent());
            //If nothing is selected in active users, create user in root group
            if (parent == null){
                parent = rootGroup;
            }
            if (treeModel.findGroupByID(rootGroup, id) == null){
                treeModel.addUserElement(parent, new Group(treeModel, id));
            }
            else {
                JOptionPane.showMessageDialog(this, "Error: That group name is taken.", "Group Already Exists", JOptionPane.ERROR_MESSAGE);
            }
            groupID.setText("");
        }
    }
    
    private void openUserView() {
        UserElement elem = ((UserElement)tree.getLastSelectedPathComponent());
        if (elem instanceof User){
            ((User)elem).openUserView();
        }
        else {
            //Could change this. Could make openUserView() a method in UserElement, if group then call on all children.
            JOptionPane.showMessageDialog(this, "Error: Cannot open UserView of Group", "Group Already Exists", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showUserTotal(){
        int result;
        UserElement start = ((UserElement)tree.getLastSelectedPathComponent());
        if (start == null){
                start = rootGroup;
        }
        UserTotalVisitor vis = new UserTotalVisitor();
        start.accept(vis);
        result = vis.total;
        JOptionPane.showMessageDialog(this, "Total count of Users: " + result, "Total Messages", JOptionPane.PLAIN_MESSAGE);
        System.out.println("Total users: " + result);
    }
    
    private void showGroupTotal(){
        int result;
        UserElement start = ((UserElement)tree.getLastSelectedPathComponent());
        if (start == null){
                start = rootGroup;
        }
        GroupTotalVisitor vis = new GroupTotalVisitor();
        start.accept(vis);
        result = vis.total;
        JOptionPane.showMessageDialog(this, "Total count of Groups: " + result, "Total Groups", JOptionPane.PLAIN_MESSAGE);
        System.out.println("Total groups: " + result);
    }
    
    private void showMessagesTotal(){
        int result;
        UserElement start = ((UserElement)tree.getLastSelectedPathComponent());
        if (start == null){
                start = rootGroup;
        }
        MessagesTotalVisitor vis = new MessagesTotalVisitor();
        start.accept(vis);
        result = vis.total;
        JOptionPane.showMessageDialog(this, "Total count of Tweets: " + result, "Total Messages", JOptionPane.PLAIN_MESSAGE);
        System.out.println("Total messages: " + result);
    }
    
    private void showPositivePercentage(){
        double result;
        UserElement start = ((UserElement)tree.getLastSelectedPathComponent());
        if (start == null){
                start = rootGroup;
        }
        PositiveMessagesTotalVisitor posTotalVis = new PositiveMessagesTotalVisitor();
        MessagesTotalVisitor messagesTotalVis = new MessagesTotalVisitor();
        start.accept(posTotalVis);
        start.accept(messagesTotalVis);
        
        result = (double)posTotalVis.total / (double)messagesTotalVis.total * 100.0;
        
        JOptionPane.showMessageDialog(this, "Percentage of Tweets containing positive messages: " + result + "%", "Positive Percentage", JOptionPane.PLAIN_MESSAGE);
        System.out.println("Positive percentage: " + result + "%");
    }
    
    //TODO make UserManager class that does all the checking to see if user exists, then just pass new User object to addUser() method in AdminPanel or return from UserManager
}
