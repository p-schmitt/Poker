/*
 * Program: Poker
 * This: poker.java
 * Date: 10/28/2015
 * Author: P. Schmitt, Z. Smith, B. Gerber, L. Knussman
 * Purpose: A program that allows two "players" to play 5 card draw poker 
            against each other.
 */
package poker;

//===========================class Poker============================
public class Poker {

    public static void main(String[] args) 
    {
        Table table = new Table();
        table.play();    
    }
}
