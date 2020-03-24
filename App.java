package l1;
import java.awt.*;
import java.util.HashSet;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class App {
public static double m = 0;
public static double d = 0;
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Charts");

                frame.setSize(600, 400);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                XYDataset ds = createDataset();

                JFreeChart chart = ChartFactory.createXYLineChart("Random signal",
                        "N", "t", ds, PlotOrientation.VERTICAL, true, true,
                        false);


                ChartPanel cp = new ChartPanel(chart);
                JPanel data = new JPanel();

                JLabel label = new JLabel("Mат. очікування: "+ m +"     Дисперсія: " + d );
                data.add(label);

                Container pane = frame.getContentPane();

                pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

                pane.add(cp);
                pane.add(data);

            }
        });

    }

    private static XYDataset createDataset() {
        double[] dataN = new double[4500];
        double[] dataX = new double[4500];
        long[] time = new long[4500];

        int n = 6;
        int w = 2100;
        int nn = 5000;
        double sum = 0;
        double a;
        double f;

        long start, end;


    for (int t = 500; t < nn; t++) {
//        start = 0;
//        end = 0;
        start = System.nanoTime();
        sum = 0;
        a = (Math.random());
        f = (Math.random());
        for (int x = 0; x < n; x++) {
            sum += a * Math.sin(w + f);
        }
        dataN[t-500] = t;
        dataX[t-500] = sum;
        end = System.nanoTime();
        time[t-500] = end - start;
    }


        for(int t = 500; t < nn; t++){
            m += dataX[t-500];
        }

        for(int t = 500; t < nn; t++){
            d += Math.pow(dataX[t-500]+ m, 2);
            d /=(nn-1);
        }

        //double[][] data2 = { {0.4, 0.233, 0.88}, {5, 3, 1} };


        XYSeriesCollection ds = new XYSeriesCollection();


        XYSeries s1 = new XYSeries("s1");
       // XYSeries s2 = new XYSeries("s1");

        for (int i = 0; i < dataN.length; i++) {
            s1.add(dataN[i], time[i]);
           // s2.add(data2[i][0], data2[i][1]);
        }

        ds.addSeries(s1);
        //ds.addSeries(s2);

        return ds;
    }

}
