import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

public class Shuffle {

   enum Elem {
      A, B, C, D, E, F, G, H, I, J, K, L, M, O, P, Q, R, S, T, U, V, W, X, Y, Z
   }

   public static void main(String[] args) {
      List<Elem> elems = Lists.newArrayList(Elem.values());

      double iterations = 1000 * 1000;

      List<Integer> distances = Lists.newArrayList();
      List<Integer> permutations = Lists.newArrayList();

      for (int i = 0; i < iterations; i++) {
         List<Elem> elems2 = Lists.newArrayList(Elem.values());

         Collections.shuffle(elems2);
         Collections.shuffle(elems2);
         Collections.shuffle(elems2);
         Collections.shuffle(elems2);
         Collections.shuffle(elems2);

         distances.add(distance(elems, elems2));
         permutations.add(permutations(elems, elems2));
      }

      System.out.println("Average distance: " + average(distances));
      System.out.println("Std. deviation distance: " + σ(distances));
      System.out.println("Average permutations: " + average(permutations));
      System.out.println("Std. deviation permutations: " + σ(distances));
   }

   private static double average(List<Integer> list) {
      int sum = 0;
      for (Integer i : list) {
         sum += i;
      }

      return ((double) sum / (double) list.size());
   }

   private static double σ(List<Integer> list) {
      double average = average(list);

      double sumDiff = 0;
      for (Integer i : list) {
         sumDiff += (i - average) * (i - average);
      }

      return Math.sqrt(sumDiff / list.size());
   }

   private static <T> int distance(List<T> list1, List<T> list2) {
      int ret = 0;

      for (T element : list1) {
         ret += Math.abs(list1.indexOf(element) - list2.indexOf(element));
      }

      return ret;
   }

   private static <T> int permutations(List<T> list1, List<T> list2) {
      int ret = 0;

      for (T element : list1) {
         int posList1 = list1.indexOf(element);
         int posList2 = list2.indexOf(element);

         if (posList1 != posList2) {
            T temp = list2.get(posList1);

            list2.set(posList1, element);
            list2.set(posList2, temp);

            ret++;
         }
      }

      return ret;
   }

}
