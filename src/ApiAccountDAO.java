import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

/**
 * this class implements the accountDAO for when the user want to input data via API retrieving a json file
 */
public class ApiAccountDAO implements AccountDao{
    private final String endpoint = "https://data.edmonton.ca/resource/q7d6-ambg.json";
    private Map<Integer, Account> accountMap;
    private List<String> assessmentClassList;

    /**
     * this returns an account object that has exactly the given account number as an account in the database
     *
     * @param acctNumber the account number we are searching for
     * @return the given account if it exists otherwise null
     */
    @Override
    public Account getByAccountNumber(int acctNumber) {
        String query = endpoint + "?account_number=" + acctNumber;
        String jsonString = callAPI(query);
        ParseJSON parseJSON = new ParseJSON(jsonString);
        this.accountMap = parseJSON.getAccountMap();

        return accountMap.get(acctNumber);
    }

    /**
     * this  returns a list of account objects that have the address string somewhere within their address
     *
     * @param address the address we are looking for
     * @return a list of account objects that contain the given address
     */
    @Override
    public List<Account> getByAddress(String address) {
        String jsonString = callAPI(endpoint);
        ParseJSON parseJSON = new ParseJSON(jsonString);
        this.accountMap = parseJSON.getAccountMap();
        accountMap.values().removeIf(account -> !account.getAddress().toString().contains(address.toUpperCase()));
        return new ArrayList<>(accountMap.values());
    }

    /**
     * this returns a list of account objects that have the exact same neighborhood as the given neighborhood
     *
     * @param neighborhood the neighborhood we are looking for
     * @return a list of account objects in the given neighborhood
     */
    @Override
    public List<Account> getByNeighborhood(String neighborhood) {
        String query = endpoint + "?neighbourhood=" + neighborhood.toUpperCase().replace(" ", "%20");
        String jsonString = callAPI(query);
        ParseJSON parseJSON = new ParseJSON(jsonString);
        this.accountMap = parseJSON.getAccountMap();

        return new ArrayList<>(accountMap.values());
    }

    /**
     * this returns a list of account objects that have the assessment class we are searching in any of their assessment
     * classes
     *
     * @param assessmentClass the assessment class we want to search for
     * @return a list of account objects that have the given assessment class
     */
    @Override
    public List<Account> getByAssessmentClass(String assessmentClass) {
        String query = endpoint + "?$where=(mill_class_1='" + assessmentClass.toUpperCase().replace(" ", "%20")
                + "')OR(mill_class_2='" + assessmentClass.toUpperCase().replace(" ", "%20")
                + "')OR(mill_class_3='" + assessmentClass.toUpperCase().replace(" ", "%20") + "')";
        String jsonString = callAPI(query);
        ParseJSON parseJSON = new ParseJSON(jsonString);
        this.accountMap = parseJSON.getAccountMap();

        return new ArrayList<>(accountMap.values());
    }

    /**
     * this returns a list of all account objects in the database that have an assessed value of over minValue
     *
     * @param minValue the minimum value of assessed value we are looking for
     * @return a list of accounts that have a higher value than the minimum value
     */
    @Override
    public List<Account> getByMinValue(int minValue) {
        String jsonString = callAPI(endpoint);
        ParseJSON parseJSON = new ParseJSON(jsonString);
        this.accountMap = parseJSON.getAccountMap();
        accountMap.values().removeIf(account -> account.getValue() < minValue);

        return new ArrayList<>(accountMap.values());
    }

    /**
     * this return a list of all the accounts in the database that have an assessed value under maxValue
     *
     * @param maxValue the maximum value of assessed value that we are looking for
     * @return a list of accounts that have a lower value than the max value
     */
    @Override
    public List<Account> getByMaxValue(int maxValue) {
        String jsonString = callAPI(endpoint);
        ParseJSON parseJSON = new ParseJSON(jsonString);
        this.accountMap = parseJSON.getAccountMap();
        accountMap.values().removeIf(account -> account.getValue() > maxValue);

        return new ArrayList<>(accountMap.values());
    }

