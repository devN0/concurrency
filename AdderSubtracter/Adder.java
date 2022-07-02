public class Adder implements Runnable {
  Value val;

  public Adder(Value val) {
    this.val = val;
  }

  @Override
  public void run() {
    for (int i = 0; i <= 100; i++) {
      val.value += i;
    }
  }
}
