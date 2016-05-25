/*
 * Program: Poker
 * This: player.java
 * Date: 10/28/2015
 * Author: P. Schmitt, Z. Smith, B. Gerber, L. Knussman
 * Purpose: A class to instantiate an object representing a poker player
 */
package poker;
import java.util.Scanner;
public class Player 
{
    private int chips;
    private String name;
    private Hand hand;
    private boolean roundWin;

    //===========================Player================================
    public Player()
    {
        
    }
    
    //=============================Player==================================
    public Player(int chips, String name)
    {
        this.chips = chips;
        this.name = name;
    }
    
    //=======================setChips=============================
    public void setChips(int chips)
    {
        this.chips = chips;
    }
    
    //=============================getChips==========================
    public int getChips()
    {
        return chips;
    }

    //=======================getName=============================
    public String getName() 
    {
        return name;
    }

    //==================setName========================
    public void setName(String name) 
    {
        this.name = name;
    }
    
    //=======================getHand==========================
    public Hand getHand()
    {
        return hand;
    }
    
    //=========================setHand======================
    public void setHand(Hand hand)
    {
        this.hand = hand;
    }
    
    //=====================getRoundWin=============================
    public boolean getRoundWin() {
        return roundWin;
    }

    //=====================setRoundWin=============================
    public void setRoundWin(boolean roundWin) {
        this.roundWin = roundWin;
    }
    
    //===========================addChips=======================
    public void addChips()
    {
        int chipsToAdd, totalChips;
        System.out.println(getName()+", how many chips would you like to add?");
        chipsToAdd = Utilities.getIntInput();
        while(chipsToAdd<=0)
        {
            System.out.println(" Please enter an amount greater than 0!");
            chipsToAdd = Utilities.getIntInput();
        }
        totalChips = getChips() + chipsToAdd;
        setChips(totalChips);
    }
    
    //===========================placeBet===========================
    public int placeBet()
    {
        int bet;
        System.out.println(getName() + ", place your bet.  You currently have " + getChips() + " chips:");
        bet = Utilities.getIntInput();
        while(bet>getChips() || bet <=0)
        {
            System.out.println("Please enter a valid amount!");
            bet = Utilities.getIntInput();
        }
        
        return bet;
    }
    
    //========================createPlayer=============================
    public static Player createPlayer()
    {
        Scanner input = new Scanner(System.in);
        Player newPlayer = new Player();
        System.out.println("Enter player name: ");
        String name = input.next();
        newPlayer.setName(name);
        newPlayer.addChips(); 
        return newPlayer;
    }
    
}
