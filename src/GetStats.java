import java.util.List;

/**
 * this class implements a method to return statistics for property assessments.
 * references:
 * - <a href="https://www.digitalocean.com/community/tutorials/java-sort-list">...</a>
 */
public class GetStats {
    private int n;
    private int min;
    private int max;
    private int range;
    private long mean;
    private int median;

    /**
     * constuctor for getStats that sets the stat variables rake from the inputted data
     *
     * @param data a sorted list of property assessment data
     */
    public GetStats(List<Account> data) {

        if (!data.isEmpty()) {
            this.n = data.size();
            this.min = data.get(0).getValue();
            this.max = data.get(n - 1).getValue();
            this.range = max - min;

            // calculate mean value
            for (Account i : data) {
                mean += i.getValue();
            }
            this.mean = mean / n;

            // calculate median value
            if (n % 2 == 0) {
                // if n is even average of middle two values
                int mid1 = data.get(n / 2).getValue();
                int mid2 = data.get(n / 2 - 1).getValue();
                this.median = (mid1 + mid2) / 2;
            } else {
                // if n is odd average of middle value
                this.median = data.get(n / 2).getValue();
            }
        }
    }

    /**
     * override of toString method for the getStats class
     *
     * @return A string of the property stats.
     */
    public String toString() {
        if (n != 0) {
            return "n = " + n +
                    "\nmin = $" + this.min +
                    "\nmax = $" + this.max +
                    "\nrange = $" + this.range +
                    "\nmean = $" + this.mean +
                    "\nmedian = $" + this.median + "\n";
        } else {
            return "";
        }
    }
}
