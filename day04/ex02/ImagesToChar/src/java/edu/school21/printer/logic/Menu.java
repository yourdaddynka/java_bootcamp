package edu.school21.printer.logic;

import com.beust.jcommander.JCommander;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;


public class Menu {
    private final String fileDerict = "/Users/letishal/Desktop/TEST.bmp";
    private String[] colorWB;


    public void run(String[] args) {
        parseFormat(args);
        colorPrint();
    }

    private void parseFormat(String[] args) {
        try {
            ParseArgs parseArgs = new ParseArgs();
            JCommander.newBuilder().addObject(parseArgs).build().parse(args);
            colorWB = new String[]{parseArgs.getColorWrite(), parseArgs.getColorBlack()};
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void colorPrint() {
        try {
            BufferedImage image = ImageIO.read(new File(fileDerict));
            ColoredPrinter coloredPrinter = new ColoredPrinter();
            int height = image.getHeight();
            int width = image.getWidth();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Color color = new Color(image.getRGB(j, i));
                    int brightness = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                    if (brightness > 128) {
                        coloredPrinter.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(colorWB[0]));
                    } else {
                        coloredPrinter.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(colorWB[1]));
                    }
                }
                System.out.println();
            }

        } catch (IOException e) {
            System.out.println("error :" + e.getMessage());
        }
    }
}
