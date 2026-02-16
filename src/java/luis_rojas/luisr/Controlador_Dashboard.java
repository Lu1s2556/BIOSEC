package luis_rojas.luisr;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class Controlador_Dashboard implements Initializable {
    @FXML private Label lblIngresosTotales;
    @FXML private Label lblPacientesNuevos;
    @FXML private LineChart<String, Number> chartIngresos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatosKPI();
        cargarGrafico();
    }

    private void cargarDatosKPI() {
        // En una app real, esto viene de una base de datos
        lblIngresosTotales.setText("$45,231.89");
        lblPacientesNuevos.setText("+2350");
    }

    private void cargarGrafico() {
        // Configurar datos del gráfico
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Ingresos 2026");

        // Datos simulados para crear la curva
        series.getData().add(new XYChart.Data<>("Lun", 3000));
        series.getData().add(new XYChart.Data<>("Mar", 2500));
        series.getData().add(new XYChart.Data<>("Mie", 5800)); // Pico alto
        series.getData().add(new XYChart.Data<>("Jue", 2000));
        series.getData().add(new XYChart.Data<>("Vie", 1800));
        series.getData().add(new XYChart.Data<>("Sab", 3500));
        series.getData().add(new XYChart.Data<>("Dom", 4000));

        chartIngresos.getData().add(series);

        // Desactivar animaciones pesadas para Windows 7 si es necesario
        chartIngresos.setAnimated(true);
    }
}

    // Inyección de componentes del FXML (Debes poner fx:id en SceneBuilder)
