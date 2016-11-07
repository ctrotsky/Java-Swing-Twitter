package twitter;

import Visitor.Visitor;
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
public class Group implements UserElement{
    private UserElementTreeModel treeModel;
    private String uniqueID;
    private ArrayList<UserElement> children;
    
    public Group(UserElementTreeModel treeModel, String uniqueID){
        this.treeModel = treeModel;
        this.uniqueID = uniqueID;
        children = new ArrayList<>();
    }
    
    @Override
    public String toString(){
        return uniqueID;
    }
    
    /////////////////////////////////////////////
    // Implementation of UserElement methods
    ///////////////////////////////////////////// 

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

    @Override
    public void accept(Visitor vis) {
        vis.atGroup(this);
        for (UserElement elem : children){
            elem.accept(vis);
        }
    }
    
}
