import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

public class Main {
  public static void main(String[] args) {
    Value value = new Value();
    Lock lockForValue = new ReentrantLock();

    ExecutorService executorService = Executors.newCachedThreadPool();
    executorService.execute(new Adder(value, lockForValue));
    executorService.execute(new Subtracter(value, lockForValue));

    executorService.shutdown();
    try {
      executorService.awaitTermination(1, TimeUnit.MINUTES);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(value.get());
  }
}
