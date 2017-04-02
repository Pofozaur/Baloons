import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by kamil on 02.04.2017.
 */
public class ScoresContainer extends ArrayList<GameScore> {


    public void loadFromFile(){

        BufferedReader fileIn = null;
        try {

            FileReader fileReader = new FileReader("scores.txt");
            fileIn = new BufferedReader(fileReader);
            String line;
            while((line = fileIn.readLine()) != null) {
                System.out.println(line);
                add(new GameScore(line));
            }
            fileIn.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        finally {
            if(fileIn != null)
                try{
                    fileIn.close();
                }catch(IOException e){
                e.printStackTrace();
                }
        }
        Collections.sort(this);
        write();
    }
    public void saveToFile(){

        BufferedWriter fileOut = null;
        try {

            FileWriter fileWriter = new FileWriter("scores.txt", false);
            fileOut = new BufferedWriter(fileWriter);

            fileOut.write(this.toString());
            fileOut.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        finally {
            if(fileOut != null)
                try{
                    fileOut.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
        }

    }
    public void write(){
        for(GameScore g : this)
            System.out.println(g);
    }
    public String toString(){
        StringBuffer result = new StringBuffer();

        for(GameScore gameScore : this){
            result.append(gameScore);
            result.append("\n");
        }

        return result.toString();
    }

    public static void main(String[] args){
        ScoresContainer sc = new ScoresContainer();
        sc.loadFromFile();
        sc.add(new GameScore("DODANY1 10000"));
        sc.add(new GameScore("DODANY2 1"));
        sc.saveToFile();
    }
}
