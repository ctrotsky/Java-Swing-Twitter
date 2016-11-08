/***************************************************************
* file: Visitor.java
* author: Colin Trotter
* class: CS 356 – Object-Oriented Design and Programming
*
* assignment: Assignment 2 - Twitter
* date last modified: 11/7/2016
*
* purpose: Interface of the Visitor design pattern. Implementations must have an action to perform when visiting
* an @User and when visiting a @Group.
****************************************************************/ 
package visitor;

import twitter.Group;
import twitter.User;

public interface Visitor {
    
    /*
    * FUNCTION: atUser()
    * 
    * Visits a User object.
    */
    public void atUser(User e);
    
    /*
    * FUNCTION: atGroup()
    * 
    * Visits a Group object.
    */
    public void atGroup(Group e);
}
