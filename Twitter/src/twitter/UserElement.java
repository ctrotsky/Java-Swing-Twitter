/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter;

import Visitor.Visitor;

/**
 *
 * @author Colin
 */


//COMPONENT in Composite pattern
public interface UserElement {
    public String getUniqueID();
    public void add(UserElement elem);
    public UserElement getChild(int i);
    public int getIndexOfChild(UserElement elem);
    public int getChildCount();
    public void accept(Visitor vis);
}
