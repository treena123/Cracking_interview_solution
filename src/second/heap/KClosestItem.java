package second.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * @author trinapal
 */
public class KClosestItem {
    public static void main(String[] args) {
        List<List<Integer>> points = Arrays.stream(new int [][]{{1,1},{2,2},{ 3,3}}).map(arr -> Arrays.stream(arr).boxed().toList()).toList();
        List<List<Integer>> res = kClosestPoints(points, 1);
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }

    private static List<List<Integer>> kClosestPoints(List<List<Integer>> points, int k) {
        List<List<Integer>> res = new ArrayList<>();
        Comparator<List<Integer>> distanceComparator = new Comparator<List<Integer>>() {


            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return distanceFromOrigin(o1)- distanceFromOrigin(o2) ;
            }

            public int distanceFromOrigin(List<Integer> p){
                return p.get(0)*p.get(0) + p.get(1)*p.get(1);
            }
        };
        PriorityQueue<List<Integer>> priorityQueue = new PriorityQueue<>(points.size(), distanceComparator);
        for(List<Integer> point: points){
            priorityQueue.add(point);
        }
        for(int i =0; i<k; i++){
            res.add(priorityQueue.poll());
        }
        return res;
    }
}
