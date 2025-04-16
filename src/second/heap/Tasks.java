package second.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author trinapal
 */
public class Tasks {
    private int priority;
    private String description;

    public Tasks(int priority, String description){
        this.priority = priority;
        this.description = description;
    }
    public static void main(String[] args) {
        PriorityQueue<Tasks> tasks = new PriorityQueue<>(5, Comparator.comparingInt(e -> e.priority));
        tasks.add(new Tasks(5, "write code"));
        tasks.add(new Tasks(7, "release product"));
        tasks.add(new Tasks(1, "write spec"));
        tasks.add(new Tasks(3, "create tests"));
       // Tasks head = tasks.poll();  // return the task with min priority
        //System.out.println(tasks.peek().description);
       // System.out.println(head.description);
        tasks.offer(new Tasks(9,"create version"));
        tasks.forEach(e -> System.out.println(e.description));

    }
}