    /**
     * this returns a list of all the accounts in the database
     *
     * @return a list of all the accounts in the database
     */
    @Override
    public List<Account> getAll() {
        String jsonString = callAPI(endpoint);
        ParseJSON parseJSON = new ParseJSON(jsonString);
        this.accountMap = parseJSON.getAccountMap();
        this.assessmentClassList = parseJSON.getAssessmentClassList();

        return new ArrayList<>(accountMap.values());
    }

    /**
     * this is a helper method for the methods in this class to call on the api with a query
     *
     * @param query the api call we want to send
     * @return a json in string form
     */
    private String callAPI(String query) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(query))
                .GET()
                .build();
        String jsonString;
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            jsonString = response.body();
        } catch (IOException | InterruptedException e) {
            return null;
        }
        return jsonString;
    }

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
    @Override
    public List<Account> searchByCriteria(int acctNumber, String address, String neighborhood, String assessmentClass, int minValue, int maxValue) {
        List<Account> results = new ArrayList<>();

        if (acctNumber != 0) {
            Account byAccountNumber = getByAccountNumber(acctNumber);
            if (byAccountNumber != null) {
                results.add(byAccountNumber);
            } else {
                return results;
            }
        }

        if (address != null && !address.isEmpty()) {
            List<Account> byAddress = getByAddress(address);
            if (!byAddress.isEmpty()) {
                if (results.isEmpty()) {
                    results.addAll(byAddress);
                } else {
                    results.removeIf(account -> byAddress.stream().noneMatch(a -> a.getAcctNum() == account.getAcctNum()));
                }
            }
        }

        if (neighborhood != null && !neighborhood.isEmpty()) {
            List<Account> byNeighborhood = getByNeighborhood(neighborhood);
            if (!byNeighborhood.isEmpty()) {
                if (results.isEmpty()) {
                    results.addAll(byNeighborhood);
                } else {
                    results.removeIf(account -> byNeighborhood.stream().noneMatch(a -> a.getAcctNum() == account.getAcctNum()));
                }
            }
        }

        if (assessmentClass != null && !assessmentClass.isEmpty()) {
            List<Account> byAssessmentClass = getByAssessmentClass(assessmentClass);
            if (!byAssessmentClass.isEmpty()) {
                if (results.isEmpty()) {
                    results.addAll(byAssessmentClass);
                } else {
                    results.removeIf(account -> byAssessmentClass.stream().noneMatch(a -> a.getAcctNum() == account.getAcctNum())
                            && byAssessmentClass.stream().noneMatch(a -> a.getAcctNum() == account.getAcctNum())
                            && byAssessmentClass.stream().noneMatch(a -> a.getAcctNum() == account.getAcctNum()));
                }
            }
        }

        if (minValue != 0) {
            List<Account> byMinValue = getByMinValue(minValue);
            if (!byMinValue.isEmpty()) {
                if (results.isEmpty()) {
                    results.addAll(byMinValue);
                } else {
                    results.removeIf(account -> byMinValue.stream().noneMatch(a -> a.getAcctNum() == account.getAcctNum()));
                }
            }
        }
        if (maxValue != 0) {
            List<Account> byMaxValue = getByMaxValue(maxValue);
            if (!byMaxValue.isEmpty()) {
                if (results.isEmpty()) {
                    results.addAll(byMaxValue);
                } else {
                    results.removeIf(account -> byMaxValue.stream().noneMatch(a -> a.getAcctNum() == account.getAcctNum()));
                }
            }
        }

        return results;
    }

    /**
     * this returns a list of all the assessment classes that are in the database
     *
     * @return list of assessment class strings
     */
    @Override
    public List<String> getAssessmentClassList() {
        return this.assessmentClassList;
    }
}
