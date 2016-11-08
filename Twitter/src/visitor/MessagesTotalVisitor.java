/***************************************************************
* file: MessagesTotalVisitor.java
* author: Colin Trotter
* class: CS 356 – Object-Oriented Design and Programming
*
* assignment: Assignment 2 - Twitter
* date last modified: 11/7/2016
*
* purpose: Visitor implementation to count the total tweets posted by Users in a UserElementTreeModel starting from a specified
* parent node, and working down into it's children.
****************************************************************/ 
package visitor;

import twitter.Group;
import twitter.User;

public class MessagesTotalVisitor implements Visitor {
    
    public int total;   //the total number of messages counted

    /*
    * FUNCTION: atUser()
    * 
    * Counts the number of tweets posted by this User, and posts it's uniqueID to the console.
    */
    @Override
    public void atUser(User e) {
        System.out.println("Counted messages from User: " + e.getUniqueID());
        total+= e.getTweets().size();
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
