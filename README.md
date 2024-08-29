### Project Overview

#### Project Name
ImageValidator

#### Description
The `ImageValidator` project is a Java-based application designed to compare two images and determine their similarity. It calculates the percentage difference between two images and provides functionality to check if the images are identical based on a predefined threshold.

#### Features
- **Compare Images**: Calculate the percentage difference between two images.
- **Check Image Identity**: Determine if two images are identical based on a percentage difference threshold.
- **Logging and Reporting**: Log comparison results and report them using TestNG's `Reporter`.

#### Technologies Used
- **Java**: The primary programming language.
- **Maven**: Build automation tool.
- **TestNG**: Testing framework.
- **Apache Commons IO**: Utility library for IO operations.
- **Apache Commons Lang**: Utility library for Java.

#### Dependencies
- `commons-io:2.16.1`
- `commons-lang3:3.15.0`
- `testng:7.10.2`

#### Maven Configuration
The project uses Maven for dependency management and build automation. The `pom.xml` file includes the necessary dependencies and repositories.

#### Key Classes and Methods
- **ImageValidator**: Main class containing methods for image comparison.
    - `compareImages(File fileA, File fileB)`: Compares two images and returns the percentage difference.
    - `areImagesIdentical(File fileA, File fileB)`: Checks if two images are identical based on a percentage difference threshold.
    - `imageCompareResult(File fileA, File fileB)`: Compares two images and asserts their identity using TestNG.

#### Usage
1. **Compare Images**:
   ```java
   double difference = ImageValidator.compareImages(new File("path/to/image1.png"), new File("path/to/image2.png"));
   ```
2. **Check Image Identity**:
   ```java
   boolean identical = ImageValidator.areImagesIdentical(new File("path/to/image1.png"), new File("path/to/image2.png"));
   ```
3. **Image Comparison Result**:
   ```java
   ImageValidator.imageCompareResult(new File("path/to/image1.png"), new File("path/to/image2.png"));
   ```

#### Build and Test
- **Build**: Use Maven to build the project.
  ```sh
  mvn clean install
  ```
- **Test**: Run tests using Maven.
  ```sh
  mvn test
  ```