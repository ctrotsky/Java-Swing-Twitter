package twitter;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Colin
 */
class Group implements UserElement{
    private String uniqueID;
    private ArrayList<UserElement> children;
    
    /////////////////////////////////////////////
    // Implementation of UserElement methods
    ///////////////////////////////////////////// 
    public Group(String uniqueID){
        this.uniqueID = uniqueID;
    }

    @Override
    public String getUniqueID() {
        return uniqueID;
    }

    @Override
    public void add(UserElement elem) {
        children.add(elem);
    }

    @Override
    public UserElement getChild(int i) {
        return children.get(i);
    }

    @Override
    public int getIndexOfChild(UserElement elem) {
        return children.indexOf(elem);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }
    
}
