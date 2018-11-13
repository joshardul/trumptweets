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
            int endIndex = 0;
            int startIndex = 0;
            String text, timePosted, likes;
            for (int i = 0; i < n; i++)
            {
                String startTarget = "data-long-form=\"true\" aria-hidden=\"true\">";
                int startTargetLen = startTarget.length();
                startIndex = html.indexOf(startTarget, endIndex);
                endIndex = html.indexOf("<", startIndex);
                timePosted = html.substring(startIndex + startTargetLen, endIndex);
                
                endIndex = startIndex;
                startTarget = "<p class=\"TweetTextSize TweetTextSize--normal js-tweet-text tweet-text\" lang=\"en\" data-aria-label-part=\"0\">";
                startTargetLen = startTarget.length();
                startIndex = html.indexOf(startTarget, endIndex);
                System.out.println(html.substring(startIndex, startIndex + 50));
                endIndex = html.indexOf("<", startIndex);
                text = html.substring(startIndex, endIndex);
                
                /*startTarget = "<span class=\"ProfileTweet-actionCount\" data-tweet-stat-count=\"";
                startTargetLen = startTarget.length();
                startIndex = html.indexOf(startTarget, endIndex);
                endIndex = html.indexOf("\">", startIndex);
                likes = html.substring(startIndex, endIndex);*/
                
                System.out.println(timePosted);
                System.out.println(text);
                //System.out.println(likes);
                //tweets[i] = new Tweet(text, timePosted, likes);
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
