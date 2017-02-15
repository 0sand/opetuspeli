/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sand.count21.logiikka;

/**
 * This class takes care of the betting and paying of the bet in the blackjack
 * game.
 *
 * @author osand
 */
public class BetManager {

    private Game game;
    private Player player;
    private int bet;
    private double blackJackPayoutFactor;
    private int normalWinPayoutFactor;

    /**
     * The betManager constructor makes a new betmanager using the provided game 
     * and the player of the game.
     * @param game blackjack game that links to the betmanager
     */
    public BetManager(Game game) {
        this.game = game;
        this.player = game.getPlayer();
        this.bet = 0;

        this.blackJackPayoutFactor = 1.5;
        this.normalWinPayoutFactor = 1;

    }

    public double getBlackJackPayoutFactor() {
        return blackJackPayoutFactor;
    }

    public int getNormalWinPayoutFactor() {
        return normalWinPayoutFactor;
    }

    public int getBet() {
        return this.bet;
    }

    /**
     * This method is used to set the current bet.
     *
     * @param bet increase the current bet
     */
    public void increseBet(int bet) {
        if (this.player.getMoney() - bet >= 0) {
            this.bet = this.bet + bet;
            this.player.deductMoney(bet);

        }

    }

    /**
     * This method zeros the current bet.
     */
    public void zeroBet() {
        this.bet = 0;
    }

    /**
     * This method gets the current bet;
     *
     * @return the current bet.
     */
    /**
     * This method pays the bet to the player if he won and zeros the current
     * bet.
     */
    public void payBetToPlayer() {
        int payout = 0;

        if (this.game.checkForBlackjack(this.player) && !this.game.checkForBlackjack(game.getDealer())) {
            payout = (int) Math.round(this.blackJackPayoutFactor * this.bet);
            this.player.reciveMoney(this.bet + payout);
        
        } else if (this.game.checkForBlackjack(this.player) && this.game.checkForBlackjack(game.getDealer())) {
            this.player.reciveMoney(this.bet);
            

        } else if (this.game.didPlayerWin()) {
            payout = this.bet * this.normalWinPayoutFactor;
            this.player.reciveMoney(this.bet + payout);
        }
        this.zeroBet();

    }
}
