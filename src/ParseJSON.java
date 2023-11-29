import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * this class takes in a JSON as a string and return in a CSV form to parse into an account map
 *
 */
public class ParseJSON {
    private final Map<Integer, Account> accountMap = new HashMap<>();
    private final List<String> assessmentClassList = new ArrayList<>();

    /**
     * this is the contructor for parseJSON that takes in the json string and turns it into a CSV and sends it to
     * Account to create an account object of out of each line
     * @param jsonString the json file as a string
     */
    public ParseJSON (String jsonString) {
        // Remove square brackets and split by comma to get individual JSON objects
        String[] jsonObjects = jsonString.substring(1, jsonString.length() - 1).split("\"}");

        Account account;
        // Store each JSON item entirely in one string
        for (String jsonObject : jsonObjects) {
            // replace parts of string to convert it to csv
            jsonObject = jsonObject.replace(",{\"account_number\":\"", "");
            jsonObject = jsonObject.replace("{\"account_number\":\"", "");
            //check if suite is there and replace if it is else add a comma
            if (jsonObject.contains("suite")) {
                jsonObject = jsonObject.replace("\",\"suite\":\"", ",");
            }else if (jsonObject.contains("house_number")){
                jsonObject = jsonObject.replace("\",\"house_number\":\"", ",\",\"house_number\":\"");
            }else if (jsonObject.contains("street_name")){
                jsonObject = jsonObject.replace("\",\"street_name\":\"", ",\",\"street_name\":\"");
            }else {
                jsonObject = jsonObject.replace("\",\"garage\":\"", ",\",\"garage\":\"");
            }

            //check if house number is there and replace if it is else add a comma
            if (jsonObject.contains("house_number")) {
                jsonObject = jsonObject.replace("\",\"house_number\":\"", ",");
            }else if (jsonObject.contains("street_name")){
                jsonObject = jsonObject.replace("\",\"street_name\":\"", ",\",\"street_name\":\"");
            }else if (jsonObject.contains("garage")){
                jsonObject = jsonObject.replace("\",\"garage\":\"", ",\",\"garage\":\"");
            }else {
                jsonObject = jsonObject.replace("\",\"neighbourhood_id\":\"", ",\",\"neighbourhood_id\":\"");
            }
            //check if street name is there and replace if it is else add a comma
            if (jsonObject.contains("street_name")) {
                jsonObject = jsonObject.replace("\",\"street_name\":\"", ",");
            }else if (jsonObject.contains("garage")){
                jsonObject = jsonObject.replace("\",\"garage\":\"", ",\",\"garage\":\"");
            }else {
                jsonObject = jsonObject.replace("\",\"neighbourhood_id\":\"", ",\",\"neighbourhood_id\":\"");
            }
            //check if garage is there and replace if it is else add a comma
            if (jsonObject.contains("garage")) {
                jsonObject = jsonObject.replace("\",\"garage\":\"", ",");
            }else {
                jsonObject = jsonObject.replace("\",\"neighbourhood_id\":\"", ",\",\"neighbourhood_id\":\"");
            }
            //check if nieghborhood id is there and replace if it is else add a comma
            if (jsonObject.contains("neighbourhood_id")) {
                jsonObject = jsonObject.replace("\",\"neighbourhood_id\":\"", ",");
            }else {
                jsonObject = jsonObject.replace("\",\"neighbourhood\":\"", ",\",\"neighbourhood\":\"");
            }
            //check if nieghborhood is there and replace if it is else add a comma
            if (jsonObject.contains("neighbourhood")) {
                jsonObject = jsonObject.replace("\",\"neighbourhood\":\"", ",");
            }else {
                jsonObject = jsonObject.replace("\",\"ward\":\"", ",\",\"ward\":\"");
            }
            //check if ward is there and replace if it is else add a comma
            if (jsonObject.contains("ward")) {
                jsonObject = jsonObject.replace("\",\"ward\":\"", ",");
            }else {
                jsonObject = jsonObject.replace("\",\"assessed_value\":\"", ",\",\"assessed_value\":\"");
            }
            //check if assessed_value is there and replace if it is else add a comma
            if (jsonObject.contains("assessed_value")) {
                jsonObject = jsonObject.replace("\",\"assessed_value\":\"", ",");
            }else {
                jsonObject = jsonObject.replace("\",\"latitude\":\"", ",\",\"latitude\":\"");
            }
            //check if latitude is there and replace if it is else add a comma
            if (jsonObject.contains("latitude")) {
                jsonObject = jsonObject.replace("\",\"latitude\":\"", ",");
            }else {
                jsonObject = jsonObject.replace("\",\"longitude\":\"", ",\",\"longitude\":\"");
            }
            //check if longitude is there and replace if it is else add a comma
            if (jsonObject.contains("longitude")) {
                jsonObject = jsonObject.replace("\",\"longitude\":\"", ",");
            }else {
                jsonObject = jsonObject.replace("\",\"point_location\":{\"type\":\"Point\",\"coordinates\":", ",\",\"point_location\":{\"type\":\"Point\",\"coordinates\":");
            }

            jsonObject = jsonObject.replace("\",\"point_location\":{\"type\":\"Point\",\"coordinates\":[", ",POINT(");
            jsonObject = jsonObject.replaceAll("\",\":@computed_region_[^,]+", ",");

            jsonObject = jsonObject.replaceAll("\":@computed_region_[^,]+", "");
            jsonObject = jsonObject.replaceAll(",\\s*$", "");

            jsonObject = jsonObject.replace("]},\"tax_class_pct_1\":\"", "),");
            jsonObject = jsonObject.replace("\",\"mill_class_1\":\"", ",");
            jsonObject = jsonObject.replace("\",\"tax_class_pct_2\":\"", ",");
            jsonObject = jsonObject.replace("\",\"mill_class_2\":\"", ",");
            jsonObject = jsonObject.replace("\",\"tax_class_pct_3\":\"", ",");
            jsonObject = jsonObject.replace("\",\"mill_class_3\":\"", ",");
            jsonObject = jsonObject.replaceAll("]\\s*$", "");
            jsonObject = jsonObject.trim();

            if (!jsonObject.isEmpty()) {
                account = new Account(jsonObject, 2);
                if (!assessmentClassList.contains(account.getAssessment().getClass1())
                        && !account.getAssessment().getClass1().isEmpty()) {
                    assessmentClassList.add(account.getAssessment().getClass1());
                }
                if (!assessmentClassList.contains(account.getAssessment().getClass2())
                        && !account.getAssessment().getClass2().isEmpty()) {
                    assessmentClassList.add(account.getAssessment().getClass2());
                }
                if (!assessmentClassList.contains(account.getAssessment().getClass3())
                        && !account.getAssessment().getClass3().isEmpty()) {
                    assessmentClassList.add(account.getAssessment().getClass3());
                }
                this.accountMap.put(account.getAcctNum(), account);
            }
        }
    }
    /**
     * gets the list of Data objects and returns it.
     *
     * @return Map of account numbers and account objects
     */
    public Map<Integer, Account> getAccountMap() {
        return this.accountMap;
    }

    /**
     * gets the list of assessment classes for use in the combo box in the gui
     *
     * @return A list of all the assessment classes used
     */
    public List<String> getAssessmentClassList() {
        return this.assessmentClassList;
    }
}
