/**
 * Represents a WebScraper that gets tweets from
 * 
 *
 * @author Noah Krueger and Shardul Joshi
 * @version 1
 */

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class WebScraper
{
    private Tweet[] tweets;
    private String url;
    public WebScraper()
    {
        this("https://twitter.com/realDonaldTrump"); //default twitter page
    }
    public WebScraper(String u)
    {
        if(!u.equals("") && u.contains("twitter")) //making sure the url is not null and is a twitter link
        {
            url = u;
        }
        else
        {
            url = "https://twitter.com/realDonaldTrump";
        }
    }
    public void getTweets(int n)
    {
        tweets = new Tweet[n]; //creates an array of objects (tweets)
        try
        {
            URL twitter = new URL(url); //establishes the url
            URLConnection yc = twitter.openConnection(); //connects to twitter
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine = "";
            String html = "";
            while ((inputLine = in.readLine()) != null) //copys over the entire html to a string
            {
                html+=inputLine + "\n";
            }
            in.close(); //closes scanner
            int endIndex = 0;
            int startIndex = 0;
            String text, timePosted, likes; //the data we are extracting
            String startTarget;
            int startTargetLen;
            for (int i = 0; i < n; i++) //does this for each tweet object
            {
                startTarget = "data-long-form=\"true\" aria-hidden=\"true\">"; //targets right before the time posted
                startTargetLen = startTarget.length(); //used to add to get the exact spot before the time
                startIndex = html.indexOf(startTarget, endIndex);
                endIndex = html.indexOf("<", startIndex); //closest closing tag
                timePosted = html.substring(startIndex + startTargetLen, endIndex);//stores the extracted string
                
                startTarget = "<p class=\"TweetTextSize TweetTextSize--normal js-tweet-text tweet-text\" lang=\"en\" data-aria-label-part=\"0\">";
                startTargetLen = startTarget.length(); //same logic as abouve
                startIndex = html.indexOf(startTarget, endIndex) + startTargetLen;
                endIndex = html.indexOf("</p>", startIndex);
                text = html.substring(startIndex, endIndex);
                int gunkIndex1, gunkIndex2;
                //tweet text can have links and stuff, so remove that
                while (text.indexOf("<") != -1)
                {
                    gunkIndex1 = text.indexOf("<");
                    gunkIndex2 = text.indexOf(">") + 1;
                    text = text.substring(0, gunkIndex1) + text.substring(gunkIndex2);
                }
                int nextSpace, nextPeriod;
                // tweets can also be very long, so to make them more readable, this splits it every 100 characters
                for (int j = 50; text.length() > 50 && j < text.length(); j += 50)
                {
                    nextSpace = text.indexOf(' ',j);
                    nextPeriod = text.indexOf('.',j);
                    if (nextSpace == -1 && nextPeriod == -1)
                        break;
                    else
                        j = (nextSpace < nextPeriod && nextSpace != -1) ? nextSpace : nextPeriod;
                    text = text.substring(0,j) + "\n" + text.substring(j + 1);
                }
                
                startTarget = "<span class=\"ProfileTweet-action--favorite u-hiddenVisually\">";
                startTargetLen = startTarget.length(); //same logic as above
                startIndex = html.indexOf(startTarget, endIndex) + startTargetLen;
                startTarget = "data-aria-label-part>";
                startTargetLen = startTarget.length();
                startIndex = html.indexOf(startTarget, startIndex) + startTargetLen;
                endIndex = html.indexOf("<", startIndex);
                likes = html.substring(startIndex, endIndex);
                
                tweets[i] = new Tweet(text, timePosted, likes); //encapsulates all information into a object
            }
        }
        catch(FileNotFoundException ex) //catches unknown exception
        {
            System.out.println("Unknown");
        }
        catch(MalformedURLException e) //cathches bad url exception
        {
            System.out.println("Badly formed url exception occurred");
        }
        catch(IOException e) //catches io exceptions
        {
            System.out.println("IO exception occurred");
        }
    }
    public String toString()
    {
        String returnThis = "------------------------------------------------------------------------------\n";
        for (int i = 0; i < tweets.length; i++) //returns the information about the string utilizing weet tostring
        {
            returnThis += tweets[i].toString() + "\n------------------------------------------------------------------------------\n";
        }
        return returnThis;
    }
}
