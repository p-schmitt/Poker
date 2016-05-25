/*
 * Program: Poker
 * This: deck.java
 * Date: 10/28/2015
 * Author: P. Schmitt, Z. Smith, B. Gerber, L. Knussman
 * Purpose: A class to instantiate an object representing a deck of 52 playing cards.  
            Contains methods to intialize a deck, deal and shuffle cards.  Also contains
            methods to analyze valid poker hands
 */
package poker;

import java.util.ArrayList;
import java.util.Collections;

//=======================class Deck========================
public class Deck 
{
    public final int NUM_OF_CARDS = 52;
    public final int NUM_OF_SUITS = 4;
    public final int NUM_OF_CARDS_SUIT = 13;
    public ArrayList<Card> deckOfCards;
    public int currCard;
    
    //==================Deck====================
    public Deck()//default constructor
    {
        
    }
    
    //==============initializeDeck==============
    public void initializeDeck()//creates a "new", unshuffled deck without needing a new deck object.
    {
        this.deckOfCards = new ArrayList<>();
        for (int suit = Card.DIAMOND; suit <=Card.SPADE; suit++)//loop through suits
        {
            for (int rank = 1; rank <= this.NUM_OF_CARDS_SUIT; rank++)//loop through each card rank
                    {
                        this.deckOfCards.add(new Card(suit, rank));
                    }
        }
        this.currCard = 0;
    }
    
    //==============deckToString==================
    public String deckToString() //used to ensure deck was initializing correctly, and shuffling. 
                                 //Not used in main program poker.java    
    {
        String deckString= "";
        int index3 = 0;
        
        for (int index1 = 0; index1 < this.NUM_OF_SUITS; index1++)
        {
            for (int index2 = 1; index2 <= this.NUM_OF_CARDS_SUIT; index2++)
            {
                deckString += this.deckOfCards.get(index3).cardToString() + " ";
            }
            deckString += "\n";
        }
        return deckString;
    }
    
    //================shuffleDeck=====================
    public void shuffleDeck()//swaps positions of cards in the array 10000 times to simulate shuffling.
    {
            Collections.shuffle(deckOfCards);
    }
    
    //================deal============================
    public Card deal()//deals card, sets value to null, increments deck array position.
    {
        Card tempCard = deckOfCards.get(0);
        deckOfCards.remove(0);
        return tempCard;
        
    }
   
    
}
