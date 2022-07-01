import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// import Sorter.java;

public class Main {
  public static void main(String[] args) throws Exception {
    List<Integer> list = List.of(1, 5, 4, 3, 6, 7, 2);

    ExecutorService executor = Executors.newCachedThreadPool();

    Future<List<Integer>> sortedFuture = executor.submit(new Sorter(list, executor));
    List<Integer> sorted = sortedFuture.get();

    System.out.println(sorted);
    executor.shutdown();
  }
}
