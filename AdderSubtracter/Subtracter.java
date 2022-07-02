public class Subtracter implements Runnable {
  Value val;

  public Subtracter(Value val) {
    this.val = val;
  }

  @Override
  public void run() {
    for (int i = 0; i <= 100; i++) {
      val.value -= i;
    }
  }
}
