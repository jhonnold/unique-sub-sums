import java.util.Iterator;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UniqueSubSums {

  public static void main(String[] args) {
    new UniqueSubSums().run();
  }

  public void run() {
    final ExecutorService pool = Executors.newFixedThreadPool(15);
    CombinationsGenerator cg = new CombinationsGenerator(85, 8);
    long start = System.currentTimeMillis();

    while (cg.hasNext()) {
      pool.execute(new SubSumThread(cg.next()));
    }

    System.out.println(System.currentTimeMillis() - start);

    pool.shutdown();
    try {
      if (!pool.awaitTermination(300, TimeUnit.SECONDS)) {
        pool.shutdownNow();
        if (!pool.awaitTermination(300, TimeUnit.SECONDS))
           System.err.println("Pool did not terminate");
      }
    } catch (InterruptedException ie) {
      pool.shutdownNow();
      Thread.currentThread().interrupt();
    }
  }

  public static void printArray(int[] array) {
    String output = "";
    for (int i = 0; i < array.length; i++)
      output += String.format("%4d", array[i]);

    System.out.println(output);
  }

  // THIS IS A WORKER
  public class SubSumThread implements Runnable {
    private int[] array;

    public SubSumThread(int[] array) {
      this.array = array;
    }

    @Override
    public void run() {
      SubsetSumGenerator ssg = new SubsetSumGenerator(array);
      HashMap<Integer, Boolean> sums = new HashMap<>();

      while (ssg.hasNext()) {
        int sum = ssg.next();

        if (sums.containsKey(sum)) {
          return;
        } else {
          sums.put(sum, true);
        }
      }

      printArray(array);
    }
  }

  // THIS GENERATES ALL THE SUMS OF SUBSETS
  public class SubsetSumGenerator implements Iterator {
    private int[] array;
    private int i;

    public SubsetSumGenerator(int[] array) {
      this.array = array;
      i = 1;
    }

    @Override
    public boolean hasNext() {
      return i < (1 << array.length);
    }

    @Override
    public Integer next() {
      int sum = 0;
      for (int j = 0; j < array.length; j++)
        if((i & (1 << j)) > 0)
          sum += array[j];

      i++;
      return sum;
    }
  }

  // THIS GENERATES ALL COMBINATIONS OF A [1..N] OF SIZE K
  public class CombinationsGenerator implements Iterator {
    private int n, k;
    private int[] array;

    public CombinationsGenerator(int n, int k) {
      this.n = n;
      this.k = k;

      array = new int[k];

      for (int i = 0; i < k; i++)
        array[i] = n - i;
      array[k - 1]++; // Fix for the first next() call
    }

    @Override
    public boolean hasNext() {
      return array[0] > k;
    }

    @Override
    public int[] next() {
      for (int i = k - 1; i >= 0; i--) {
        if (array[i] > k - i) {
          array[i]--;
          for (int j = i + 1; j < k; j++) {
            array[j] = array[j - 1] - 1;
          }
          break;
        }
      }

      return Arrays.copyOf(array, k);
    }
  }
}
