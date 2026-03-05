package luis_rojas.luisr;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
// classes are in the same package; no import required

public class ClinicalLabController {

    @FXML
    private TextField searchField;
    @FXML
    private GridPane testsGrid;
    @FXML
    private TextField patientField;
    @FXML
    private Label subtotalLabel;
    @FXML
    private Label totalAmount;
    @FXML
    private TableView<Concept> summaryTable;
    @FXML
    private TableColumn<Concept, String> nameColumn;
    @FXML
    private TableColumn<Concept, Double> priceColumn;

    private double subtotal = 0.0;
    private List<Concept> allConcepts;
    private Set<String> addedTests = new HashSet<>();

    @FXML
    public void initialize() {
        if (summaryTable != null) {
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        }

        if (testsGrid != null) {
            allConcepts = MySQLDB.getConcepts();
            if (allConcepts.isEmpty()) {
                allConcepts.add(new Concept(0, "Hemograma", 15.0));
            }
            populateGrid(allConcepts);
        }

        if (searchField != null) {
            searchField.textProperty().addListener((obs, oldV, newV) -> {
                if (newV == null || newV.trim().isEmpty()) {
                    populateGrid(allConcepts);
                } else {
                    String lowerQuery = newV.toLowerCase();
                    List<Concept> filtered = allConcepts.stream()
                            .filter(c -> c.getName().toLowerCase().contains(lowerQuery))
                            .toList();
                    populateGrid(filtered);
                }
            });
        }
    }

    private void populateGrid(List<Concept> itemsToDisplay) {
        if (testsGrid == null)
            return;
        testsGrid.getChildren().clear();

        int col = 0, row = 0;
        for (Concept c : itemsToDisplay) {
            Button add = new Button("+");
            add.getStyleClass().add("add-button");
            final String testName = c.getName();
            final double priceVal = c.getPrice();
            add.setOnAction(e -> addTestToSummary(testName, priceVal));

            VBox card = new VBox(6);
            card.getStyleClass().add("test-card");
            Label name = new Label(testName);
            name.getStyleClass().add("test-name");
            Label category = new Label("");
            category.getStyleClass().add("test-category");
            Label price = new Label(String.format("$%.2f", priceVal));
            price.getStyleClass().add("test-price");

            HBox priceRow = new HBox(8);
            priceRow.getChildren().addAll(price, add);
            HBox.setHgrow(price, Priority.ALWAYS);

            card.getChildren().addAll(name, category, priceRow);
            testsGrid.add(card, col, row);

            col++;
            if (col >= 2) {
                col = 0;
                row++;
            }
        }
    }

    private void addTestToSummary(String testName, double price) {
        if (addedTests.contains(testName)) {
            System.out.println("El examen ya fue agregado: " + testName);
            return;
        }
        addedTests.add(testName);

        subtotal += price;
        updateTotals();

        if (summaryTable != null) {
            summaryTable.getItems().add(new Concept(0, testName, price));
        }
    }

    private void updateTotals() {
        String s = String.format("$%.2f", subtotal);
        if (subtotalLabel != null)
            subtotalLabel.setText(s);
        if (totalAmount != null)
            totalAmount.setText(s);
    }
}
