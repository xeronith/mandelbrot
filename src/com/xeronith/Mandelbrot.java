package com.xeronith;

import java.awt.Color;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Mandelbrot {

    public static void main(String[] args) throws Exception {
        int width = 1000, height = 1000, iterationMaximum = 100;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int[] colors = new int[iterationMaximum];
        for (int i = 0; i < iterationMaximum; i++) {
            colors[i] = Color.HSBtoRGB(i / 256f, 1, i / (i + 8f));
        }

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                double cReal = (column - width / 2) * 4.0 / width;
                double cImaginary = (row - height / 2) * 4.0 / width;
                double x = cReal, y = cImaginary;
                
                int iteration = 0;
                while (x * x + y * y < 4 && iteration < iterationMaximum) {
                    double newX = x * x - y * y -.8;
                    y = 2 * x * y + .156;
                    x = newX;
                    iteration++;
                }
                
                if (iteration < iterationMaximum) {
                    image.setRGB(column, row, colors[iteration]);
                } else {
                    image.setRGB(column, row, 0);
                }
            }
        }

        ImageIO.write(image, "png", new File("Mandelbrot.png"));
    }
}
