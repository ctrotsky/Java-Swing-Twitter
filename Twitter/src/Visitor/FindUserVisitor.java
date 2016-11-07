/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visitor;

import twitter.Group;
import twitter.User;

/**
 *
 * @author Colin
 */
public class FindUserVisitor implements Visitor {
    
    public User result;
    private String id;
    
    public FindUserVisitor(String searchID){
        id = searchID;
    }

    @Override
    public void atUser(User e) {
        System.out.println("Searching User: " + e.getUniqueID());
        if (e.getUniqueID().equals(id)){
            System.out.println("Found User: " + e.getUniqueID());
            result = e;
        }
    }

    @Override
    public void atGroup(Group e) {
        System.out.println("Searching Group: " + e.getUniqueID());
    }
    
}
