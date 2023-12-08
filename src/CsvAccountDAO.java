import java.util.*;

/**
 * this class implements accountDAO when the user wants to get the data from a csv file
 */
public class CsvAccountDAO implements AccountDao{
    private final Map<Integer, Account> accountMap;
    private List<String> assessmentClassList;

    /**
     * this is the constructor for the database structure which takes data from a csv file and puts it into an account
     * map
     */
    public CsvAccountDAO() {
        String fileName = "Property_Assessment_Data_2022.csv";
        ParseCSV parseCSV = new ParseCSV(fileName);
        this.accountMap = parseCSV.getAccountMap();
        this.assessmentClassList = parseCSV.getAssessmentClassList();
    }

    /**
     * this returns an account object that has exactly the given account number as an account in the database
     *
     * @param acctNumber the account number we are searching for
     * @return the given account if it exists otherwise null
     */
    @Override
    public Account getByAccountNumber(int acctNumber) { return accountMap.get(acctNumber); }

    /**
     * this  returns a list of account objects that have the address string somewhere within their address
     *
     * @param address the address we are looking for
     * @return a list of account objects that contain the given address
     */
    @Override
    public List<Account> getByAddress(String address) {
        List<Account> addressMatch = new ArrayList<>();
        for (Account account : accountMap.values()) {
            if (account.getAddress().toString().contains(address.toUpperCase())) {
                addressMatch.add(account);
            }
        }
        return addressMatch;
    }

    /**
     * this returns a list of account objects that have the exact same neighborhood as the given neighborhood
     *
     * @param neighborhood the neighborhood we are looking for
     * @return a list of account objects in the given neighborhood
     */
    @Override
    public List<Account> getByNeighborhood(String neighborhood) {
        List<Account> inNbrHood = new ArrayList<>();
        for (Account account : accountMap.values()) {
            if (account.getNbrHood().getName().contains(neighborhood.toUpperCase())) {
                inNbrHood.add(account);
            }
        }
        return inNbrHood;
    }

    /**
     * this returns a list of account objects from the database if the given ward is the ward
     * of the property assessment.
     *
     * @param ward the ward we are looking for
     * @return a list of account objects that contain the given ward
     */
    @Override
    public List<Account> getByWard(String ward) {
        List<Account> inWard = new ArrayList<>();
        for (Account account : accountMap.values()) {
            if (account.getNbrHood().getWard().contains(ward.toLowerCase())) {
                inWard.add(account);
            }
        }
        return inWard;
    }

    /**
     * this returns a list of all the accounts in the database that have or does not have garage
     * @param garage the existence of garage; Yes or No
     * @return a list of accounts that has or does not have garage
     */
    @Override
    public List<Account> getByGarage(String garage) {
        List<Account> hasGarage = new ArrayList<>();
        for (Account account : accountMap.values()) {
            if (account.getGarage().equals(garage)){
                hasGarage.add(account);
            }
        }
        return hasGarage;
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
        List<Account> inAssessmentClass = new ArrayList<>();
        for (Account account : accountMap.values()) {
            if (    (account.getAssessment().getClass1()).contains(assessmentClass.toUpperCase()) ||
                    (account.getAssessment().getClass2()).contains(assessmentClass.toUpperCase()) ||
                    (account.getAssessment().getClass3()).contains(assessmentClass.toUpperCase())
            ) {
                inAssessmentClass.add(account);
            }
        }
        return inAssessmentClass;
    }

    /**
     * this returns a list of all account objects in the database that have an assessed value of over minValue
     *
     * @param minValue the minimum value of assessed value we are looking for
     * @return a list of accounts that have a higher value than the minimum value
     */
    @Override
    public List<Account> getByMinValue(int minValue) {
        List<Account> aboveMin = new ArrayList<>();
        for (Account account : accountMap.values()) {
            if (account.getValue() > minValue) {
                aboveMin.add(account);
            }
        }
        return aboveMin;
    }

    /**
     * this return a list of all the accounts in the database that have an assessed value under maxValue
     *
     * @param maxValue the maximum value of assessed value that we are looking for
     * @return a list of accounts that have a lower value than the max value
     */
    @Override
    public List<Account> getByMaxValue(int maxValue) {
        List<Account> belowMax = new ArrayList<>();
        for (Account account : accountMap.values()) {
            if (account.getValue() < maxValue) {
                belowMax.add(account);
            }
        }
        return belowMax;
    }



    /**
     * this returns a list of all the accounts in the database
     *
     * @return a list of all the accounts in the database
     */
    @Override
    public List<Account> getAll() {
        if (!accountMap.isEmpty()) {
            return new ArrayList<>(accountMap.values());
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * this iterates through the other searches in accountDAO and returns a lit of accounts that pass all of the
     * search criteria
     *
     * @param acctNumber the account number we are looking for
     * @param address the address we are looking for
     * @param neighborhood the neighborhood we are looking for
     * @param ward the ward we are looking for
     * @param assessmentClass the assessment class we are looking for
     * @param minValue the minimum value we are looking for
     * @param maxValue the maximum value we are looking for
     * @param garage existence of garage
     * @return a list of accounts that passes every one of the search methods
     */
    @Override
    public List<Account> searchByCriteria(int acctNumber, String address, String neighborhood, String assessmentClass, int minValue, int maxValue, String garage, String ward) {
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

        if (ward != null && !ward.isEmpty()) {
            List<Account> byWard = getByWard(ward);
            if (!byWard.isEmpty()) {
                if (results.isEmpty()) {
                    results.addAll(byWard);
                } else {
                    results.removeIf(account -> byWard.stream().noneMatch(a -> a.getAcctNum() == account.getAcctNum()));
                }
            }
        }

        if (garage != null && !garage.isEmpty()) {
            List<Account> byGarage = getByGarage(garage);
            if (!byGarage.isEmpty()) {
                if (results.isEmpty()) {
                    results.addAll(byGarage);
                } else{
                    results.removeIf(account -> byGarage.stream().noneMatch(a -> a.getAcctNum() == account.getAcctNum()));
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
