/*
 * Program: Poker
 * This: deck.java
 * Date: 10/28/2015
 * Author: P. Schmitt, Z. Smith, B. Gerber, L. Knussman
 * Purpose: A class to instantiate an object that represents a hand of cards,
            consisting of 5 Card objects for use in poker.
 */
package poker;

import java.util.ArrayList;
import java.util.Scanner;

//=======================class Card=====================================
public class Hand
{
    private ArrayList<Card> hand = new ArrayList<>(5);
    private int score; //implement score to compare hands
    final int DISCARD_MAX = 3;
    final int HAND_SIZE = 5;
    private  Card identCard;
    private  Card kicker;
    
    //========================Hand==========================
    public Hand()
    {
        
    }
    
    //======================addCard========================
    public void addCard(Card card)//adds card to the list
    {
        hand.add(card);
    }
    
    //======================getHand===========================
    public ArrayList<Card> getHand()//allows access to the list
    {
        return hand;
    }
    
    //=========================getScore========================
    public int getScore()
    {
        return score;
    }
    public Card getKicker()
    {
        return kicker;
    }
    
    public Card getIDCard()
    {
        return identCard;
    }
    //======================printHand=============================
    public void printHand()
    { 
        
       System.out.println("Your hand: ");
         {
             for (int index = 0; index < HAND_SIZE; index ++)
             {
                 System.out.println ((index+1) + ". " + hand.get(index).cardToString());           
             }
         }
        System.out.println();
        
    }
    
    //=======================exchangeCards============================
    public void exchangeCards(Deck deckOfCards)
    {
        Scanner input = new Scanner(System.in);
        int discardChoice, discardNum;
        ArrayList<Integer> discardList = new ArrayList<>();
        System.out.print("How many cards would you like to exchange? ");
        discardNum = Utilities.getIntInput();
        while (discardNum > DISCARD_MAX || discardNum <0)
        {
            System.out.println("You can only exchange 3 cards. ");
            System.out.print("How many cards would you like to exchange? ");
            discardNum = Utilities.getIntInput();
        }
        while (discardNum > 0) //&& discardNum<=4)
        {
            if(discardList.isEmpty())
            {
            System.out.print("Enter the number next to the card to discard and get a new one: ");
         
            discardChoice = Utilities.getIntInput();
            while(discardChoice>5 || discardChoice<=0)
            {
                System.out.println("Not a valid card.  Try again.");
                discardChoice = Utilities.getIntInput();
            }
            getHand().set(discardChoice -1 , deckOfCards.deal());
            discardList.add(discardChoice);
            discardNum--;
            }
            else
            {
            System.out.print("Enter the number next to the card to discard and get a new one: ");
            discardChoice = Utilities.getIntInput();
            while(discardChoice>5 || discardChoice<=0)
            {
                System.out.println("Not a valid card.  Try again.");
                discardChoice = Utilities.getIntInput();
            }
            for(int index = 0; index<discardList.size(); index++)
            {
                while(discardChoice==discardList.get(index))
                {
                    System.out.println("You've already exchanged that card. Please select another");
                    discardChoice = Utilities.getIntInput();
                }
            }
            getHand().set(discardChoice -1 , deckOfCards.deal());
            discardList.add(discardChoice);
            discardNum--;   
            }
        }
    }
    
    //=======================sortByRank=========================
    public void sortByRank()
    {
        for (int index1 = 0 ; index1 < hand.size() ; index1++ )
      {
         int min = index1;
         for (int index2 = (index1 + 1); index2 < hand.size(); index2++ )
         {
            if ( hand.get(index2).rank() < hand.get(min).rank() )
            {
               min = index2;    
            }
         }
         Card sort = hand.get(index1);
         hand.set(index1, hand.get(min));
         hand.set(min, sort);
      }
    }
    
    //===========================sortBySuit=========================
    public void sortBySuit()
    {
        for (int index1 = 0 ; index1 < hand.size() ; index1++ )
      {
         int min = index1;
         for (int index2 = (index1 + 1); index2 < hand.size(); index2++ )
         {
            if ( hand.get(index2).suit() < hand.get(min).suit())
            {
               min = index2;    
            }
         }
         Card sort = hand.get(index1);
         hand.set(index1, hand.get(min));
         hand.set(min, sort);
      }
    }
    
    //==================isFlush============================
    public static boolean isFlush(Hand myHand)//sorts by suit, if first pos.suit = last pos.suit, flush is true
   {
       myHand.sortBySuit();
       boolean isFlush =false;
       if(myHand.getHand().get(0).suit() == (myHand.getHand().get(4).suit()))
       {
           isFlush = true;
           myHand.identCard = myHand.getHand().get(4);
           myHand.kicker = myHand.getHand().get(4);
       }
       return isFlush;
   }
    
