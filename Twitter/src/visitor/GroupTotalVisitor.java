/***************************************************************
* file: GroupTotalVisitor.java
* author: Colin Trotter
* class: CS 356 – Object-Oriented Design and Programming
*
* assignment: Assignment 2 - Twitter
* date last modified: 11/7/2016
*
* purpose: Visitor implementation to count the total groups in a UserElementTreeModel starting from a specified
* parent node, and working down into it's children.
****************************************************************/ 
package visitor;

import twitter.Group;
import twitter.User;

public class GroupTotalVisitor implements Visitor {
    
    public int total;   //the total number of Groups counted.

    
    /*
    * FUNCTION: atUser()
    * 
    * Posts this User's uniqueID to the console.
    */
    @Override
    public void atUser(User e) {
        System.out.println("Found User: " + e.getUniqueID());
    }

    /*
    * FUNCTION: atGroup()
    * 
    * Counts this Group, and posts its uniqueID to the console.
    */
    @Override
    public void atGroup(Group e) {
        System.out.println("Counting Group: " + e.getUniqueID());
        total++;
    }
    
}
