/*
 * Program: Poker
 * This: card.java
 * Date: 10/28/2015
 * Author: P. Schmitt, Z. Smith, B. Gerber, L. Knussman
 * Purpose: A class to instantiate an object representing a playing card,
            having both a suit and a rank.
 */
package poker;

//================class Card========================
public class Card 
{
    public final static int DIAMOND = 1;
    public final static int CLUB = 2;
    public final static int HEART = 3;
    public final static int SPADE = 4;
    private int cardSuit;
    private int cardRank;
    private final String[] SUIT = {null, "♦ (Diamonds)", "♣ (Clubs)", "♥ (Hearts)", "♠ (Spades)"};
    private final String[] RANK = {null, null, "2", "3", "4", "5", "6", "7", 
                                    "8", "9", "10", "J", "Q", "K", "A"};
    
    //===============Card=====================
    public Card(int suit, int rank)//default constructor sets rank and suit
    {
        if (rank == 1)
        {
            this.cardRank = 14; 
        }
        else
        {
            this.cardRank = rank;
        }
        
        this.cardSuit = suit;
    }
   
    //=================rank=======================
    public int rank()//returns card rank
    {
        return this.cardRank;
    }
    
    //================suit=========================
    public int suit()//returns card suit
    {
        return this.cardSuit;
    }
    
    public String suitToString(int index)
    {  
        return SUIT[index];
    }
    
    public String rankToString(int index)
    {
        return RANK[index];
    }
    
    //================cardToString=================
    public String cardToString()//returns rank and suit as a string
    {
        String cardString = RANK[this.cardRank] +" of " + SUIT [this.cardSuit];
        return cardString;
    }
}
