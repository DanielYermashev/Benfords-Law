import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


import java.util.Scanner; // import scanner class
import jdk.jfr.Percentage;
import java.io.BufferedReader; // import buffer reader class (because scanner likes to skip lines)
import java.io.FileReader; // import class to read files
import java.io.*;
import java.io.IOException;

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

        String filePath = "/Users/daiphylee/11 ComSci Java Lessons/A. Assignments/Benford's Law/Benfords-Law/sales.csv";

        FileReader fr = new FileReader(filePath);
        // initialize bufferedreader 
        BufferedReader br = new BufferedReader(fr);

        int[] tally = new int[]{0,0,0,0,0,0,0,0,0,0};
        int count = 0;

        for (String s = br.readLine(); s != null; s = br.readLine()){
            String ld = Character.toString(s.charAt(4));
            char[] digits = ld.toCharArray();
            for(char digit : digits){
                if(Character.isDigit(digit)){
                    count++;
                    tally[Integer.parseInt(Character.toString(digit))]++;
                    break;
                }
            }
        }
        br.close();

        for(int index = 0; index < 10; index++) {
            double average = tally[index] == 0 ? 0.0 : (((double)tally[index]) / count) * 100;
            System.out.println("[" + index + "][" + tally[index] + "][" + count + "][" + Math.round(average * 100.0) / 100.0 + "]");
        }  
        
    }
}
