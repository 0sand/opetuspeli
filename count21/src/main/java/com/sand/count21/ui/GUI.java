package com.sand.count21.ui;

import com.sand.count21.logiikka.ImageGetter;
import com.sand.count21.logiikka.Card;
import com.sand.count21.logiikka.Game;
import com.sand.count21.logiikka.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * Grafic Interface for the blackjack game. At the moment quit ugly code and
 * needs a lot of work. But hey. The game works.
 * @author osand
 */
public class GUI extends JPanel {

    Timer timer;

    Game game;
    ImageGetter imageGetter = new ImageGetter();

    JPanel dealerPanel = new JPanel();
    JPanel playerPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JButton hitButton = new JButton();
    JButton stayButton = new JButton();
    JButton againButton = new JButton();
    JLabel dealerlabel = new JLabel();
    JLabel playerlabel = new JLabel();

    JLabel dealerCard2;
    JLabel dealerCard1;
    JLabel dealerCardHit;
    JLabel playerCard0;
    JLabel playerCard1;
    JLabel playerCardHit;

    public GUI(Game game) {
        this.game = game;
        timer = new Timer(100, new MyTimerActionListener());

        dealerPanel.setBackground(new Color(12, 112, 12));
        playerPanel.setBackground(new Color(12, 112, 12));
        bottomPanel.setBackground(new Color(12, 112, 12));
        bottomPanel.setLayout(new FlowLayout());
        hitButton.setText("Hit");
        hitButton.setPreferredSize(new Dimension(120, 80));
        hitButton.setEnabled(false);
        stayButton.setText("Stay");
        againButton.setEnabled(true);
        againButton.setPreferredSize(new Dimension(120, 80));
        againButton.setText("Deal");
        stayButton.setEnabled(false);
        stayButton.setPreferredSize(new Dimension(120, 80));
        bottomPanel.add(hitButton);

        hitButton.addActionListener(new HitButton());
        againButton.addActionListener(new AgainButton());
        stayButton.addActionListener(new StayButton());

        bottomPanel.add(stayButton);
        bottomPanel.add(againButton);
        dealerlabel.setText("  Dealer:  ");
        playerlabel.setText("  Player:  ");

        setLayout(new BorderLayout());
        add(dealerPanel, BorderLayout.NORTH);
        add(playerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

    }

    public void display() {
        JFrame myFrame = new JFrame("Blackjack");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setContentPane(this);
        myFrame.setPreferredSize(new Dimension(1700, 1000));
        myFrame.pack();
        myFrame.setVisible(true);

    }

    public class HitButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Player player = game.getPlayer();
            if (!game.checkIfPlayerIsBust(player)) {
                game.dealTo(player, 1);
                Card card = player.getLastCard();
                Image image = imageGetter.getCardImage(card);
                playerCardHit = new JLabel(new ImageIcon(image));
                playerPanel.add(playerCardHit);
                playerPanel.revalidate();
            }

            if (game.checkIfPlayerIsBust(player)) {
                hitButton.setEnabled(false);
            }

        }
    }

    public class AgainButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            againButton.setEnabled(false);
            stayButton.setEnabled(true);
            hitButton.setEnabled(true);
            game.everyOneFolds();

            playerPanel.removeAll();
            playerPanel.repaint();

            dealerPanel.removeAll();
            dealerPanel.repaint();

            game.firstCardsInRound();
            ArrayList<Card> cards = game.getPlayer().getCards();
            Card card1 = cards.get(0);
            Image image1 = imageGetter.getCardImage(card1);
            Card card2 = cards.get(1);
            Image image2 = imageGetter.getCardImage(card2);

            playerCard0 = new JLabel(new ImageIcon(image1));
            playerCard1 = new JLabel(new ImageIcon(image2));
            playerPanel.add(playerCard0);
            playerPanel.add(playerCard1);

            cards = game.getDealer().getCards();
            card1 = cards.get(0);
            image1 = imageGetter.getImageFromString("images/backcard.png");
            card2 = cards.get(1);
            image2 = imageGetter.getCardImage(card2);

            dealerCard1 = new JLabel(new ImageIcon(image1));
            dealerCard2 = new JLabel(new ImageIcon(image2));
            dealerPanel.add(dealerCard1);
            dealerPanel.add(dealerCard2);

            playerPanel.revalidate();
            dealerPanel.revalidate();

            Player player = game.getPlayer();
            if (game.checkIfPlayerHas21(player)) {
                hitButton.setEnabled(false);
            }

        }
    }

    public class StayButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            timer.start();

        }
    }

    class MyTimerActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            dealerPanel.removeAll();
            ArrayList<Card> cards = game.getDealer().getCards();
            Card card1 = cards.get(0);
            Card card2 = cards.get(1);     
            Image image1 = imageGetter.getCardImage(card1);
            Image image2 = imageGetter.getCardImage(card2);
            
            
            dealerCard1 = new JLabel(new ImageIcon(image1));
            dealerCard2 = new JLabel(new ImageIcon(image2));
            dealerPanel.add(dealerCard1);
            dealerPanel.add(dealerCard2);
            dealerPanel.revalidate();


            Player dealer = game.getDealer();

            while (game.checkIfDealerMustHit(dealer)) {

                game.dealTo(dealer, 1);
                Card card = dealer.getLastCard();
                Image image = imageGetter.getCardImage(card);
                dealerCardHit = new JLabel(new ImageIcon(image));
                dealerPanel.add(dealerCardHit);
                dealerPanel.revalidate();

            }
            againButton.setEnabled(true);
            hitButton.setEnabled(false);
            stayButton.setEnabled(false);
            timer.stop();
        }

    }
}
