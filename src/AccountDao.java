import java.util.List;

/**
 * this interface sets up the data structure for the accounts being set up and displayed in the GUI.
 * there are two implementations at the moment, one for taking in the city of edmontons API data as a json and
 * one that will take in a given csv file
 */
public interface AccountDao {
    /**
     * this method returns an account if the given account number matches exactly an account number in the database
     *
     * @param acctNumber the account number we are searching for
     * @return an account object for the given account number if found, otherwise null
     */
    Account getByAccountNumber (int acctNumber);

    /**
     * this returns a list of account objects from the database if the string is at all partially contained in the
     * property's full address
     *
     * @param address the address we are looking for
     * @return a list of account objects that their address contains the given address string
     */
    List<Account> getByAddress (String address);

    /**
     * this returns a list of account objects from the database if the given neighborhood matches exactly the
     * neighborhood of the property assessment
     *
     * @param neighborhood the neighborhood we are looking for
     * @return a list of account objects that contain the given neighborhood
     */
    List<Account> getByNeighborhood(String neighborhood);

    /**
     * this returns a list of account objects from the database if the given ward is the ward
     * of the property assessment.
     *
     * @param ward the ward we are looking for
     * @return a list of account objects that contain the given ward
     */
    List<Account> getByWard(String ward);

    /**
     * this returns a list of accounts fromm the database if the given assessment class is any of the three possible
     * assessment classes for the property assessment
     *
     * @param assessmentClass the assessment class we want to search for
     * @return a list of account objects match the neighborhood given to the method
     */
    List<Account> getByAssessmentClass(String assessmentClass);

    /**
     * this returns a list of all account objects in the database that have an assessed value of over minValue
     *
     * @param minValue the minimum value of assessed value we are looking for
     * @return a list of accounts that have a higher value than the minimum value
     */
    List<Account> getByMinValue(int minValue);

    /**
     * this return a list of all the accounts in the database that have an assessed value under maxValue
     *
     * @param maxValue the maximum value of assessed value that we are looking for
     * @return a list of accounts that have a lower value than the max value
     */
    List<Account> getByMaxValue(int maxValue);

    /**
     * this returns a list of all the accounts in the database that have or does not have garage
     * @param garage the existence of garage; Yes or No
     * @return a list of accounts that has or does not have garage
     */
    List<Account> getByGarage(String garage);
    /**
     * this returns a list of all the accounts in the database
     *
     * @return a list of all the accounts in the database
     */
    List<Account> getAll();

    /**
     * this iterates through the other searches in accountDAO and returns a lit of accounts that pass all of the
     * search criteria
     *
     * @param acctNumber the account number we are looking for
     * @param address the address we are looking for
     * @param neighborhood the neighborhood we are looking for
     * @param assessmentClass the assessment class we are looking for
     * @param minValue the minimum value we are looking for
     * @param maxValue the maximum value we are looking for
     * @return a list of accounts that passes every one of the search methods
     */
    List<Account> searchByCriteria(int acctNumber, String address, String neighborhood, String assessmentClass, int minValue, int maxValue, String garage, String ward);

    /**
     * this returns a list of all the assessment classes that are in the database
     *
     * @return list of assessment class strings
     */
    List<String> getAssessmentClassList();

}
