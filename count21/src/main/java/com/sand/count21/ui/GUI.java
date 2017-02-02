package com.sand.count21.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GUI extends JPanel {

    JPanel dealerPanel = new JPanel();
    JPanel playerPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JButton hitbutton = new JButton();
    JButton staybutton = new JButton();
    JButton playagainbutton = new JButton();
    JLabel dealerlabel = new JLabel();
    JLabel playerlabel = new JLabel();

    JLabel dealercard0;
    JLabel dealercard1;

    public GUI() {

        dealerPanel.setBackground(new Color(12, 112, 12));
        playerPanel.setBackground(new Color(12, 112, 12));
        bottomPanel.setBackground(new Color(12, 112, 12));
        bottomPanel.setLayout(new FlowLayout());
        hitbutton.setText("Hit");
        hitbutton.setEnabled(true);
        staybutton.setText("Stay");
        playagainbutton.setEnabled(true);
        playagainbutton.setText("Again");
        staybutton.setEnabled(true);
        bottomPanel.add(hitbutton);
        bottomPanel.add(staybutton);
        bottomPanel.add(playagainbutton);
        dealerlabel.setText("  Dealer:  ");
        playerlabel.setText("  Player:  ");

        dealercard0 = new JLabel(new ImageIcon("01S.jpg"));
        dealercard1 = new JLabel(new ImageIcon("01S.jpg"));

        dealerPanel.add(dealercard0);
        dealerPanel.add(dealercard1);

        setLayout(new BorderLayout());
        add(dealerPanel, BorderLayout.NORTH);
        add(playerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

    }

    public void display() {
        JFrame myFrame = new JFrame("Blackjack");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setContentPane(this);
        myFrame.setPreferredSize(new Dimension(1000, 700));

        myFrame.pack();
        myFrame.setVisible(true);

    }
}
