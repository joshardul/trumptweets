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
            System.out.println("How many of Donald Trump's tweets would you like to see? (1 - 10)");
            numTweets = input.nextInt();
            WebScraper.getTweets(numTweets);
        }
        while (numTweets > 10 || numTweets < 1);
    }
}
