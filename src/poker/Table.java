/*
 * Program: Poker
 * This: table.java
 * Date: 10/28/2015
 * Author: P. Schmitt, Z. Smith, B. Gerber, L. Knussman
 * Purpose: A class to instantiate an object representing a table on which poker
            is played on.  It handles the main functions of a poker game.
 */
package poker;

//=====================Table=============================
public class Table 
{
   private static Player player1;
   private static Player player2;
   private static int mainMenuChoice;
   private static int discardMenuChoice;
   private static int playerMenuChoice;
   private static int pot;
   private static final Deck deckOfCards = new Deck();
   private final static int HAND_SIZE = 5;
   
   //=======================Table==========================
    public Table()
    {
        
    }
    
    //=====================play=============================
    public void play()
    {
        player1 = Player.createPlayer();
        player2 = Player.createPlayer();
        
        do{
           tableMenu();
           mainMenuChoice = Utilities.getIntInput();
           if(mainMenuChoice == 1)
           {
               playRound();
           }
           
           else if(mainMenuChoice ==2)
           {
               playerMenu();
               playerMenuChoice = Utilities.getIntInput();
               if(playerMenuChoice == 1)
               {
                   player1.addChips();
               }
               else if(playerMenuChoice == 2)
               {
                   player2.addChips();
               }
               else
               {
                   System.out.println("Player doesn't exist");
               }
           }
           else if(mainMenuChoice==3)
           {
               displayChips();
           }
        }while(mainMenuChoice!=0);
    }
    
    //=====================tableMenu===========================
    private static void tableMenu()
    {
        System.out.println("1. Play a hand");
        System.out.println("2. Add funds");
        System.out.println("3. Display chip amounts");
        System.out.println("-----------------------");
        System.out.println("0. Quit");
    }
    
    //====================playRound=========================
    private static void playRound()
    {
        if(player1.getChips()<=0 || player2.getChips()<=0)
        {
            System.out.println("Please verify that both players have chips!");
        }
        else
        {
        deckOfCards.initializeDeck();//load cards into deck
        deckOfCards.shuffleDeck();//shuffle the deck
        int player1Bet = player1.placeBet();//get bets from players
        int player2Bet = player2.placeBet();
        pot = player1Bet+player2Bet;
        player1.setChips(player1.getChips()-player1Bet);
        player2.setChips(player2.getChips()-player2Bet);
        
        initialDeal();//deal 5 cards to each player
        playHand(player1);//asks the user if they want to exchange cards
        playHand(player2);
        System.out.println(player1.getName() + "'s Hand result: " + Hand.analyzeHand(player1.getHand()));
        System.out.println(player2.getName() + "'s Hand result: " + Hand.analyzeHand(player2.getHand())); 

        
        if(player1.getHand().getScore()>player2.getHand().getScore())
        {
            player1.setRoundWin(true);
            player2.setRoundWin(false);
        }
        else if(player1.getHand().getScore()<player2.getHand().getScore())
        {
            player2.setRoundWin(true);
            player1.setRoundWin(false);
        }
        else if (player1.getHand().getScore()==player2.getHand().getScore())
        {
            breakTie();
        }
        else
        {
            
        }
        
        if(player1.getRoundWin())
        {
            System.out.println(player1.getName() + " wins!");
            player1.setChips(player1.getChips()+pot);
        }
        
        else if(player2.getRoundWin())
        {
            System.out.println(player2.getName() + " wins!");
            player2.setChips(player2.getChips()+pot);
        }
        
        else 
        {
            System.out.println("It's a draw! The pot is split!");
        }
        }

    }
    
    //=======================playHand==================================
    private static void playHand(Player player)
    {
        System.out.println(player.getName() +"'s Hand: ");
        player.getHand().printHand();
        discardMenu();
        discardMenuChoice = Utilities.getIntInput();
        while(discardMenuChoice!=1 && discardMenuChoice!=2)
        {
            System.out.println("Please enter a valid option!");
            discardMenuChoice = Utilities.getIntInput();
        }
        if(discardMenuChoice ==1 )
        {
            player.getHand().exchangeCards(deckOfCards);
        }
        player.getHand().printHand();
    }
    
    //=======================intitialDay=================================
    private static void initialDeal()
    {
        Hand hand1 = new Hand();
        Hand hand2 = new Hand();
        for (int index = 0; index < HAND_SIZE; index++)
            {
                hand1.addCard(deckOfCards.deal());
                hand2.addCard(deckOfCards.deal());
            }
        player1.setHand(hand1);
        player2.setHand(hand2); 
    }
    
    //=======================breakTie======================
    private static void breakTie() //returns the player that won the round.
    {
         
        if(player1.getHand().getIDCard().rank()>player2.getHand().getIDCard().rank())
        {
            player1.setRoundWin(true);
            player2.setRoundWin(false);
        }
        else if(player1.getHand().getIDCard().rank()<player2.getHand().getIDCard().rank())
        {
            player2.setRoundWin(true);
            player1.setRoundWin(false);
        }
        else if(player1.getHand().getIDCard().rank()==player2.getHand().getIDCard().rank())
        {
            if(player1.getHand().getKicker().rank()>player2.getHand().getKicker().rank())
            {
                player1.setRoundWin(true);
                player2.setRoundWin(false);
            }
            else if(player1.getHand().getKicker().rank()<player2.getHand().getKicker().rank())
            {
                player2.setRoundWin(true);
                player1.setRoundWin(false);
            }
            else
            {
                player1.setRoundWin(true);
                player2.setRoundWin(true);
            }
        }
        else
        {
            player1.setRoundWin(true);
            player2.setRoundWin(true);
        }
       
    }
    
    //===============================discardMenu============================
    private static void discardMenu()
    {
        System.out.println("Would you like to exchange any cards? You can replace up to 3!");
        System.out.println("1. Exchange cards");
        System.out.println("2. Play current hand");
        
    }
    
    //=======================playerMenu=============================
    private static void playerMenu()
    {
        System.out.println("1. " + player1.getName());
        System.out.println("2. " + player2.getName());
    }

    //============================displayChips=========================
    private void displayChips() 
    {
        System.out.println(player1.getName() + "'s chips: " + player1.getChips());
        System.out.println(player2.getName() + "'s chips: " + player2.getChips());
    }
}