    //=================isStraight===================
    public static boolean isStraight(Hand myHand)//sorts by rank and tests for 5 consecutive cards
    {
        myHand.sortByRank();
        int nextRank = myHand.getHand().get(0).rank() + 1;
        boolean isStraight = true;
        
        if(myHand.getHand().get(4).rank()== 14) // special cases for straights containing an Ace
        {
            if(myHand.getHand().get(0).rank() == 2 && myHand.getHand().get(1).rank() == 3 &&
               myHand.getHand().get(2).rank() == 4 && myHand.getHand().get(3).rank() == 5)
            {
                isStraight = true;
                myHand.identCard = myHand.getHand().get(0);
                
            }
            
            else if (myHand.getHand().get(0).rank() == 10 && myHand.getHand().get(1).rank() == 11 &&
               myHand.getHand().get(2).rank() == 12 && myHand.getHand().get(3).rank() == 13)
            {
                isStraight = true;
                myHand.identCard = myHand.getHand().get(4);
            }
            
            else
            {
                isStraight = false;
            }
            
        }
        
        else //standard case for a straight
        {
           for (int index = 1; index < 5; index++)
            {
                if ( myHand.getHand().get(index).rank() != nextRank)
                {
                    isStraight = false;
                }
            nextRank++;
            }
           myHand.identCard = myHand.getHand().get(4);
        }
        return isStraight;
    }
    
    //=================isFourofKind====================
    public static boolean isFourofKind(Hand myHand) // checks for 2 possible 4 of a kind combos after sorting by rank.
    {
        myHand.sortByRank();
        boolean fourKind = false; 
        if (myHand.getHand().get(0).rank() == myHand.getHand().get(1).rank()
                && myHand.getHand().get(1).rank() == myHand.getHand().get(2).rank()
                && myHand.getHand().get(2).rank() == myHand.getHand().get(3).rank())
        {
            fourKind = true;
            myHand.identCard = myHand.getHand().get(0);
            myHand.kicker = myHand.getHand().get(4);
        }
        else if(myHand.getHand().get(1).rank() == myHand.getHand().get(2).rank()
                && myHand.getHand().get(2).rank() == myHand.getHand().get(3).rank()
                && myHand.getHand().get(3).rank() == myHand.getHand().get(4).rank())
        {
            fourKind = true;
            myHand.identCard = myHand.getHand().get(1);
            myHand.kicker = myHand.getHand().get(0);
        }
        
        return fourKind;
    }
    
    //=================isFullHouse=====================
    public static boolean isFullHouse(Hand myHand)//checks for 2 possible combinations of a full house
    {
        myHand.sortByRank();
        boolean fullHouse = false;  
        if(myHand.getHand().get(0).rank() == myHand.getHand().get(1).rank()
                && myHand.getHand().get(1).rank() == myHand.getHand().get(2).rank()
                && myHand.getHand().get(3).rank() == myHand.getHand().get(4).rank())
        {
            fullHouse = true;
            myHand.identCard = myHand.getHand().get(2);
        }
        else if(myHand.getHand().get(0).rank() == myHand.getHand().get(1).rank()
                && myHand.getHand().get(2).rank() == myHand.getHand().get(3).rank()
                && myHand.getHand().get(3).rank() == myHand.getHand().get(4).rank())
        {
            fullHouse = true;
            myHand.identCard = myHand.getHand().get(4);
        }
        
        return fullHouse;
    }
    
     //================isThreeOfKind=================
    public static boolean isThreeOfKind(Hand myHand)//check if fourofKind or fullHouse first, then 3 of a kind
    {
        boolean threeKind = false;
        if (isFourofKind(myHand) || isFullHouse(myHand))
        {
            threeKind = false;
        }
        
        else 
        {
            myHand.sortByRank();
            if (myHand.getHand().get(0).rank() == myHand.getHand().get(1).rank() 
                    && myHand.getHand().get(1).rank() == myHand.getHand().get(2).rank())
            {
                threeKind = true;
                myHand.identCard = myHand.getHand().get(0);
                myHand.kicker = myHand.getHand().get(4);
            }
            
            else if(myHand.getHand().get(1).rank() == myHand.getHand().get(2).rank() 
                    && myHand.getHand().get(2).rank() == myHand.getHand().get(3).rank())
            {
                threeKind = true;
                myHand.identCard = myHand.getHand().get(1);
                myHand.kicker = myHand.getHand().get(4);
            }
            
            else if(myHand.getHand().get(2).rank() == myHand.getHand().get(3).rank() 
                    && myHand.getHand().get(3).rank() == myHand.getHand().get(4).rank())
            {
                threeKind = true;
                myHand.identCard = myHand.getHand().get(2);
                myHand.kicker = myHand.getHand().get(1);
            }
            
            else 
            {
                threeKind = false;
            }
        }
        
        return threeKind;
    }
    
