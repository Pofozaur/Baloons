import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by kamil on 02.04.2017.
 */
public class BaloonFileReader {

    private BaloonContainer bc = null;
    private String fileName;
    private int visibleRows;
    private int allRows;
    private int columns;
    private BaloonsList visibleBaloons;
    private BaloonsList hiddenBaloons;
    int mapRows;

    BaloonFileReader(String fileName){
        this.fileName = fileName;
    }

    public BaloonsList getVisibleBaloons() {
        return visibleBaloons;
    }

    public BaloonsList getHiddenBaloons() {
        return hiddenBaloons;
    }

    public int getAllRows() {
        return allRows;
    }

    public int getVisibleRows() {
        return visibleRows;
    }

    public int getColumns() {
        return columns;
    }

    public int getMapRows() {
        return mapRows;
    }

    void load(){
        try{
        Scanner scanner = new Scanner(new File(fileName));
        columns = scanner.nextInt();
        visibleRows = scanner.nextInt();
        allRows = scanner.nextInt();
        mapRows = scanner.nextInt();

        System.out.print(columns + " " + visibleRows + " " + allRows);

        visibleBaloons = new BaloonsList();
        hiddenBaloons = new BaloonsList();
        for(int i = 0; i < visibleRows; i++){
            BaloonRow br = new BaloonRow();
            if(i%2 == 0) {
                for (int j = 0; j < columns; j++){
                    br.add(new Baloon(BaloonType.values()[scanner.nextInt()], new Point(j,i), false));
                }
            }
            else{
                for (int j = 0; j < columns-1; j++){
                    br.add(new Baloon(BaloonType.values()[scanner.nextInt()], new Point(j,i), true));
                }
            }
            visibleBaloons.add(br);
        }


            for(int i = 0; i < allRows-visibleRows; i++){
                BaloonRow br = new BaloonRow();
                if(i%2 == 1) {
                    for (int j = 0; j < columns; j++){
                        br.add(new Baloon(BaloonType.values()[scanner.nextInt()], new Point(j,(-1)*(i+1)), true));
                    }
                }
                else{
                    for (int j = 0; j < columns-1; j++){
                        br.add(new Baloon(BaloonType.values()[scanner.nextInt()], new Point(j,(-1)*(i+1)), false));
                    }
                }
               hiddenBaloons.add(br);
            }



        scanner.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        BaloonFileReader bfr = new BaloonFileReader("level1.level");
        bfr.load();

        for(BaloonRow be : bfr.visibleBaloons)
            for(Baloon b : be)
                System.out.println(b);

        for(BaloonRow be : bfr.hiddenBaloons)
            for(Baloon b : be)
                System.out.println(b);
    }
}
