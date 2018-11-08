/**
 * Gets Donald Trumps tweets
 *
 * @author Noah Krueger and Shardul Joshi
 * @version 1
 */

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class WebScraper
{
    public static Tweet[] getTweets(int n)
    {
        Tweet[] tweets = new Tweet[n];
        String twitterPage = "https://twitter.com/realDonaldTrump";
        try
        {
            URL twitter = new URL(twitterPage);
            URLConnection yc = twitter.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine = "";
            String html = "";
            while ((inputLine = in.readLine()) != null)
            {
                html+=inputLine + "\n";
            }
            in.close();
            int endIndex = html.indexOf("<div id=\"timeline\"");
            int startIndex;
            String text,datePosted,likes;
            for (int i = 0; i < n; i++)
            {
                startIndex = html.indexOf("<span aria-hidden", endIndex);
                startIndex = html.indexOf("<span class=\"u-hiddenVisually", startIndex);
                startIndex = html.indexOf(">", startIndex);
                endIndex = html.indexOf("<", startIndex);
                datePosted = html.substring(startIndex+1, endIndex);
                System.out.println(datePosted);
                
                startIndex = html.indexOf("<div class=\"js-tweet-text-container", endIndex);
                endIndex = html.indexOf("</div>", startIndex);
                
                tweets[i] = new Tweet();
            }
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("unknown");
            return null;
        }
        catch(MalformedURLException e)
        {
            System.out.println("badly formed url exception occurred");
            return null;
        }
        catch(IOException e)
        {
            System.out.println("IO exception occurred");
            return null;
        }  
        return tweets;
    }
}
