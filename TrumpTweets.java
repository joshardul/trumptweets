/**
 * Get yourself a dosage of Trump
 *
 * @author Noah Krueger and Shardul Joshi
 * @version 1
 */

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TrumpTweets
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int numTweets;
        System.out.println("Welcome to TrumpTweets");
        do
        {
            System.out.println("How many of Donald Trump's tweets would you like to see? (1 - 3)");
            try
            {
                numTweets = input.nextInt();
            }
            catch(Exception InputMismatchException)//makes sure to catch the exeption and not break the program
            {
                System.out.println("Please enter a valid integer input betweem 1 and 3.");
                numTweets = 0;
                input.next();
            }
        }
        while (numTweets > 3 || numTweets < 1);
        WebScraper w = new WebScraper("https://twitter.com/realDonaldTrump");//creates webscraper object
        w.getTweets(numTweets);//uses webscraper function
        System.out.println(w);//utilizes tostring 
        System.out.println("Thanks for using TrumpTweets! See you for your next Trump dosage soon.");
    }
}
