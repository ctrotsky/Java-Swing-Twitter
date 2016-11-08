/***************************************************************
* file: UserElementTreeCellRenderer.java
* author: Colin Trotter
* class: CS 356 – Object-Oriented Design and Programming
*
* assignment: Assignment 2 - Twitter
* date last modified: 11/7/2016
*
* purpose: TreeCellRenderer implementation to be used with a UserElementTreeModel. Allows for custom icons
* depending on the image URL returned by each UserElement's iconURL method.
*
****************************************************************/ 
package tree;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;
import twitter.Group;
import twitter.UserElement;

public class UserElementTreeCellRenderer implements TreeCellRenderer {
    private JLabel label;   //The JLabel that displays the name and icon for the UserElement

    /*
    * CONSTRUCTOR: UserElementTreeCellRenderer()
    * 
    * Initializes the JLabel that will display information about the UserElement in the JTree.
    */
    public UserElementTreeCellRenderer() {
        label = new JLabel();
    }

    /*
    * FUNCTION: getTreeCellRendererComponent()
    * 
    * Will return a component with an icon and name for the given value to display in the JTree, 
    * as well as highlighting the value if it is selected. Group names will be bolded.
    */
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
                                                  boolean leaf, int row, boolean hasFocus) {
        UserElement userElem = ((UserElement) value);
        URL imageUrl = getClass().getResource(userElem.getIconURL());
            if (imageUrl != null) {
                label.setIcon(new ImageIcon(new ImageIcon(imageUrl).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
            }
            label.setText(userElem.getUniqueID() + " ");
            if (selected) {
                label.setOpaque(true);
            }
            else {
                label.setOpaque(false);
            }
            if (userElem instanceof Group){
                label.setFont(new Font("SANS_SERIF", Font.BOLD, 12));
            }
            else {
                label.setFont(new Font("SANS_SERIF", Font.PLAIN, 12));
            }

        return label;
    }
}

