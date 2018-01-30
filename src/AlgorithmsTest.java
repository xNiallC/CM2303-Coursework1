import java.util.Arrays;
/**
 * Created by niall on 29/01/2018.
 */
public class AlgorithmsTest {
    public static void main(String[] args) {
        runtimeTest("insertion", "average", 1000, 10000, 10, 0, 100, false);
    }
    public static void runtimeTest(String algorithmType, String dataType, int nRangeLowerBound, int nRangeUpperBound, int nDataPoints, int minValue, int maxValue, boolean showArrays) {
        // Code for calculating our intervals of N to test
        int valuesOfN[] = new int[nDataPoints];
        int nInterval = ((nRangeUpperBound - nRangeLowerBound) / (nDataPoints - 1));
        int currentValue = nRangeLowerBound;

        for(int i = 0; i < valuesOfN.length; i++) {
            valuesOfN[i] = currentValue;
            currentValue += nInterval;
        }

        System.out.println(Arrays.toString(valuesOfN));

        SortingAlgorithms sort = new SortingAlgorithms();

        // Fixing anomalous results for first run of algorithm --->
        // JVM uses a compilation theory known as JIT (Just In Time)
        // So when I call the algorithms for the first time, the compiler has to compile the algorithm upon call
        // This skewed the results so the first array would take significantly longer, as much as 3x in testing
        // Here I basically 'warm up' the compiler by completing some dummy tasks so everything is accurate upon testing
        long hackyStartTime = System.nanoTime();
        int hackyArray[] = ArrayGenerator.generateArray(10, minValue, maxValue);
        sort.insertionSort(hackyArray, 10);
        int hackyOutputToChangeArray[] = new int[10];
        sort.countingSort(hackyArray, hackyOutputToChangeArray, 100);
        System.out.println(System.nanoTime() - hackyStartTime);
        System.out.println("-------");
        //

        // Apply Insertion sort with parameters
        if(algorithmType == "insertion") {
            for(int i = 0; i < valuesOfN.length; i++) {
                int totalTime = 0;

                // Do each N 100 times and average for accuracy
                for(int k = 0; k < 100; k++) {
                    int testArray[] = ArrayGenerator.generateArray(valuesOfN[i], minValue, maxValue);

                    // Best case for insertion is if array is already sorted, so we use a ready made java function to sort before testing
                    if (dataType == "best") {
                        Arrays.sort(testArray);
                    }

                    // Worst case for insertion is if array is reverse sorted, so we sort it then reverse manually
                    if (dataType == "worst") {
                        Arrays.sort(testArray);
                        for (int j = 0; j < testArray.length / 2; j++) {
                            int temp = testArray[j];
                            testArray[j] = testArray[testArray.length - j - 1];
                            testArray[testArray.length - j - 1] = temp;
                        }
                    }

                    if (showArrays) {
                        System.out.println(Arrays.toString(testArray));
                    }

                    long startTime = System.nanoTime();
                    //Take start time of algorithm

                    int outputArray[] = sort.insertionSort(testArray, valuesOfN[i]);

                    //Compute total time
                    totalTime += (System.nanoTime() - startTime);

                    if (showArrays) {
                        System.out.println(Arrays.toString(outputArray));
                    }
                }

                totalTime = totalTime / 100;

                System.out.println(totalTime);
            }
        }

        // Apply counting sort with parameters
        else if(algorithmType == "counting") {
            for(int i = 0; i < valuesOfN.length; i++) {
                int totalTime = 0;

                // Do each N 100 times and average for accuracy
                for(int k = 0; k < 100; k++) {
                    sort.countingSort(hackyArray, hackyOutputToChangeArray, 100);
                    int testArray[];
                    int outputToChangeArray[] = new int[valuesOfN[i]];

                    // An example worst case scenario identified as k = n^2, so for each n we just square it to get the max value
                    if (dataType == "worst") {
                        testArray = ArrayGenerator.generateArray(valuesOfN[i], minValue, (valuesOfN[i] * valuesOfN[i]));
                    }
                    // Else we just generate randomly
                    else {
                        testArray = ArrayGenerator.generateArray(valuesOfN[i], minValue, maxValue);
                    }

                    if (showArrays) {
                        System.out.println(Arrays.toString(testArray));
                    }

                    // Calculate max value
                    int arrayMaxValue = SortingAlgorithms.determineMaxValue(testArray);

                    long startTime = System.nanoTime();
                    //Take start time of algorithm

                    // Apply counting sort
                    int outputArray[] = sort.countingSort(testArray, outputToChangeArray, arrayMaxValue);

                    //Compute total time
                    totalTime += (System.nanoTime() - startTime);

                    if (showArrays) {
                        System.out.println(Arrays.toString(outputArray));
                    }
                }

                totalTime = totalTime / 100;

                System.out.println(totalTime);
            }
        }
    }
}