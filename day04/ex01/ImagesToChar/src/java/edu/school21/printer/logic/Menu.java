package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Menu {
    private static String path;
    private char[][] images;
    private char[] imageChar;

    public void run(String args1, String args2) {
        parsePath(args1, args2);
        if (parseFormat()) {
            createCharArr();
            print();
        } else {
            System.err.println("Incorrect format");
            System.exit(-1);
        }
    }

    private void parsePath(String args1, String args2) {
        imageChar = new char[2];
        imageChar[0] = args1.toCharArray()[0];
        imageChar[1] = args1.toCharArray()[1];
        path = args2;
    }

    private void print() {
        for (int i = 0; i < images.length; i++) {
            for (int j = 0; j < images[i].length; j++) {
                System.out.print(images[i][j]);
            }
            System.out.println();
        }
    }

    private void createCharArr() {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            int height = image.getHeight();
            int width = image.getWidth();
            images = new char[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Color color = new Color(image.getRGB(j, i));
                    int brightness = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                    images[i][j] = (brightness > 128) ? imageChar[0] : imageChar[1];
                }
            }

        } catch (IOException e) {
            System.out.println("error :" + e.getMessage());
        }
    }

    private Boolean parseFormat() {
        File file = new File(path);
        System.out.println(path);
        boolean format = path.substring(path.lastIndexOf('.') + 1).equals("bmp");
        return format && file.exists();
    }
}
