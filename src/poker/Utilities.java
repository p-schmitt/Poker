/*
 * Program: Poker
 * This: utilities.java
 * Date: 10/28/2015
 * Author: P. Schmitt, Z. Smith, B. Gerber, L. Knussman
 * Purpose: Utilities class to handle input
 */
package poker;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilities 
{
   //================================getMenuInput============================
    public static int getIntInput()//method to get user input for various menus
    {
        Scanner input = new Scanner(System.in);
        int menuChoice;
        boolean continueInput = true;
        
        do{
            try{
                System.out.print("Enter a  choice: ");
                menuChoice = input.nextInt();
                continueInput = false;
                return menuChoice;
            }
            catch(InputMismatchException ex)
            {
                System.out.println("Invalid choice. Select again!");
                input.nextLine();
            }
        }while(continueInput);
        return -1;
    }
}
