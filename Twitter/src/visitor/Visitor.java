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
public interface Visitor {
    public void atUser(User e);
    public void atGroup(Group e);
}
