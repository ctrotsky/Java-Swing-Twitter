/***************************************************************
* file: TwitterForm.java
* author: Colin Trotter
* class: CS 356 – Object-Oriented Design and Programming
*
* assignment: Assignment 2 - Twitter
* date last modified: 11/7/2016
*
* purpose: Abstract class representing a JFrame of the Twitter program. Provides methods to stylize
* JComponents in a unified style between different implementations of TwitterForm.
****************************************************************/ 
package twitter;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

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
    
    /*
    * FUNCTION: errorMessage()
    * 
    * Displays an error message with the given text.
    */
    public void errorMessage(String messageTitle, String messageText){
        JOptionPane.showMessageDialog(this, messageText, messageTitle, JOptionPane.ERROR_MESSAGE);
    }
    
}
