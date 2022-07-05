import java.util.concurrent.locks.Lock;

public class Subtracter implements Runnable {
  Value value;
  Lock lockForValue;

  public Subtracter(Value val, Lock lock) {
    this.value = val;
    this.lockForValue = lock;
  }

  @Override
  public void run() {
    for (int i = 0; i <= 100; i++) {
      try {
        Thread.sleep(1L);
      } catch (Exception e) {
        e.printStackTrace();
      }
      lockForValue.lock();
      int cur = value.get();
      int next = cur - i;
      value.set(next);
      lockForValue.unlock();
    }
  }
}
