package second.quickSelectOrSort;

/**
 * @author trinapal
 */
public class KthLargest {
    public static void main(String[] args) {
        int[] arr = {10, 4, 5, 8, 6, 11, 26};
        int k = 3;  // Find the 3rd largest element
        int low = 0;
        int high = arr.length - 1;

        // Adjust the call to pass k as is
        System.out.println("The " + k + "th largest element is: " + findKthLargestByQuickSelect(arr, low, high, k));
    }

    // Function to find the k-th largest element
    private static int findKthLargestByQuickSelect(int[] arr, int low, int high, int k) {
        // Calculate the index of the k-th largest element in the array
        int targetIndex = arr.length - k;

        if (low == high) {
            return arr[low]; // Base case: only one element
        }

        // Partition the array and get the pivot index
        int pivotIndex = partition(arr, low, high);

        // If the pivot is the target index, return the element at the pivot
        if (pivotIndex == targetIndex) {
            return arr[pivotIndex];
        } else if (pivotIndex < targetIndex) {
            // Search the right side of the array
            return findKthLargestByQuickSelect(arr, pivotIndex + 1, high, k);
        } else {
            // Search the left side of the array
            return findKthLargestByQuickSelect(arr, low, pivotIndex - 1, k);
        }
    }

    // Partition function to reorder the array around a pivot
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];  // Choose the last element as the pivot
        int i = low - 1;  // Pointer for the smaller element

        // Rearrange elements greater than pivot to the left and smaller to the right
        for (int j = low; j < high; j++) {
            if (arr[j] > pivot) {  // For finding the k-th largest element, use '>' comparison
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Place the pivot in the correct position
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        // Return the pivot index
        return i + 1;
    }
}
