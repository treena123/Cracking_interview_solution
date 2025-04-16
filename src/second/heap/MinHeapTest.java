package second.heap;

/**
 * @author trinapal
 */
public class MinHeapTest {
    public static void main(String[] args) {
        MinHeap h = new MinHeap(11);
        h.insertKey(3);
        h.insertKey(2);
        h.deleteKey(1);
        h.insertKey(15);
        h.insertKey(5);
        h.insertKey(4);
        h.insertKey(45);
        System.out.print(h.extractMin() + " ");
        System.out.print(h.getMin() + " ");

        h.decreaseKey(2, 1);
        System.out.print(h.getMin());

        h.changeValueOnAKey(5, 30);
    }
}

class MinHeap{
    //to store array in heap
    int [] heapArr;
    // max heap size
    int maxHeapCapacity;
    //current Heap Size
    int currentHeapSize;

    MinHeap(int n){
        maxHeapCapacity =n;
        heapArr = new int[n];
        currentHeapSize = 0;
    }

    //get parent i.e. root

    private int parent(int key){
        return (key-1)/2;
    }

    private int leftChild(int key){
        return 2* key +1;
    }

    private int rightChild(int key){
        return 2*key + 2;
    }

    public boolean insertKey(int key) {

        //check if heap is full
        if(maxHeapCapacity == currentHeapSize){
            return false;
        }
        int i = currentHeapSize;
        heapArr[i] = key;
        currentHeapSize++;
        
        //adjust the heap
        while(i!= 0 && heapArr[i] < heapArr[parent(i)]){
            swap(heapArr, i,parent(i));
            i = parent(i); //why this is needed*
        }
        return true;
    }

    public void swap(int [] a, int key, int parent){
        int temp = a[parent];
        a[parent] = a[key];
        a[key] = temp;
    }

    //method to remove the min value
    public int extractMin() {
        if (currentHeapSize <= 0) {
            return Integer.MAX_VALUE;
        }

        if (currentHeapSize == 1) {
            currentHeapSize--;
            return heapArr[0];
        }

        // Store the minimum value, 
        // and remove it from heap 
        int root = heapArr[0];

        heapArr[0] = heapArr[currentHeapSize - 1];
        currentHeapSize--;
        minHeapify(0);

        return root;
    }

    private void minHeapify(int key) {
        int l = leftChild(key);
        int r = rightChild(key);

        int smallest = key;
        if (l < currentHeapSize && heapArr[l] < heapArr[smallest]) {
            smallest = l;
        }
        if (r < currentHeapSize && heapArr[r] < heapArr[smallest]) {
            smallest = r;
        }

        if (smallest != key) {
            swap(heapArr, key, smallest);
            minHeapify(smallest);
        }
    }

    public int getMin() {
        return heapArr[0];
    }

    public void deleteKey(int key) {
        decreaseKey(key, Integer.MIN_VALUE);
        extractMin();
    }

    public void decreaseKey(int oldKey, int value) {
        heapArr[oldKey] = value;

        //adjust heap
        while(oldKey != 0 && heapArr[oldKey] < heapArr[parent(oldKey)]){
            swap(heapArr, oldKey, parent(oldKey));
            oldKey = parent(oldKey);
        }
    }

    // Increases value of given key to new_val.
    // It is assumed that new_val is greater
    // than heapArray[key].
    // Heapify from the given key
    public void increaseKey(int key, int new_val) {
        heapArr[key] = new_val;
        minHeapify(key);
    }
    // Changes value on a key
    public void changeValueOnAKey(int key, int new_val) {
        if (heapArr[key] == new_val) {
            return;
        }
        if (heapArr[key] < new_val) {
            increaseKey(key, new_val);
        } else {
            decreaseKey(key, new_val);
        }
    }
}
