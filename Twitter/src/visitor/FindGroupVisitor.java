/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitor;

import twitter.Group;
import twitter.User;

/**
 *
 * @author Colin
 */
public class FindGroupVisitor implements Visitor {
    
    public Group result;
    private String id;
    
    public FindGroupVisitor(String searchID){
        id = searchID.toLowerCase();
    }

    @Override
    public void atUser(User e) {
        System.out.println("Searching User: " + e.getUniqueID());
    }

    @Override
    public void atGroup(Group e) {
        System.out.println("Searching Group: " + e.getUniqueID());
        if (e.getUniqueID().toLowerCase().equals(id)){
            System.out.println("Found Group: " + e.getUniqueID());
            result = e;
        }
    }
    
}
