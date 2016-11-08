/***************************************************************
* file: UserElementTreeModel.java
* author: Colin Trotter
* class: CS 356 – Object-Oriented Design and Programming
*
* assignment: Assignment 2 - Twitter
* date last modified: 11/7/2016
*
* purpose: TreeModel implementation to be used with a JTree. Allows for nodes to be instances of UserElements.
*
****************************************************************/ 
package tree;

import visitor.FindUserVisitor;
import visitor.FindGroupVisitor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.*;
import twitter.UserElement;

public class UserElementTreeModel implements TreeModel{
    
    private UserElement root;                   //The root UserElement that all other contained UserElements are a descendent of.
    private List<TreeModelListener> listeners;  //List of TreeModelListeners to listen for changes in the tree's structure
    
    /*
    * CONSTRUCTOR: UserElementTreeModel(UserElement root)
    * 
    * Constructor that provides the root node for this tree model. All future added nodes will be children
    * of this root node.
    */
    public UserElementTreeModel(UserElement root){
        this.root = root;
        listeners = new ArrayList<>();
    }
    
    /*
    * FUNCTION: findUserByID()
    * 
    * Searches for and returns the User with the given id. Will search the provided UserElement start, and descendents of start.
    */
    public UserElement findUserByID(UserElement start, String id){
        FindUserVisitor vis = new FindUserVisitor(id);
        start.accept(vis);
        return vis.result;
    }
    
    /*
    * FUNCTION: findGroupByID()
    * 
    * Searches for and returns the Group with the given id. Will search the provided UserElement start, and descendents of start.
    */
    public UserElement findGroupByID(UserElement start, String id){
        FindGroupVisitor vis = new FindGroupVisitor(id);
        start.accept(vis);
        return vis.result;
    }
    
    /*
    * FUNCTION: findUserByID()
    * 
    * Each listener for this tree model will receive a treeStructureChanged TreeModelEvent, causing the
    * JTree to update its display with the new tree structure.
    */
    private void fireTreeStructureChanged()
    {
        Object[] o = {root};
        TreeModelEvent e = new TreeModelEvent(this, o);
        for(TreeModelListener l : listeners){
            l.treeStructureChanged(e);
        }
    }
    
    /*
    * FUNCTION: addUserElement()
    * 
    * Adds the given UserElement elem as a child of parent.
    */
    public void addUserElement(UserElement parent, UserElement elem){
        parent.add(elem);
        fireTreeStructureChanged();
    }

    /*
    * FUNCTION: getRoot()
    * 
    * Returns the root UserElement.
    */
    @Override
    public Object getRoot() {
        return root;
    }

    /*
    * FUNCTION: getRoot()
    * 
    * Gets the child of parent that is at the index index.
    */
    @Override
    public Object getChild(Object parent, int index) {
        return ((UserElement)(((UserElement) parent).getChild(index)));
    }

    /*
    * FUNCTION: getRoot()
    * 
    * Gets the number of children of the given UserElement parent.
    */
    @Override
    public int getChildCount(Object parent) {
        return (((UserElement) parent).getChildCount());
    }

    /*
    * FUNCTION: isLeaf()
    * 
    * Returns true if node has no children. Otherwise returns false.
    */
    @Override
    public boolean isLeaf(Object node) {
        return (((UserElement) node).getChildCount() == 0);
    }

    /*
    * FUNCTION: valueForPathChanged()
    * 
    * Unsupported
    */
    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*
    * FUNCTION: getIndexOfChild()
    * 
    * Returns the index of child in parent's children if it exists.
    */
    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return (((UserElement) parent).getIndexOfChild(((UserElement) child)));
    }

    /*
    * FUNCTION: addTreeModelListener()
    * 
    * Adds a TreeModelListener for this Tree Model.
    */
    @Override
    public void addTreeModelListener(TreeModelListener l) {
        listeners.add(l);
    }

    /*
    * FUNCTION: addTreeModelListener()
    * 
    * Removes a TreeModelListener for this Tree Model.
    */
    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        listeners.remove(l);
    }
    
}
