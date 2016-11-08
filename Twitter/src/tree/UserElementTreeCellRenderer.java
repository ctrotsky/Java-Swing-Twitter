/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;
import twitter.UserElement;

/**
 *
 * @author Colin
 */
public class UserElementTreeCellRenderer implements TreeCellRenderer {
    private JLabel label;

    public UserElementTreeCellRenderer() {
        label = new JLabel();
    }

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

        return label;
    }
}

