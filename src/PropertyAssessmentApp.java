import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.List;

/**
 * this class creates a screen to display data from either an API call or CSV and allows parsing through the data to
 * to find specific property assessments from the data
 */
public class PropertyAssessmentApp extends Application {
    private Stage primaryStage;
    private AccountDao accountDao;
    private List<String> assessmentClassList;
    private TableView<Account> tableView = new TableView<>();
    private ComboBox<String> dataSourceComboBox = new ComboBox<>();
    private TextField accountNumberField = new TextField();
    private TextField addressField = new TextField();
    private TextField neighborhoodField = new TextField();
    private ComboBox<String> assessmentClassComboBox = new ComboBox<>();
    private TextField minValueField = new TextField();
    private TextField maxValueField = new TextField();
    private ComboBox<String> garageComboBox = new ComboBox<>();


    /**
     * this launches our application
     *
     * @param args the application
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * this starts our application, run on start it starts up the screen with initUI
     *
     * @param primaryStage the main and only window in the application
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initUI();
    }

    /**
     * this initializes our UI with all the features that it needs and sets up the styles of everything
     */
    private void initUI() {
        // put items in the combo box
        dataSourceComboBox.getItems().addAll("CSV file", "Edmonton's open data portal");

        // set width of assessment class combobox
        assessmentClassComboBox.setPrefWidth(200);
        garageComboBox.setPrefWidth(200);
        // Create a button to read data
        Button readDataButton = new Button("Read Data");
        readDataButton.setOnAction(e -> handleReadDataButtonClick());

        // Create labels
        Label selectDataLabel = new Label("Select Data Source:");
        selectDataLabel.setStyle("-fx-font-weight: bold;");
        Label findPropertyAsessmentLabel = new Label("Find Property Assessment");
        findPropertyAsessmentLabel.setStyle("-fx-font-weight: bold;");
        Label accountNumberLabel = new Label("Account Number:");
        Label addressLabel =  new Label("Address (# Suite # House Street):");
        Label neighborhoodLabel = new Label("Neighborhood:");
        Label assessmentClassLabel = new Label("Assessment Class:");
        Label garageLabel = new Label("Garage:");
        Label ValueLabel = new Label("Assessed Value Range:");

        //create a search button
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> handleSearchButtonClick());
        searchButton.setPrefWidth(100);

