import javax.imageio.ImageIO;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        ImageToASCII imageToASCII = new ImageToASCII();

        String[] asciiImage = imageToASCII.convert(ImageIO.read(new File("Joker.jpg")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Ascii.txt"));
        PrintWriter printWriter = new PrintWriter(bufferedWriter);
        for (int i = 0; i < asciiImage.length; i++) {
            if (asciiImage[i] != null) {
                printWriter.write(asciiImage[i]);
            }
            printWriter.write("\n");
        }
        printWriter.close();
    }
}