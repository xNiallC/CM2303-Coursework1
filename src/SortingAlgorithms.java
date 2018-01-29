import java.util.Arrays;

public class SortingAlgorithms {
    public static int[] insertionSort(int inputArray[], int inputSize) {
        for(int i = 1; i < inputSize; i++) {
            int item = inputArray[i];
            int j = i - 1;
            // Sift through the list from i - 1 until an element smaller than i is found
            while ((j > -1) && (inputArray[j] > item)) {
                // For each item larger than the current, shift forward in the list
                inputArray[j+1] = inputArray[j];
                j--;
            }
            // J is smaller than current item and we shifted all larger elements up, so we place the current item in J+1
            inputArray[j+1] = item;
        }
        return inputArray;
    }

    public static int[] countingSort(int inputArray[], int outputArray[], int maxValue) {
        // Create counting array, where we store the count of each value in input array
        int countArray[] = new int[maxValue + 1];

        // The Java 7 ':' (For each) operator allows us to cleanly count each time a number appears
        // Without having to manually look for unique numbers and then finding if it exists other times
        for(int i : inputArray) {
            countArray[i]++;
        }

        // Keep track of what element in the output we are at
        int currentOutputIndex = 0;

        // For each element in the counting array
        for(int i= 0; i < countArray.length; i++) {
            int currentCount = countArray[i];

            // We look at the number of times the item appeared in the input
            for(int j = 0; j < currentCount; j++) {
                // Then add it to our output, and increase our index
                outputArray[currentOutputIndex] = i;
                currentOutputIndex++;
            }
        }
        return outputArray;
    }
    public static int determineMaxValue(int inputArray[]) {
        // We do this outside the scope of our algorithm to prevent it taking up valuable CPU time
        int maxValue = inputArray[0];
        for (int i = 1; i < inputArray.length; i++) {
            if (inputArray[i] > maxValue) {
                maxValue = inputArray[i];
            }
        }
        return maxValue;
    }
}