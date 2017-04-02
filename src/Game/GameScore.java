package Game;

import java.util.regex.*;

/**
 * Created by kamil on 02.04.2017.
 */
public class GameScore implements Comparable{

    private String playerName;
    private int score;

    public GameScore(String playerName, int score){
        this.playerName = playerName;
        this.score = score;
    }

    public GameScore(String line){
        Pattern namePattern = Pattern.compile("^(.*)\\s");
        Pattern scorePattern = Pattern.compile("\\d+$");
        Matcher nameMatcher = namePattern.matcher(line);
        Matcher scoreMatcher = scorePattern.matcher(line);
        nameMatcher.find();
        playerName = nameMatcher.group(1);
        scoreMatcher.find();
        System.out.println(scoreMatcher.group());
        score = Integer.parseInt(scoreMatcher.group());

    }

    public int getScore() {return score;}
    public String getPlayerName(){return playerName;}
    @Override
    public int compareTo(Object o) {
        GameScore comparedTo = (GameScore)o;
        if(score > comparedTo.getScore())
            return -1;
        else if(score < comparedTo.getScore())
            return 1;
        else
            return 0;
    }
    public String toString(){
        return playerName + " " + score;
    }

    public static void main(String[] args){
        System.out.println(new GameScore("Adam4 40"));
    }
}
