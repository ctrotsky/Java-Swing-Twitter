/***************************************************************
* file: FindGroupVisitor.java
* author: Colin Trotter
* class: CS 356 – Object-Oriented Design and Programming
*
* assignment: Assignment 2 - Twitter
* date last modified: 11/7/2016
*
* purpose: Visitor implementation to find and return a Group in a UserElementTreeModel starting from a specified
* parent node, and working down into it's children.
****************************************************************/ 
package visitor;

import twitter.Group;
import twitter.User;

public class FindGroupVisitor implements Visitor {
    
    public Group result;    //the group found with a uniqueID equal to the search ID, or null if not found.
    private String id;      //the uniqueID to search for.
       
    /*
    * CONSTRUCTOR: FindUserVisitor()
    * 
    * Constructor for this class, providing a uniqueID to search for.
    */
    public FindGroupVisitor(String searchID){
        id = searchID.toLowerCase();
    }

    /*
    * FUNCTION: atUser()
    * 
    * Post this User's uniqueID to the console.
    */
    @Override
    public void atUser(User e) {
        System.out.println("Searching User: " + e.getUniqueID());
    }

    
    /*
    * FUNCTION: atGroup()
    * 
    * Checks if this groups's uniqueID is equal to the searchID. If so, set result equal to this grpup.
    */
    @Override
    public void atGroup(Group e) {
        System.out.println("Searching Group: " + e.getUniqueID());
        if (e.getUniqueID().toLowerCase().equals(id)){
            System.out.println("Found Group: " + e.getUniqueID());
            result = e;
        }
    }
    
}
