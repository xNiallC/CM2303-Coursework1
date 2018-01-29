import java.util.Random;
public class ArrayGenerator {
    // Generate a new array of random numbers with the specified requirements
    public static int[] generateArray(int length, int lowerBound, int upperBound) {
        Random rand = new Random();
        int newArray[] = new int[length];
        for(int i = 0; i<length; i++) {
            newArray[i] = rand.nextInt(upperBound) + lowerBound;
        }
        return newArray;
    }
}
