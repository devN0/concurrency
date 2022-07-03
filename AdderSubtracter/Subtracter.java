public class Subtracter implements Runnable {
  Value val;

  public Subtracter(Value val) {
    this.val = val;
  }

  @Override
  public void run() {
    for (int i = 0; i <= 10000; i++) {
      try {
        Thread.sleep(1L);
      } catch (Exception e) {
        e.printStackTrace();
      }
      val.subtract(i);
    }
  }
}
