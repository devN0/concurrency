import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Sorter implements Callable<List<Integer>> {
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
  ExecutorService executor;

  public Sorter(List<Integer> list, ExecutorService executor) {
    this.list = list;
    this.executor = executor;
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Integer> call() throws Exception {

    int N = list.size();
    if (N <= 1) {
      return list;
    }

    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();

    int mid = N / 2;

    for (int i = 0; i < mid; i++) {
      left.add(list.get(i));
    }
    for (int j = mid; j < N; j++) {
      right.add(list.get(j));
    }

    Future<List<Integer>> leftSortedFuture = executor.submit(new Sorter(left, executor));
    Future<List<Integer>> rightSortedFuture = executor.submit(new Sorter(right, executor));

    List<Integer> leftSorted = leftSortedFuture.get();
    List<Integer> rightSorted = rightSortedFuture.get();

    // merging left and right arrays
    List<Integer> mergedList = new ArrayList();

    int i = 0;
    int j = 0;
    int iEnd = left.size();
    int jEnd = right.size();

    while (i < iEnd && j < jEnd) {
      int li = leftSorted.get(i);
      int lj = rightSorted.get(j);
      if (li <= lj) {
        mergedList.add(li);
        i++;
      } else {
        mergedList.add(lj);
        j++;
      }
    }

    while (i < iEnd) {
      mergedList.add(leftSorted.get(i));
      i++;
    }
    while (j < jEnd) {
      mergedList.add(rightSorted.get(j));
      j++;
    }

    return mergedList;
  }
}
