/**
 * Created by kamil on 02.04.2017.
 */
public class NewGameSettings {

    private String levelFilePath;
    private Difficulty difficulty;
    private String playerName;
    int score;

    public NewGameSettings(String levelFilePath, Difficulty difficulty, String playerName){
        this.levelFilePath = levelFilePath;
        this.difficulty = difficulty;
        this.playerName = playerName;
        score = 0;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getLevelFilePath() {
        return levelFilePath;
    }

    public String getPlayerName() {
        return playerName;
    }
}
