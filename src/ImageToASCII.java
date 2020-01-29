import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;


public class ImageToASCII {

    public ImageToASCII() {

    }


    public static BufferedImage scale(BufferedImage bufferedImage, int maxDimension) {
        double w = bufferedImage.getWidth();
        double h = bufferedImage.getHeight();
        double sf = 0;
        if (w > h) {
            sf = maxDimension / w;
        } else {
            sf = maxDimension / h;
        }
        int scaledWidth = (int)(w * sf);
        int scaledHeight = (int)(h * sf);

        Image tmp = bufferedImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    public String[] convert(BufferedImage img, int maxDimension) {                                                              // Each element in array is a line of text to print
        BufferedImage bufferedImage = scale(img, maxDimension);
        StringBuilder[] imageInASCII = new StringBuilder[bufferedImage.getHeight()];
        for (int i = 0; i < imageInASCII.length; i++) {
            imageInASCII[i] = new StringBuilder();
        }

        ColorSpace colorSpace = ColorSpace.getInstance(ColorSpace.CS_GRAY);                                             // Convert to grayscale
        ColorConvertOp colorConvertOp = new ColorConvertOp(colorSpace, null);
        bufferedImage = colorConvertOp.filter(bufferedImage, null);

        int meanColor;
        Color color;
        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                if (y + 1 <= bufferedImage.getHeight() && x + 1 <= bufferedImage.getWidth()) {
                    meanColor = 0;
                    color = new Color(bufferedImage.getRGB(x, y));
                    meanColor += color.getBlue();
                    imageInASCII[y].append(colorToChar(meanColor));
                }
            }
        }

        String[] asciiImage = new String[bufferedImage.getHeight()];
        for (int i = 0; i < asciiImage.length; ++i) {
            asciiImage[i] = imageInASCII[i].toString();
        }
        return asciiImage;
    }

    private char colorToChar(int color) {                                                                               // Program currently limited by # characters used
        String chars = "@#8&o*/\'. ";
        return chars.charAt(color/(255/chars.length() + 1));
//        if (color < 25) {                                                                                               // I eyeballed densities - next step is to calculate densities
//            return '@';                                                                                                 // And utilize more ascii characters
//        } else if (color >= 25 && color < 51) {
//            return '#';
//        } else if (color >= 51 && color < 76) {
//            return '8';
//        } else if (color >= 76 && color < 102) {
//            return '&';
//        } else if (color >= 102 && color < 127) {
//            return 'o';
//        } else if (color >= 127 && color < 153) {
//            return '*';
//        } else if (color >= 153 && color < 178) {
//            return '/';
//        } else if (color >= 178 && color < 204) {
//            return '\'';
//        } else if (color >= 204 && color < 229) {
//            return '.';
//        }
//        return ' ';
    }


}

