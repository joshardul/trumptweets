/**
 * @(#)urlConnectionWiki.java
 *
 * This is a proof of concept program that tells the user the birthday of whatever celebrity they are intersted in
 * It uses Wikipedia to find the information.
 *
 * Updates to make: Accept single names,
 * rewrite the date string into a nicer format, if the Wikipedia page doesn't exist for the normal format, try alternate format.
 * Rewrite as a class, use a GUI to instantiate.
 *
 * @author Alison DiBenedetto
 *
 * @version 1.00 2015/11/1
 */

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class urlConnectionwiki {
        
    public static void main(String[] args)  {
    
        Scanner reader = new Scanner(System.in);
        String celeb="";
              
          while (true){
          
            try{
          
            System.out.println("Who is your favorite celebrity? (type quit if done)");
            celeb = reader.nextLine();
            celeb=celeb.trim();
            if(celeb.equals("quit"))
                    break;
     
            int spaceIndex = celeb.indexOf(" ");
     
            if(spaceIndex>-1)
            {
      
                String firstName = celeb.substring(0, spaceIndex).trim();
                String lastName = celeb.substring(spaceIndex+1).trim();
          
                char c = Character.toUpperCase(firstName.charAt(0));
                firstName = c + firstName.substring(1);
                c = Character.toUpperCase(lastName.charAt(0));
                lastName = c + lastName.substring(1);
                    
                String wikiPage = "https://twitter.com/realDonaldTrump?ref_src=twsrc%5Eappleosx%7Ctwcamp%5Esafari%7Ctwgr%5Eprofile";
                URL wiki = new URL(wikiPage);
                URLConnection yc = wiki.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(
                                yc.getInputStream()));
    
                String inputLine;
                String line="";                     
    
                while ((inputLine = in.readLine()) != null)
                    line+=inputLine;
    
                in.close();
                int pos=0;
                    
                pos = line.indexOf("bday", pos+1);
                if(pos>-1)
                {
                 int endbdayIndex = line.indexOf("</span>", pos+1);
                    String bday = line.substring(pos+6, endbdayIndex);
                    System.out.println("born: " + bday);                
                }
                else
                System.out.println("We don't know");
            }
            else
                System.out.println("We need a first and last name");
          }
          catch(FileNotFoundException ex)
          {
            System.out.println("unknown");
          }
          catch(MalformedURLException e)
          {
            System.out.println("badly formed url exception occurred");
            return;
          }
           catch(IOException e)
          {
            System.out.println("IO exception occurred");
            return;
          }  
        }     
    }//endmain
}//endclass