import java.util.List;
import java.util.ArrayList;

/**
 * This class Hold variables related to the assessment of a property
 */
public class Assessment {
    private int Percent1;
    private int Percent2;
    private int Percent3;
    private final String Class1;
    private final String Class2;
    private final String Class3;
    private final List<String> assessmentList = new ArrayList<String>();

    /**
     * constructs the assessment object with provided value, percentages and
     * classes
     *
     * @param Percent1 the first percentage as a float.
     * @param Percent2 the second percentage as a float.
     * @param Percent3 the third percentage as a float.
     * @param Class1   the first assessment class as a string.
     * @param Class2   the second assessment class as a string.
     * @param Class3   the third assessment class as a string.
     */
    public Assessment(String Percent1, String Percent2, String Percent3, String Class1, String Class2,
                      String Class3) {
        // convert percent1 from String to float
        try {
            this.Percent1 = Integer.parseInt(Percent1);
        } catch (NumberFormatException ex) {
            this.Percent1 = 0;
        }
        // convert percent2 from String to float
        try {
            this.Percent2 = Integer.parseInt(Percent2);
        } catch (NumberFormatException ex) {
            this.Percent2 = 0;
        }
        // convert percent3 from String to float
        try {
            this.Percent3 = Integer.parseInt(Percent3);
        } catch (NumberFormatException ex) {
            this.Percent3 = 0;
        }
        this.Class1 = Class1;
        this.Class2 = Class2;
        this.Class3 = Class3;

        // create assessmentList
        if (!this.Class1.isEmpty()) {
            String s1 = this.Class1 +
                    " " + this.Percent1;
            assessmentList.add(s1);
        }
        if (!this.Class2.isEmpty()) {
            String s2 = this.Class2 +
                    " " + this.Percent2;
            assessmentList.add(s2);
        }
        if (!this.Class3.isEmpty()) {
            String s3 = this.Class3 +
                    " " + this.Percent3;
            assessmentList.add(s3);
        }
    }



    /**
     * gets the first percent of the assessment
     *
     * @return the first percentage
     */
    public int getPercent1() {
        return this.Percent1;
    }

    /**
     * gets the second percent of the assessment
     *
     * @return the second percentage
     */
    public int getPercent2() {
        return this.Percent2;
    }

    /**
     * gets the third percent of the assessment
     *
     * @return the third percentage
     */
    public int getPercent3() {
        return this.Percent3;
    }

    /**
     * gets the first class of the assessment
     *
     * @return the first class
     */
    public String getClass1() {
        return this.Class1;
    }

    /**
     * gets the second class of the assessment
     *
     * @return the second class
     */
    public String getClass2() {
        return this.Class2;
    }

    /**
     * gets the third class of the assessment
     *
     * @return the third class
     */
    public String getClass3() {
        return this.Class3;
    }

    /**
     * gets the list of assessment classes and their corresponding percentages.
     *
     * @return list of assessment classes and their percentages
     */
    public List<String> getAssessmentList() {
        return this.assessmentList;
    }

    /**
     * override of toString method for Address
     *
     * @return the address as a string
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // populate a string from each item in the assessmentList
        sb.append("[");
        int size = assessmentList.size();
        for (int i = 0; i < size; i++) {
            sb.append(assessmentList.get(i));

            // append "%, " if it's not the last item
            if (i < size - 1) {
                sb.append("%, ");
            } else {
                sb.append("%");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
