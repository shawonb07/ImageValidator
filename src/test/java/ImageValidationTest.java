import org.testng.annotations.Test;

import java.io.File;

public class ImageValidationTest {

    @Test
    public void testImageComparison() {
        File fileA = new File("src/sampleFiles/image1.png");
        File fileB = new File("src/sampleFiles/image3.png");

        ImageValidator.imageCompareResult(fileA, fileB);
    }
}
