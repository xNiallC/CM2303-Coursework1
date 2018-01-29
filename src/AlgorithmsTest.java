import java.util.Arrays;
import java.util.Collections;

/**
 * Created by niall on 29/01/2018.
 */
public class AlgorithmsTest {
    public static void main(String[] args) {
        runtimeTest("counting", "worst", 1, 10, 10, 0, 100);
    }
    public static void runtimeTest(String algorithmType, String dataType, int nRangeLowerBound, int nRangeUpperBound, int nDataPoints, int minValue, int maxValue) {
        // Code for calculating our intervals of N to test
        int valuesOfN[] = new int[nDataPoints];
        int nInterval = ((nRangeUpperBound - nRangeLowerBound) / (nDataPoints - 1));
        int currentValue = nRangeLowerBound;

        for(int i = 0; i < valuesOfN.length; i++) {
            valuesOfN[i] = currentValue;
            currentValue += nInterval;
        }

        System.out.println(Arrays.toString(valuesOfN));

        // Apply Insertion sort with parameters
        if(algorithmType == "insertion") {
            for(int i = 0; i < valuesOfN.length; i++) {
                int testArray[] = ArrayGenerator.generateArray(valuesOfN[i], minValue, maxValue);

                // Best case for insertion is if array is already sorted, so we use a ready made java function to sort before testing
                if(dataType == "best") {
                    Arrays.sort(testArray);
                }

                // Worst case for insertion is if array is reverse sorted, so we sort it then reverse manually
                if(dataType == "worst") {
                    Arrays.sort(testArray);
                    for(int j = 0; j < testArray.length / 2; j++)
                    {
                        int temp = testArray[j];
                        testArray[j] = testArray[testArray.length - j - 1];
                        testArray[testArray.length - j - 1] = temp;
                    }
                }
                System.out.println(Arrays.toString(testArray));
                int outputArray[] = SortingAlgorithms.insertionSort(testArray, valuesOfN[i]);
                System.out.println(Arrays.toString(outputArray));
            }
        }

        // Apply counting sort with parameters
        else if(algorithmType == "counting") {
            for(int i = 0; i < valuesOfN.length; i++) {
                int testArray[];
                int outputToChangeArray[] = new int[valuesOfN[i]];
                // An example worst case scenario identified as k = n^2, so for each n we just square it to get the max value
                if(dataType == "worst") {
                    testArray = ArrayGenerator.generateArray(valuesOfN[i], minValue, (valuesOfN[i] * valuesOfN[i]));
                }
                // Else we just generate randomly
                else {
                    testArray = ArrayGenerator.generateArray(valuesOfN[i], minValue, maxValue);
                }
                System.out.println(Arrays.toString(testArray));

                // Calculate max value
                int arrayMaxValue = SortingAlgorithms.determineMaxValue(testArray);

                // Apply counting sort
                int outputArray[] = SortingAlgorithms.countingSort(testArray, outputToChangeArray, arrayMaxValue);
                System.out.println(Arrays.toString(outputArray));
            }
        }
    }
}