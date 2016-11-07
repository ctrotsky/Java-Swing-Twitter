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
public class MessagesTotalVisitor implements Visitor {
    
    public int total;

    @Override
    public void atUser(User e) {
        System.out.println("Counted messages from User: " + e.getUniqueID());
        total+= e.getTweets().size();
    }

    @Override
    public void atGroup(Group e) {
        System.out.println("Counting children of Group: " + e.getUniqueID());
    }
    
}
