import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  public static void main(String[] args) {
    List<Integer> list = List.of(4, 6, 2, 3, 8, 1, 12, 5);
    int n = list.size();
    ExecutorService executor = Executors.newCachedThreadPool();

    executor.execute(new Sorter(list, executor, 0, n - 1));
    System.out.println(list);
    // try {
    // executor.shutdown();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
  }
}
