/**
 * Represents a Tweet
 *
 * @author Noah Krueger and Shardul Joshi
 * @version 1
 */
public class Tweet
{
    private String text, timePosted, likes;
    public Tweet()
    {
        this("none","never","0");
    }
    public Tweet(String t, String tp, String l)
    {
        text = t;
        timePosted = tp;
        likes = l;
    }
    public String toString()
    {
        return "a tweet";
    }
}