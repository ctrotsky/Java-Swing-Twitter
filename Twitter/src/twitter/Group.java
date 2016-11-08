/***************************************************************
* file: Group.java
* author: Colin Trotter
* class: CS 356 – Object-Oriented Design and Programming
*
* assignment: Assignment 2 - Twitter
* date last modified: 11/7/2016
*
* purpose: Implementation of UserElement. Can contain children that are other UserElements which could be of 
* type User or Group. Composite class of Composite design pattern. 
*
****************************************************************/ 

package twitter;

import tree.UserElementTreeModel;
import visitor.Visitor;
import java.util.ArrayList;

public class Group implements UserElement{
    private UserElementTreeModel treeModel;                         //the TreeModel this Group is contained in.
    private String uniqueID;                                        //a unique ID for this Group. No other Groups will have the same ID.
    private ArrayList<UserElement> children;                        //contains the children UserElements of this Group.
    private final String iconURL = "/resources/group_icon.png";     //filepath to an image to represent a Group in a UserElementTreeModel
    
    /*
    * CONSTRUCTOR: Group(UserElementTreeModel treeModel, String uniqueID)
    * 
    * Constructs an instance with a reference to the tree model it is contained in, as well as a String containnig
    * this User's unique ID. Initializes children ArrayList.
    */
    public Group(UserElementTreeModel treeModel, String uniqueID){
        this.treeModel = treeModel;
        this.uniqueID = uniqueID;
        children = new ArrayList<>();
    }
    
    /*
    * FUNCTION: toString()
    * 
    * Returns this Group's uniqueID. Needed to properly see uniqueID in JTree.
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
    * Returns the uniqueID of this Group.
    */
    @Override
    public String getUniqueID() {
        return uniqueID;
    }

    /*
    * FUNCTION: add()
    * 
    * Adds elem as a child of this Group.
    */
    @Override
    public void add(UserElement elem) {
        children.add(elem);
    }

    /*
    * FUNCTION: getChild()
    * 
    * Returns the child of this Group with the given index.
    */
    @Override
    public UserElement getChild(int i) {
        return children.get(i);
    }

    /*
    * FUNCTION: getIndexOfChild()
    * 
    * Returns the index of elem within this Group's children.
    */
    @Override
    public int getIndexOfChild(UserElement elem) {
        return children.indexOf(elem);
    }

    /*
    * FUNCTION: getChildCount()
    * 
    * Returns the number of children this Group has.
    */
    @Override
    public int getChildCount() {
        return children.size();
    }
    
    /*
    * FUNCTION: accept()
    * 
    * Accepts a visitor and allows it to perform its operation.
    */
    @Override
    public void accept(Visitor vis) {
        vis.atGroup(this);
        for (UserElement elem : children){
            elem.accept(vis);
        }
    }

    /*
    * FUNCTION: getIconURL()
    * 
    * Returns a string representing the filepath to the image for this Group to use in a @UserElementTreeModel.
    */
    @Override
    public String getIconURL() {
        return iconURL;
    }
    
    /*
    * FUNCTION: openUserView()
    * 
    * Calls openUserView() on each of this Group's children. Allows you to open userViews for multiple Users
    * at once.
    */
    @Override
    public void openUserView(){
        for (UserElement elem : children){
            elem.openUserView();
        }
    }

}
