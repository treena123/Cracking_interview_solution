package first.mock1;

import java.util.concurrent.*;
import java.util.List;
import java.util.Arrays;

public class ConcurrentWordCount {
    public static void main(String[] args) throws InterruptedException {
        // Sample list of words
        List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple", "banana", "orange");

        // Create a ConcurrentHashMap to store word counts
        ConcurrentHashMap<String, Integer> wordCountMap = new ConcurrentHashMap<>();

        // Create a thread pool to process the words concurrently
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // Divide the list of words into chunks and assign each chunk to a thread
        int chunkSize = words.size() / 4;
        for (int i = 0; i < 4; i++) {
            final int start = i * chunkSize;
            final int end = (i + 1) * chunkSize;
            executorService.submit(new WordCountTask(words, start, end, wordCountMap));
        }

        // Shut down the executor service and wait for all tasks to complete
        executorService.shutdown();
        if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
            executorService.shutdownNow();
        }

        // Print the word counts
        System.out.println("Word Counts: " + wordCountMap);
    }
}

class WordCountTask implements Runnable {
    private final List<String> words;
    private final int start;
    private final int end;
    private final ConcurrentHashMap<String, Integer> wordCountMap;

    public WordCountTask(List<String> words, int start, int end, ConcurrentHashMap<String, Integer> wordCountMap) {
        this.words = words;
        this.start = start;
        this.end = end;
        this.wordCountMap = wordCountMap;
    }

    @Override
    public void run() {
        // Process the words in the assigned chunk and update the word count map
        for (int i = start; i < end; i++) {
            String word = words.get(i);
            wordCountMap.merge(word, 1, Integer::sum); // Atomically increment the count
        }
    }
}
