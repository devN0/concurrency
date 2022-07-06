import java.util.concurrent.atomic.AtomicInteger;

public class Value {
  private int value = 0;
  private AtomicInteger atomicValue = new AtomicInteger(0);

  public void add(int i) {
    atomicValue.addAndGet(i);
  }

  public void subtract(int i) {
    atomicValue.addAndGet(-i);
  }

  public int get() {
    return atomicValue.get();
  }
}
