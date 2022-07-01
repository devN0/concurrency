import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Sorter implements Runnable {
  // 1: SF = {1, 5, 4, 3, 6, 7, 2}; s = 0; e = 6; // i(s<e) -> m = 3;
  // 1-1: LSF = {1, 5, 4, 3}; s = 0; e = 3; // i(s<e) -> m = 1;
  // 1-2: RSF = {6, 7, 2}; s = 4; e = 6; // i(s<e) -> m = 5;
  // i = 4; j = 6; // w(4<=5 && 6<=6) -> i(l[j]<l[i]) {2}
  // 1-1-1: LSF = {1, 5}; s = 0; e = 1; // i(s<e) -> m = 0;
  // 1-1-2: RSF = {4, 3} s = 2; e = 3; // i(s<e) -> m = 2;
  // 1-2-1: LSF = {6, 7} s = 4; e = 5; // i(s<e) -> m = 4;
  // i = 4; j = 5; // w(4<=4 && 5<=5) -> i(l[4]<l[5]) {6,7}
  // 1-2-2: RSF = {2} s = 6; e = 6; // X; {2}
  // 1-1-1-1: LSF = {1} s = 0; e = 0; // X;
  // 1-1-1-2: RSF = {5} s = 1; e = 1; // X;
  // 1-1-2-1: LSF = {4} s = 2; e = 2; // X;
  // 1-1-2-2: RSF = {3} s = 3; e = 3; // X;
  // 1-2-1-1: LSF = {6} s = 4; e = 4; // X; {6}
  // 1-2-1-2: RSF = {7} s = 5; e = 5; // X; {7}

  List<Integer> list;
  int start;
  int end;
  ExecutorService executor;

  public Sorter(List<Integer> list, ExecutorService executor, int start, int end) {
    this.list = list;
    this.start = start;
    this.end = end;
    this.executor = executor;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void run() {

    if (start < end) {
      int mid = start + (end - start) / 2;
      executor.execute(new Sorter(list, executor, start, mid));
      executor.execute(new Sorter(list, executor, mid + 1, end));

      List<Integer> mergedList = new ArrayList();

      int i = start;
      int j = mid + 1;

      while (i <= mid && j <= end) {
        int li = list.get(i);
        int lj = list.get(j);
        if (li <= lj) {
          mergedList.add(li);
          i++;
        } else {
          mergedList.add(lj);
          j++;
        }
      }

      while (i <= mid) {
        mergedList.add(list.get(i));
        i++;
      }
      while (j <= end) {
        mergedList.add(list.get(j));
        j++;
      }

      for (int k = 0; k < mergedList.size(); k++) {
        list.set(start + k, mergedList.get(k));
      }

    }
  }
}
