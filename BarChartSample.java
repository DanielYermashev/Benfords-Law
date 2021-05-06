import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class BarChartSample extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Bar Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
        bc.setTitle("Benfords Law Distribution");
        xAxis.setLabel("Digit");
        yAxis.setLabel("Percent");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data("1", 30.1));
        series1.getData().add(new XYChart.Data("2", 17.6));
        series1.getData().add(new XYChart.Data("3", 12.5));
        series1.getData().add(new XYChart.Data("4", 9.7));
        series1.getData().add(new XYChart.Data("5", 7.9));
        series1.getData().add(new XYChart.Data("6", 6.7));
        series1.getData().add(new XYChart.Data("7", 5.8));
        series1.getData().add(new XYChart.Data("8", 5.1));
        series1.getData().add(new XYChart.Data("9", 4.6));

        Scene scene = new Scene(bc, 800, 600);
        bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
