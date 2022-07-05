import java.util.concurrent.locks.*;

public class Subtracter implements Runnable {
  Value val;
  Lock lockForValue;

  public Subtracter(Value val, Lock lockForValue) {
    this.val = val;
    this.lockForValue = lockForValue;
  }

  @Override
  public void run() {
    for (int i = 0; i <= 10000; i++) {
      try {
        Thread.sleep(1L);
      } catch (Exception e) {
        e.printStackTrace();
      }

      lockForValue.lock();
      val.subtract(i);
      lockForValue.unlock();
    }
  }
}
