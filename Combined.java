/**
 * 
 */

//import classes
import java.util.Scanner;
import java.io.*;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Combined {
    public static void main(String[] args) throws IOException {
        // initialize Scanner package
        Scanner reader = new Scanner(System.in);
        // initialize the array
        double[] percentArr = new double[10];

        // prompt user to enter file path
        System.out.println(
                "Enter the file path. (To find this open the file on VS code, Right click on the tab and click 'Copy Path')");
        System.out.println(
                "Your path should look like : /Users/daiphylee/11 ComSci Java Lessons/A. Assignments/Benford's Law/Benfords-Law/sales.csv ");
        String filePath = reader.nextLine();

        // read file method
        readFile(filePath, percentArr);

        BarGraph chart = new BarGraph("Digit Frequency", "Benfords law distribution leading digit", percentArr);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);

    }

    public static void readFile(String path, double[] percent) throws IOException {

        // initialize filereader and bufferedreader package
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);

        try {

            // initialize the array and integer
            int[] tally = new int[10]; // array that counts the amount of each num in the file
            int count = 0; // integer that counts amount of lines in the file

            // for loop to read each line in the file until it reaches a blank line
            for (String s = br.readLine(); s != null; s = br.readLine()) {
                // ld is the leading digit of the number
                String ld = Character.toString(s.charAt(4));
                // convert the leading digit from a string to a char arrat
                char[] digits = ld.toCharArray();
                // for each loop to go through all the leading digits and change only the
                // integers into characters
                for (char digit : digits) {
                    if (Character.isDigit(digit)) {
                        count++; // counts the amount of lines
                        // counts and tallys the amount of each num
                        tally[Integer.parseInt(Character.toString(digit))]++;
                        // breaks the for loop when all of the int are converted into characters
                        break;
                    }
                }
            }
            // calls on method to find the percent
            percentageArr(percent, tally, count);

        }
        // Program cannot find file
        catch (IOException e) {
            System.out.println("An Error has occurred. File not Found.");
        }
        // to close reader
        finally {
            try {
                if (br != null) {
                    br.close();
                }
            }
            // for catching errors
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public static void percentageArr(double[] percent, int[] tally, int count) {
        // goes through each digits frequency
        for (int i = 1; i < 10; i++) {
            // finds the percentage of each leading digit number
            percent[i] = tally[i] == 0 ? 0.0 : (((double) tally[i]) / count) * 100;
            // rounds the percent to the hundredth decimal place
            percent[i] = Math.round(percent[i] * 100.0) / 100.0;
            System.out.println(
                    "[" + i + "][" + tally[i] + "][" + count + "][" + Math.round(percent[i] * 100.0) / 100.0 + "]");
            System.out.println(percent[i]);
        }
        // let user know if there's fraud && calls on fraud method
        System.out.println("Is there Fraud present? " + fraudValidation(percent));
    }

    public static String fraudValidation(double[] arr) {
        // if the frequency percentage of 1 isn't in range then return yes -> yes fraud
        if (arr[0] >= 32.0 && arr[0] <= 29.0) {
            return "Yes";
        }
        // else return no -> no fraud
        else {
            return "No";
        }
    }

    public void BarGraph(String applicationTitle, String chartTitle, double[] percent) {
        JFreeChart barChart = ChartFactory.createBarChart(chartTitle, "Digits", "Frequency", createDataset(percent),
                PlotOrientation.VERTICAL, true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
    }

    private CategoryDataset createDataset(double[] percent) {

        String[] didgits = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };

        DefaultCategoryDataset data = new DefaultCategoryDataset();

        for (int i = 0; i < didgits.length; i++) {
            data.addValue(percent[i], "Digit Frequency", didgits[i]);
        }

        JFreeChart chart = ChartFactory.createBarChart("Benfords Law", "Frequency", "", data, PlotOrientation.VERTICAL,
                true, true, false);

        return data;
    }

}
