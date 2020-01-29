import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        ImageToASCII imageToASCII = new ImageToASCII();

        Scanner scan = new Scanner(System.in);
        System.out.print("Input the maximum desired dimension of your image: ");
        int maxDimension = scan.nextInt();
        String[] asciiImage = imageToASCII.convert(ImageIO.read(new File("Minesweeper.PNG")), maxDimension);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Minesweeper.txt"));
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