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
    private ScrollPane summaryScroll;

    private double subtotal = 0.0;

    @FXML
    public void initialize() {
        if (summaryScroll != null && summaryScroll.getContent() == null) {
            VBox v = new VBox(8);
            summaryScroll.setContent(v);
        }

        if (testsGrid != null) {
            testsGrid.getChildren().clear();
            // Query conceptos from MySQL (XAMPP). Uses default localhost/root with empty
            // password.
            List<Concept> concepts = MySQLDB.getConcepts();
            if (concepts.isEmpty()) {
                // fallback sample
                concepts.add(new Concept(0, "Hemograma", 15.0));
            }

            int col = 0, row = 0;
            for (Concept c : concepts) {
                System.out.println("Prueba cargada desde BD: " + c.getName() + " - " + c.getPrice());

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
                if (col >= 3) {
                    col = 0;
                    row++;
                }
            }
        }

        if (searchField != null) {
            searchField.textProperty().addListener((obs, oldV, newV) -> {
                // placeholder for search/filter behavior
                System.out.println("Buscar: " + newV);
            });
        }
    }

    private void addTestToSummary(String testName, double price) {
        subtotal += price;
        updateTotals();

        if (summaryScroll == null)
            return;
        if (!(summaryScroll.getContent() instanceof VBox)) {
            summaryScroll.setContent(new VBox(8));
        }
        VBox container = (VBox) summaryScroll.getContent();
        Label item = new Label(testName + " — " + String.format("$%.2f", price));
        container.getChildren().add(item);
    }

    private void updateTotals() {
        String s = String.format("$%.2f", subtotal);
        if (subtotalLabel != null)
            subtotalLabel.setText(s);
        if (totalAmount != null)
            totalAmount.setText(s);
    }
}
