public class Adder implements Runnable {
  Value val;

  public Adder(Value val) {
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
      val.add(i);
    }
  }
}
