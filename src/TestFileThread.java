import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;

/**
 * Created by kamil on 30.03.2017.
 */
public class TestFileThread implements Runnable {

    public void run() {

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("testFile.txt"));

            System.out.println("FileThread");
            TimeUnit.SECONDS.sleep(6);
            System.out.println("WokeUp");
            System.out.println(bufferedReader.readLine());

            while (true) {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("LOOP");
            }
        } catch (Exception e) {
            System.out.println("Failed to open file.");
            return;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                    System.out.print("ClosingFile");
                } catch (Exception e) {
                    System.out.println("FailedToCloseFile");
                }
            }
        }

    }
}
