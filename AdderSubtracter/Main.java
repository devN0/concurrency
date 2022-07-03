import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
  public static void main(String[] args) {
    Value val = new Value();

    ExecutorService executor = Executors.newCachedThreadPool();

    executor.execute(new Adder(val));
    executor.execute(new Subtracter(val));

    executor.shutdown();
    try {
      executor.awaitTermination(1, TimeUnit.MINUTES);
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println(val.get());
  }
}
