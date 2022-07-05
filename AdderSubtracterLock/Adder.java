import java.util.concurrent.locks.Lock;

import java.util.concurrent.locks.*;

public class Adder implements Runnable {
  Value val;
  Lock lockForValue;

  public Adder(Value val, Lock lockForValue) {
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
      val.add(i);
      lockForValue.unlock();
    }
  }
}