    //=======================isTwoPair========================
    public static boolean isTwoPair(Hand myHand)//checks for 2 pair
    {
        boolean twoPair = false;
        if (isFourofKind(myHand) || isFullHouse(myHand) || isThreeOfKind(myHand))
        {
            twoPair = false;
        }
        
        else
        {
            myHand.sortByRank();
            
            if (myHand.getHand().get(0).rank() == myHand.getHand().get(1).rank() 
                    && myHand.getHand().get(2).rank() == myHand.getHand().get(3).rank())
            {
                twoPair = true;
                myHand.identCard = myHand.getHand().get(3);
                myHand.kicker = myHand.getHand().get(4);
            }
            
            else if (myHand.getHand().get(0).rank() == myHand.getHand().get(1).rank() 
                    && myHand.getHand().get(3).rank() == myHand.getHand().get(4).rank())
            {
                twoPair = true;
                myHand.identCard = myHand.getHand().get(4);
                myHand.kicker = myHand.getHand().get(2);
            }
            
            else if (myHand.getHand().get(1).rank() == myHand.getHand().get(2).rank() 
                    && myHand.getHand().get(3).rank() == myHand.getHand().get(4).rank())
            {
                twoPair = true;
                myHand.identCard = myHand.getHand().get(4);
                myHand.kicker = myHand.getHand().get(0);
            }
            
            else
            {
                twoPair = false;
            }
        }
        
        return twoPair;
        
    }
    
    //=====================isPair=======================
    public static boolean isPair(Hand myHand)//checks for a pair
    {
        boolean pair = false;
        if(isFourofKind(myHand) || isThreeOfKind(myHand) || isFullHouse(myHand) || isTwoPair(myHand))
        {
           pair = false;
        }
        else
        {
           if (myHand.getHand().get(0).rank() == myHand.getHand().get(1).rank())
           {
               pair = true;
               myHand.identCard = myHand.getHand().get(0);
               myHand.kicker = myHand.getHand().get(4);
           }
           
           else if (myHand.getHand().get(1).rank() == myHand.getHand().get(2).rank())
           {
               pair = true;
               myHand.identCard = myHand.getHand().get(1);
               myHand.kicker = myHand.getHand().get(4);
           }
           
           else if (myHand.getHand().get(2).rank() == myHand.getHand().get(3).rank())
           {
               pair = true;
               myHand.identCard = myHand.getHand().get(2);
               myHand.kicker = myHand.getHand().get(4);
           }
           
           else if (myHand.getHand().get(3).rank() == myHand.getHand().get(4).rank())
           {
               pair = true;
               myHand.identCard = myHand.getHand().get(3);
               myHand.kicker = myHand.getHand().get(2);
           }
           
           else 
           {
               pair = false;
           }     
        }
        return pair;
    }
    
    //====================analyzeHand==============================
    public static String analyzeHand(Hand myHand)//use if-else to "move down" the heirarchy of poker hands
    {
        String result;
        if(isFlush(myHand) && isStraight(myHand) && myHand.getHand().get(0).rank()==10)
        {
            myHand.score = 1000;
            result = "Your hand contains a royal flush! ";
        }
        else if (isFlush(myHand) && isStraight(myHand))
        {
            myHand.score = 900;
            result = "Your hand contains a straight flush! (" 
                    + myHand.identCard.rankToString(myHand.identCard.rank()) 
                    +" was the high card)" ;
        }
        else if (isFourofKind(myHand))
        {
            myHand.score = 800;
            result = "Your hand contains four of a kind! (" 
                    + myHand.identCard.rankToString(myHand.identCard.rank()) +"'s)" ;
        }
        else if (isFullHouse(myHand))
        {
            myHand.score = 700;
            result = "Your hand contains a full house!( 3" 
                    +  myHand.identCard.rankToString(myHand.identCard.rank()) 
                    +"'s)" ;
        }
        else if (isFlush(myHand))
        {
            myHand.score = 600;
            result = "Your hand contains a flush!(" 
                    + myHand.identCard.suitToString(myHand.identCard.suit()) +")" ;
        }
        else if (isStraight(myHand))
        {
            myHand.score = 500;
            result = "Your hand contains a straight!(" 
                    + myHand.identCard.rankToString(myHand.identCard.rank()) 
                    +" was the high card)" ;
        }
        else if (isThreeOfKind(myHand))
        {
            myHand.score = 400;
            result = "Your hand contains three of a kind!(" 
                    + myHand.identCard.rankToString(myHand.identCard.rank()) +"'s)" ;
        }
        else if (isTwoPair(myHand))
        {
            myHand.score = 300;
            result = "Your hand contains two pairs!(" 
                    + myHand.identCard.rankToString(myHand.identCard.rank())
                    +"'s was the high pair)" ;
        }
        else if (isPair(myHand))
        {
            myHand.score = 200;
            result = "Your hand contains a pair!(" 
                    + myHand.identCard.rankToString(myHand.identCard.rank()) +"'s)" ;
        }
        else
        {
            myHand.score = 100;
            myHand.identCard=myHand.getHand().get(4);
            myHand.kicker = myHand.getHand().get(3);
            result = "Your hand contains no valid poker combinations!(" 
                    +  myHand.identCard.rankToString(myHand.identCard.rank()) 
                    +" was the high card)" ;
            
        }
        return result;
    }
    
    
}
