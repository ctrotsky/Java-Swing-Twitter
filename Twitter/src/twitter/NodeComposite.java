/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter;

import java.util.List;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Colin
 */
public abstract class NodeComposite extends DefaultMutableTreeNode {
    
    private List<NodeComposite> children = new ArrayList<>();
    private String uniqueID;
    
    public NodeComposite(String uniqueID){
        this.uniqueID = uniqueID;
    }
    
    public void add(NodeComposite user) {
        super.add(user);    //not sure about this
        children.add(user);
    }
    
    public int count() {
        return children.size();
    }
    
    public String getID(){
        return uniqueID;
    }
    
}
