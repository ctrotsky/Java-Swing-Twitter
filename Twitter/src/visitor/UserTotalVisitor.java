/***************************************************************
* file: UserTotalVisitor.java
* author: Colin Trotter
* class: CS 356 – Object-Oriented Design and Programming
*
* assignment: Assignment 2 - Twitter
* date last modified: 11/7/2016
*
* purpose: Visitor implementation to count the total Users in a UserElementTreeModel starting from a specified
* parent node, and working down into it's children.
****************************************************************/ 
package visitor;

import twitter.Group;
import twitter.User;

/**
 *
 * @author Colin
 */
public class UserTotalVisitor implements Visitor {
    
    public int total;   //the total number of Users counted

    /*
    * FUNCTION: atUser()
    * 
    * Counts this user, and posts its uniqueID to the console.
    */
    @Override
    public void atUser(User e) {
        System.out.println("Counted User: " + e.getUniqueID());
        total++;
    }

    
    /*
    * FUNCTION: atGroup()
    * 
    * Posts this group's uniqueID to the console.
    */
    @Override
    public void atGroup(Group e) {
        System.out.println("Counting children of Group: " + e.getUniqueID());
    }
    
}
