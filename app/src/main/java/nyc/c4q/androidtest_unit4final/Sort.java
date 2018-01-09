package nyc.c4q.androidtest_unit4final;

import java.util.List;

/**
 * Created by justiceo on 1/7/18.
 */

public class Sort {

    /**
     * Sorts a list using the selection sort algorithm.
     * See lecture on sorting: https://github.com/C4Q/AC-Android/tree/v2/DSA/sorting
     *
     * When `isAscending` is true, the list is sorted in ascending order,
     * otherwise it is sorted in descending order.
     * @param list
     * @param isAscending
     */
    public static void selectionSort(List<String> list, boolean isAscending) {
        // TODO: Implement selection sort.
        // Tip: Try a version without ordering first.

        for (int n = 0; n < list.size(); n++) {
            // step 1: find smallest/largest element depending on order
            int minMax = n;
            for(int i = n; i < list.size(); i++) {
                if (isAscending && list.get(i).compareTo(list.get(minMax)) < 0) {
                    minMax = i;
                }

                if (!isAscending && list.get(i).compareTo(list.get(minMax)) > 0) {
                    minMax = i;
                }
            }

            // step 2: swap with first element
            String tmp = list.get(minMax);
            list.set(minMax, list.get(n));
            list.set(n, tmp);
        }
    }
}
