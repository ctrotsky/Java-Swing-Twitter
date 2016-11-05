package twitter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.tree.*;

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
        
        rootGroup = new Group("Root");
        treeModel = new UserElementTreeModel(rootGroup);
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
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //TODO disable open user view button when user not selected in tree
}
