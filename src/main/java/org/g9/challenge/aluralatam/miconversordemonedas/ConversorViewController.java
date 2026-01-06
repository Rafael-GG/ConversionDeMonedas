package org.g9.challenge.aluralatam.miconversordemonedas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.UnaryOperator;

public class ConversorViewController {

    @FXML
    private ComboBox<String> cmbDeMonedas;

    @FXML
    private ComboBox<String> cmbAMonedas;

    @FXML
    private MediaView mediaView;

    @FXML
    private TextField txtMonto;

    @FXML
    private Label lblResultado;

    @FXML
    private Label lblRate;

    @FXML
    private Label lblUltimaAct;

    @FXML
    private Label lblProximaAct;

    @FXML
    private ScrollBar sclBar;


    ObservableList<String> items = FXCollections.observableArrayList();

    public void initialize() {
        //Carga el video de fondo
        File videoFile = new File("C:/Users/pcwal/Documents/Alura-ONE/ConversorDeMonedas/src/videos/cm.mp4");
        Media media = new Media(videoFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // Cargar los códigos de monedas al iniciar la aplicación
        cargarMonedas();

        //Controla el txtMonto para permitir solo números y un punto decimal
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            // Permite solo números con hasta un punto decimal
            if (newText.matches("\\d*(\\.\\d*)?")) {
                return change;
            }
            return null; // Rechaza el cambio
        };
        txtMonto.setTextFormatter(new TextFormatter<>(filter));

        // Usar un FilteredList independiente para cada ComboBox para evitar que las búsquedas
        // en uno afecten al otro (esto también evita problemas de selección incorrecta).
        FilteredList<String> filteredDe = new FilteredList<>(items, p -> true);
        FilteredList<String> filteredA = new FilteredList<>(items, p -> true);

        cmbDeMonedas.setItems(filteredDe);
        cmbAMonedas.setItems(filteredA);

        // Listener para búsqueda case-insensitive en cmb
        activarBusqueda(cmbDeMonedas, filteredDe);
        activarBusqueda(cmbAMonedas, filteredA);
    }

    private void activarBusqueda(ComboBox<String> combo, FilteredList<String> filtered) {

        combo.getEditor().textProperty().addListener((obs, oldText, newText) -> {

            if (newText == null || newText.isEmpty()) {

                filtered.setPredicate(s -> true);
                return;
            }

            ObservableList<String> filtrados = FXCollections.observableArrayList();

            for (String item : items) {
                if (item.toLowerCase().contains(newText.toLowerCase())) {
                    filtrados.add(item);
                }
            }

            combo.setItems(filtrados);
            //combo.hide();
            combo.show();
        });
    }


    private void cargarMonedas(){
        CodigoDeMonedas codigosActuales = ConsultaApi.consultarCodigosDeMonedas();

        // Manejar respuestas nulas de la API para evitar NullPointerException
        if (codigosActuales == null || codigosActuales.supported_codes() == null) {
            this.items.clear();
            return;
        }

        // Rellenar la lista existente en lugar de reasignarla, para no romper los FilteredList
        var lista = codigosActuales.supported_codes().stream()
                .map(moneda -> moneda.get(0) + " - " + moneda.get(1)).toList();
        this.items.setAll(lista);
    }

    @FXML
    public void realizarConversion(){
        // Validaciones básicas
        String montoText = txtMonto.getText();
        String deValue = cmbDeMonedas.getValue();
        String aValue = cmbAMonedas.getValue();

        if (montoText == null || montoText.isBlank()) {
            showAlert("Monto inválido", "Ingrese un monto válido.");
            return;
        }
        if (deValue == null || aValue == null) {
            showAlert("Moneda no seleccionada", "Seleccione la moneda de origen y destino.");
            return;
        }

        double monto;
        try {
            monto = Double.parseDouble(montoText);
        } catch (NumberFormatException ex) {
            showAlert("Monto inválido", "El monto debe ser un número. Ej: 10.5");
            return;
        }

        String deMoneda = deValue.substring(0, Math.min(3, deValue.length()));
        String aMoneda = aValue.substring(0, Math.min(3, aValue.length()));

        Moneda miConversion = ConsultaApi.ConvertirMoneda(deMoneda, aMoneda, monto);

        // Manejar respuestas nulas de la API de conversión
        if (miConversion == null) {
            showAlert("Error de conversión", "No se pudo obtener la conversión desde la API.");
            return;
        }

        Locale localeAR = new Locale("es", "AR");
        NumberFormat formato = NumberFormat.getNumberInstance(localeAR);
        formato.setMaximumFractionDigits(0);
        formato.setMaximumFractionDigits(2);

        String resultadoFormateado = null;
        String rateFormateado = null;
        String resultado = miConversion.conversion_result();
        String rate = miConversion.conversion_rate();
        if (resultado == null || resultado.isBlank()) {
            showAlert("Error de conversión", "La API devolvió un resultado inválido.");
            return;
        }else {
            resultadoFormateado = formato.format(Double.parseDouble(resultado));
            rateFormateado = formato.format(Double.parseDouble(rate));
        }

        lblResultado.setText(montoText + " " + deValue.substring(0, 3) + " equivalen a " + resultadoFormateado + " " + aValue.substring(0, 3));
        lblRate.setText("Tasa de Conversion: " + rateFormateado);
        lblUltimaAct.setText("Ultima Actualizacion: " + miConversion.time_last_update_utc().substring(0, 22));
        lblProximaAct.setText("Proxima Actualizacion: " + miConversion.time_next_update_utc().substring(0, 22));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Método público para inicializar la Stage desde el controlador
//    public void initStage(Stage stage) {
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.setTitle("Conversor de Monedas");
//        stage.show();
//    }
}
