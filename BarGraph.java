import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class BarGraph extends ApplicationFrame {

    public BarGraph(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        JFreeChart barChart = ChartFactory.createBarChart(chartTitle, "Category", "Score", createDataset(),
                PlotOrientation.VERTICAL, true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset() {

        String[] didgits = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };

        DefaultCategoryDataset data = new DefaultCategoryDataset();

        for (int i = 0; i < didgits.length; i++) {
            data.addValue("Daiphys part the frequency of the numbers goes here <--", "Digit Frequency", didgits[i]);
        }

        JFreeChart chart = ChartFactory.createBarChart("Benfords Law", "Frequency", "lol", data,
                PlotOrientation.VERTICAL, true, true, false);

        return data;
    }

    public static void main(String[] args) {
        BarGraph chart = new BarGraph("Car Usage Statistics", "Which car do you like?");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}
