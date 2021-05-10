/**
 * Name : Daiphy Lee & Daniel Yermashev
 * Date : May 10, 2021
 * Teacher : Mr. Ho
 * Description : Benford's Law Assignement
 */

//import classes
import java.util.Scanner; // scanner
// File imports
import java.io.*;

// jfreechart imports (bar graph)
// Get the included jar file in the github
// In VSCode, Explorer > JAVA PROJECTS > Referenced Libraries > Add library (the jar file)
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.ChartUtilities;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

class Final {
    public static void main(String[] args) throws IOException {
        // initialize Scanner package
        Scanner reader = new Scanner(System.in);
        // initialize the array
        double[] percentArr = new double[10];

        String filePath;
        boolean existingFile = false;

        do {
            // prompt user to enter file path
            System.out.println(
                    "Enter the file path. (To find this open the file on VS code, Right click on the tab and click 'Copy Path')");
            filePath = reader.nextLine();
            System.out.println(
                    "Your path should look like : /Users/daiphylee/11 ComSci Java Lessons/Assignments/Benford's Law/Benfords-Law/sales.csv");

            // System.out.println();

            // read file method
            existingFile = readFile(filePath, percentArr);
        } while (existingFile == false);

        System.out.println("Generating the csv file and bar graph image ......");
        BarGraph(percentArr);
        CVSfile(reader, filePath, percentArr);

        System.out.println("\nEnd of Program");

        reader.close();

    }

    /**
     * Description: Reads file takes the 4th character of each line and determines
     * the amount of times each number appears as the leading digit
     * 
     * @author Daiphy lee
     * @param path    file patht the user enters
     * @param percent the pervent array that is updated with the frequency of each
     *                number appeared
     * @return false when file exists
     * @throws IOException
     */

    public static boolean readFile(String path, double[] percent) throws IOException {

        // initialize filereader and bufferedreader package
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);

        try {

            // initialize the array and integer
            int[] tally = new int[10]; // array that counts the amount of each num in the file
            int count = 0; // integer that counts amount of lines in the file
            String s;

            // for loop to read each line in the file until it reaches a blank line
            while ((s = br.readLine()) != null) {
                // ld is the leading digit of the number
                String ld = Character.toString(s.charAt(4));
                // convert the leading digit from a string to a char array
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

            br.close();

        }
        // Program cannot find file
        catch (IOException e) {
            System.out.println("An Error has occurred. File not Found.");
            return false;
        }
        return true;

    }

    /**
     * Description: Determines the percantage of each number's frequency in the file
     * using the tally and count from the read file method
     * 
     * @author Daiphy lee
     * @param percent the percent array that is updated with the frequency of each
     *                number appeared
     * @param tally   the counted amount of each time, a number appears as the
     *                leading digit
     * @param count   the total amount of lines
     */

    public static void percentageArr(double[] percent, int[] tally, int count) {
        int totalFrequency = sumArrElements(tally);
        // goes through each digits frequency
        for (int i = 1; i < tally.length; i++) {
            // rounds the percent to the hundredth decimal place
            percent[i] = Math.round((tally[i] * 1.0 / totalFrequency) * 100 * 100.0) / 100.0;
            // DELETE LATER
            // System.out.println(
            // "[" + i + "][" + tally[i] + "][" + count + "][" + Math.round(percent[i] *
            // 100.0) / 100.0 + "]");
            // System.out.println(percent[i]);
        }
        // let user know if there's fraud && calls on fraud method
        System.out.println("Is there Fraud present? " + fraudValidation(percent));
    }

    /**
     * Description: Getting the sum in the array
     * 
     * @author Daniel Yermashev
     * @param arr
     * @return
     */
    public static int sumArrElements(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i]; // accumulator variable
        }
        return sum;
    }

    /**
     * Description: Printing the results on too an cvs document
     * 
     * @param reader the pervent array that is updated with the frequency of each
     *               number appeared
     * @author Daniel Yermashev
     * @param path    file patht the user enters
     * @param percent the percent array that is updated with the frequency of each
     *                number appeared
     */
    public static void CVSfile(Scanner reader, String path, double[] percent) {

        String content = "";
        try {
            // The file name
            String fileName = "results.csv";

            // Creating the CVS file
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            PrintWriter pw = new PrintWriter(bw);

            // Title of file
            String title = "Relative Frequency";

            // Information in the file
            pw.print((title + "\n").concat(contentCSV(percent, content)));
            bw.close();
            pw.close();

            // if the file fails to generate
        } catch (Exception e) {
            System.out.println("The file could not generate");
        }

    }

    /**
     * Description: the content that will be printed on the cvs file
     * 
     * @param percent the percent array that is updated with the frequency of each
     *                number appeared
     * @author Daniel Yermashev
     * @param content
     * @return the string that will be printed
     */
    public static String contentCSV(double[] percent, String content) {
        // For looping to read each line
        for (int i = 1; i < percent.length; i++) {
            content += ((i) + " | " + percent[i] + "\n");
        }
        return content;
    }

    /**
     * Description: Determines if there is fraud present by checking if the
     * frequency percentage of 1 in the file is in the range of 29 to 32
     * 
     * @author Daiphy lee
     * @param arr the percentage frequency array
     * @return yes if fraud is present return no is fraud is not present
     */
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

    /**
     * Description: Generating a bar graph with the information provided
     * 
     * @author Daniel Yermashev
     * @param percent the percentage array
     */
    public static void BarGraph(double[] percent) {
        // x-axis labels
        String[] labelsArr = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        String graphTitle = "Benford's Law Distribution Leading Digit";
        String xAxisTitle = "First Digit";
        String yAxisTitle = "Relative Frequency (%)";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); // jfreechart dataset

        for (int i = 1; i < percent.length; i++) {
            // (y-value, legend, x-value)
            dataset.addValue(percent[i], "Relative Frequency", labelsArr[i - 1]);
        }

        JFreeChart barChart = ChartFactory.createBarChart(graphTitle, // Graph title
                xAxisTitle, yAxisTitle, // x-axis title, y-axis title
                dataset, PlotOrientation.VERTICAL, // range axis is vertical
                true, true, false);

        int width = 640; // Image Width
        int height = 480; // Image Height
        String chartFileName = "BenfordBarChart.png";
        File barChartName = new File(chartFileName); // File name

        System.out.println("View the bar graph image in this program's directory.");

        try {
            ChartUtilities.saveChartAsPNG(barChartName, barChart, width, height); // save chart as png image file
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }

}
