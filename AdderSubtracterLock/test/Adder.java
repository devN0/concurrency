import java.util.concurrent.locks.Lock;

public class Adder implements Runnable {
  Value value;
  Lock lockForValue;

  public Adder(Value val, Lock lock) {
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
      // all threads have to obtain this lock before entering this critical section
      lockForValue.lock();
      int cur = value.get();
      int next = cur + i;
      value.set(next);
      lockForValue.unlock();
    }
  }
}