        //create a reset button
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> handleResetButtonClick());
        resetButton.setPrefWidth(100);

        // Create a VBox for search criteria
        VBox searchCriteriaBox = new VBox(
                accountNumberLabel, accountNumberField,
                addressLabel, addressField,
                neighborhoodLabel, neighborhoodField,
                assessmentClassLabel, assessmentClassComboBox,
                garageLabel, garageComboBox,
                ValueLabel, new HBox(minValueField, maxValueField),
                new HBox(searchButton, resetButton)
        );
        searchCriteriaBox.setPadding(new Insets(5));

        VBox leftBox = new VBox(selectDataLabel, dataSourceComboBox, readDataButton, new Separator(),
                findPropertyAsessmentLabel, searchCriteriaBox);
        leftBox.setAlignment(Pos.CENTER);
        leftBox.setSpacing(10);
        leftBox.setPrefWidth(200);
        leftBox.setAlignment(Pos.TOP_CENTER);

        // Initialize with CsvAccountDAO
        accountDao = new CsvAccountDAO();

        List<Account> accountList = accountDao.getAll();

        // Create columns for each property
        TableColumn<Account, Integer> acctNumColumn = new TableColumn<>("Account");
        acctNumColumn.setPrefWidth(60);
        acctNumColumn.setCellValueFactory(new PropertyValueFactory<>("acctNum"));

        TableColumn<Account, Address> addressColumn = new TableColumn<>("Address");
        addressColumn.setPrefWidth(150);
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Account, String> garageColumn = new TableColumn<>("Garage");
        garageColumn.setPrefWidth(120);
        garageColumn.setCellValueFactory(new PropertyValueFactory<>("garage"));

        TableColumn<Account, String> valueColumn = new TableColumn<>("fValue");
        valueColumn.setPrefWidth(70);
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("formattedValue"));

        TableColumn<Account, Assessment> assessmentColumn = new TableColumn<>("Assessment");
        assessmentColumn.setPrefWidth(250);
        assessmentColumn.setCellValueFactory(new PropertyValueFactory<>("assessment"));

        TableColumn<Account, NbrHood> nbrHoodColumn = new TableColumn<>("Neighborhood");
        nbrHoodColumn.setPrefWidth(280);
        nbrHoodColumn.setCellValueFactory(new PropertyValueFactory<>("nbrHood"));

        TableColumn<Account, Location> locationColumn = new TableColumn<>("Location");
        locationColumn.setPrefWidth(260);
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));




        // Add columns to the table
        tableView.getColumns().addAll(acctNumColumn, addressColumn, garageColumn, valueColumn, assessmentColumn, nbrHoodColumn, locationColumn);

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(leftBox);
        borderPane.setCenter(new StackPane(tableView));

        Scene scene = new Scene(borderPane, 1000, 600);
        primaryStage.setTitle("Edmonton Property Assessments");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * this takes in the data source selection from the combo box at the top on the screen and sets which accountDAO
     * implementation we will use for our database
     *
     * @param selectedDataSource the data source, either csv or Edmonton's open data portal API
     */
    private void handleDataSourceSelection(String selectedDataSource) {
        if (selectedDataSource != null) {
            if ("CSV file".equals(selectedDataSource)) {
                accountDao = new CsvAccountDAO();
            } else if ("Edmonton's open data portal".equals(selectedDataSource)) {
                accountDao = new ApiAccountDAO();
            }
        } else {
            // If nothing is selected, set an empty list of items
            ((TableView<Account>) ((StackPane) primaryStage.getScene().getRoot()).getChildren().get(0)).setItems(FXCollections.emptyObservableList());
            return;
        }

        // Reload data when the data source changes
        List<Account> accountList = accountDao.getAll();
        ObservableList<Account> items = FXCollections.observableArrayList(accountList);
        tableView.setItems(items);
    }

    /**
     * this method handles the actions taken when the read data button is pressed, it mainly calls
     * handleDataSourceSelection as long as an item is picked in the combobox. otherwise it gives a popup warning user
     * to pick a data source
     */
    private void handleReadDataButtonClick() {
        ComboBox<String> dataSourceComboBox = (ComboBox<String>) ((VBox) primaryStage.getScene().getRoot().getChildrenUnmodifiable().get(0)).getChildren().get(1);

        if (dataSourceComboBox.getValue() != null) {
            handleDataSourceSelection(dataSourceComboBox.getValue());

            // Update the assessment class ComboBox
            updateAssessmentClassComboBox();
            updateGarageComboBox();
        } else {
            showNoDataSourcePopup();
        }
    }

    /**
     * this method updates the assessment classes in the assessment class combobox with a list of assessment classes
     * in the selected data set
     */
    private void updateAssessmentClassComboBox() {
        assessmentClassList = accountDao.getAssessmentClassList();
        assessmentClassComboBox.getItems().clear();
        assessmentClassComboBox.getItems().addAll(assessmentClassList);
    }
    /**
     * this method updates the garage status in the garage combobox with Yes and No
     * in the selected data set
     */
    private void updateGarageComboBox(){
        garageComboBox.getItems().clear();
        garageComboBox.getItems().addAll("Yes", "No");
    }

    /**
     * this method handles when the search button is clicked sending all the entered data to searchByCriteria in the
     * DAO to get a list of Accounts that satisfy all the searched for fields
     */
    private void handleSearchButtonClick() {
        int acctNumber = parseTextField(accountNumberField);
        String address = addressField.getText().trim();
        String neighborhood = neighborhoodField.getText().trim();
        String assessmentClass = assessmentClassComboBox.getValue();
        String garage = garageComboBox.getValue();
        int minValue = parseTextField(minValueField);
        int maxValue = parseTextField(maxValueField);

        // Call searchByCriteria method
        List<Account> searchResults = accountDao.searchByCriteria(acctNumber, address, neighborhood, assessmentClass, minValue, maxValue, garage);
        if (searchResults.isEmpty()) {
            // Display a popup if there are no search results
            showNoResultsPopup();
        } else {
            ObservableList<Account> items = FXCollections.observableArrayList(searchResults);
            tableView.setItems(items);
        }
    }

    /**
     * this method handles the reset button click which clears all search fields and resets the data back to all items
     * in the dataset
     */
    private void handleResetButtonClick() {
        // Clear all search fields
        accountNumberField.clear();
        addressField.clear();
        neighborhoodField.clear();
        assessmentClassComboBox.setValue(null);
        garageComboBox.setValue(null);
        minValueField.clear();
        maxValueField.clear();

        // Reload all data
        handleReadDataButtonClick();
    }

    /**
     * this method is to show a no results found popup when nothing is returned by a search
     */
    private void showNoResultsPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No Results");
        alert.setHeaderText(null);
        alert.setContentText("No results were returned.");

        // Add "OK" button to close the popup
        ButtonType okButton = new ButtonType("OK", ButtonType.OK.getButtonData());
        alert.getButtonTypes().setAll(okButton);

        // Handle button click event
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                // Handle OK button click (you can leave this empty or perform any specific action)
            }
        });
    }

    /**
     * this method it display a no data source selected popup when the user forgets to select a data source
     */
    private void showNoDataSourcePopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No data source selected");
        alert.setHeaderText(null);
        alert.setContentText("Please select a data source.");

        // Add "OK" button to close the popup
        ButtonType okButton = new ButtonType("OK", ButtonType.OK.getButtonData());
        alert.getButtonTypes().setAll(okButton);

        // Handle button click event
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                // Handle OK button click (you can leave this empty or perform any specific action)
            }
        });
    }

    /**
     * this method is a helper function for pulling integers from a textField that will set them to 0 if it fails
     *
     * @param textField the integer we want to store
     * @return an integer value, either the integer in th field or 0 if the function fails.
     */
    private int parseTextField(TextField textField) {
        try {
            return Integer.parseInt(textField.getText().trim());
        } catch (NumberFormatException e) {
            return 0; // Default value if parsing fails
        }
    }
}