import org.testng.Assert;
import org.testng.Reporter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageValidator {
    public static double compareImages(File fileA, File fileB) {
        System.out.println(
                "ImageValidator.compareImage(): Comparing images: " + fileA.getName() + " and " + fileB.getName()
        );
        Reporter.log(
                "ImageValidator.compareImage(): Comparing images: " + fileA.getName() + " and " + fileB.getName()
        );
        BufferedImage imgA = null;
        BufferedImage imgB = null;

        try {
            imgA = ImageIO.read(fileA);
            imgB = ImageIO.read(fileB);
        } catch (IOException e) {
            System.out.println(e);
        }

        assert imgA != null;
        int width1 = imgA.getWidth();
        int height1 = imgA.getHeight();

        assert imgB != null;
        if (width1 != imgB.getWidth() || height1 != imgB.getHeight()) {
            throw new IllegalArgumentException("Error: Images dimensions mismatch");
        }

        long difference = 0;

        for (int y = 0; y < height1; y++) {
            for (int x = 0; x < width1; x++) {
                int rgbA = imgA.getRGB(x, y);
                int rgbB = imgB.getRGB(x, y);

                difference += Math.abs(((rgbA >> 16) & 0xff) - ((rgbB >> 16) & 0xff));
                difference += Math.abs(((rgbA >> 8) & 0xff) - ((rgbB >> 8) & 0xff));
                difference += Math.abs((rgbA & 0xff) - (rgbB & 0xff));
            }
        }

        double total_pixels = width1 * height1 * 3;
        double percentage = (double) difference / total_pixels / 255 * 100;

        System.out.println("ImageValidator.compareImage(): Difference Percentage ---> " + percentage);
        Reporter.log("ImageValidator.compareImage(): Difference Percentage ---> " + percentage);

        return percentage;
    }

    public static boolean areImagesIdentical(File fileA, File fileB) {
        System.out.println("ImageValidator.areImagesIdentical(): Comparing images...");
        Reporter.log("ImageValidator.areImagesIdentical(): Comparing images...");
        double percentDifference = compareImages(fileA, fileB);

        if (percentDifference > 2) {
            System.out.println("Images are not identical.");
            Reporter.log("Images are not identical.");
            return false;
        } else {
            System.out.println("Images are identical.");
            Reporter.log("Images are identical.");
            return true;
        }
    }

    public static void imageCompareResult(File fileA, File fileB) {
        System.out.println("ImageValidator.imageCompareResult(): Comparing images...");
        Reporter.log("ImageValidator.imageCompareResult(): Comparing images...");
        boolean areImagesEqual = ImageValidator.areImagesIdentical(fileA, fileB);

        try {
            Assert.assertTrue(areImagesEqual);
            System.out.println("The test passed because the difference in percentage is less than 5.");
            Reporter.log("The test passed because the difference in percentage is less than 5.");
        } catch (AssertionError e) {
            System.out.println("The test failed because the difference in percentage is greater than 5.");
            Reporter.log("The test failed because the difference in percentage is greater than 5.");
            throw e;
        }
    }
}