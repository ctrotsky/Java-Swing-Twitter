/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Colin
 */
public abstract class TwitterForm extends JFrame implements ActionListener {
    
        /*
    * FUNCTION: buttonLayout()
    *
    * Modifies a button with the specified parameters and a hardcoded style.
    */
    protected void buttonLayout(JButton b, int posX, int posY, int width, int height, JPanel panel) {
        b.setBounds(posX, posY, width, height);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setVerticalTextPosition(JButton.CENTER);
        b.setMargin(new Insets(0, 0, 0, 0));
        b.setBackground(new Color(98, 190, 253));
        b.setForeground(Color.WHITE);
        b.setBorderPainted(true);
        b.setFocusPainted(false);
        b.setContentAreaFilled(false);
        b.setOpaque(true);
        b.setFont(new Font("SANS_SERIF", Font.BOLD, 12));

        b.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(b, new Color(79, 184, 255));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(b, new Color(98, 190, 253));
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setBackground(b, new Color(251, 150, 82));
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                setBackground(b, new Color(251, 150, 82));
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBackground(b, new Color(98, 190, 253));
            }

            void setBackground(JButton b, Color c) {
                if (b.isEnabled()) {
                    b.setBackground(c);
                }
            }
        ;
        });
        b.addActionListener(this);
        panel.add(b);
    }
    
    /*
    * FUNCTION: panelLayout()
    *
    * Modifies a panel with specified parameters and hardcoded style.
    */
    protected void panelLayout(JPanel p, int posX, int posY, int width, int height) {
        p.setBounds(posX, posY, width, height);
        p.setLayout(null);
        p.setBackground(Color.LIGHT_GRAY);
        p.setOpaque(true);
        p.setBorder(BorderFactory.createBevelBorder(1));
        add(p);
    }
    
    /*
    * FUNCTION: treeLayout()
    *
    * Modifies a panel with specified parameters and hardcoded style.
    */
    protected void treeLayout(JTree t, int posX, int posY, int width, int height) {
        t.setBounds(posX, posY, width, height);
        t.setLayout(null);
        t.setBackground(Color.WHITE);
        t.setOpaque(true);
        t.setBorder(BorderFactory.createLineBorder(new Color(98, 190, 253)));
    }
    
    /*
    * FUNCTION: titleLayout()
    *
    * Modifies a panel with specified parameters and hardcoded style.
    */
    protected void titleLayout(JLabel l, int posX, int posY, int width, int height) {
        l.setHorizontalAlignment(SwingConstants.CENTER);
        l.setFont(new Font("SANS_SERIF", Font.BOLD, 16));
        l.setBounds(posX,posY,width,height);
    }
    
    
}
