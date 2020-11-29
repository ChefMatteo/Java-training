import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {


        long startTime = System.currentTimeMillis();
        BufferedImage inputImage = null;
        try {
            inputImage = ImageIO.read(new File("gris.png")); //caricamento immagine
        } catch (IOException e) {
        }

        BufferedImage outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_4BYTE_ABGR); // creazione immagine output

        Job job1 = new Job(inputImage, outputImage, 0, inputImage.getHeight() / 4);
        Job job2 = new Job(inputImage, outputImage, inputImage.getHeight() / 4, (inputImage.getHeight() / 4) * 2);
        Job job3 = new Job(inputImage, outputImage, (inputImage.getHeight() / 4) * 2, (inputImage.getHeight() / 4) * 3);
        Job job4 = new Job(inputImage, outputImage, (inputImage.getHeight() / 4) * 3, inputImage.getHeight());

        job1.start();
        job2.start();
        job3.start();
        job4.start();

        try {
            job1.join();
            job2.join();
            job3.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            ImageIO.write(outputImage, "png", new File("outputImage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
