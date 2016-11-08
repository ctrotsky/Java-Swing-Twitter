/***************************************************************
* file: UserElement.java
* author: Colin Trotter
* class: CS 356 – Object-Oriented Design and Programming
*
* assignment: Assignment 2 - Twitter
* date last modified: 11/7/2016
*
* purpose: Interface for the component class of the Composite design pattern. Implementations can be shown in a
* @UserElementTreeModel. Used to create the User and Group classes.
*
****************************************************************/ 
package twitter;

import visitor.Visitor;

public interface UserElement {
    
    /*
    * FUNCTION: getUniqueID()
    * 
    * Returns the uniqueID of the UserElement.
    */
    public String getUniqueID();
    
    /*
    * FUNCTION: add()
    * 
    * Will attempt to add elem as a child of this UserElement.
    */
    public void add(UserElement elem);
    
    /*
    * FUNCTION: getChild()
    * 
    * Will attempt to return the child of this UserElement with the given index.
    */
    public UserElement getChild(int i);
    
    /*
    * FUNCTION: getIndexOfChild()
    * 
    * Will attempt to return the index of elem within this UserElement's children.
    */
    public int getIndexOfChild(UserElement elem);
    
    /*
    * FUNCTION: getChildCount()
    * 
    * Returns the number of children this UserElement has.
    */
    public int getChildCount();
    
    /*
    * FUNCTION: accept()
    * 
    * Accepts a visitor and allows it to perform its operation.
    */
    public void accept(Visitor vis);
    
    /*
    * FUNCTION: getIconURL()
    * 
    * Returns a string representing the filepath to the image for this UserElement to use in a @UserElementTreeModel.
    */
    public String getIconURL();
    
    /*
    * FUNCTION: openUserView()
    * 
    * Opens the userView for this UserElement.
    */
    public void openUserView();
}
