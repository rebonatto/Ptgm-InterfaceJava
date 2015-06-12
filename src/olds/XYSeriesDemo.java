package olds;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import persistencia.Conexao;

/**
 * A simple demo showing a dataset created using the {@link XYSeriesCollection} class.
 *
 */
public class XYSeriesDemo extends ApplicationFrame {

    double sen[] = new double[12];
    double coss[] = new double[12];
    double somatorio[] = new double[12];
    String type[] = new String [256];
    double val[] = new double[256];
    int codharmonica = 0;
    float tempo[] = new float [256];

    /**
     * A demonstration application showing an XY series containing a null value.
     *
     * @param title  the frame title.
     */
    public XYSeriesDemo(final String title) throws SQLException {

        super(title);
        String sql = "select codharmonica as cod, sen as sen, coss as cos "
                + "from ondaatual where codcapturaatual =2;";

        final XYSeries series = new XYSeries("Random Data");
        final XYSeries series2 = new XYSeries("Random Data");
//        series.add(1.0, 500.2);
//        series.add(5.0, 694.1);
//        series.add(4.0, 100.0);
//        series.add(12.5, 734.4);
//        series.add(17.3, 453.2);
//        series.add(21.2, 500.2);
//        series.add(21.9, 444);
//        series.add(25.6, 734.4);
//        series.add(30.0, 453.2);



        java.sql.PreparedStatement ps = Conexao.getPreparedStatement(sql);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            codharmonica = rs.getInt("cod");
            sen[codharmonica-1] = rs.getDouble("sen");
            coss[codharmonica-1] = rs.getDouble("cos");

        }


        float t = 0;
        double vm = 638325;

        for(int i=0; i < 256; i++){
            if (i == 0){
                tempo[i] = t = 0;
            }
            else
                tempo[i] = (t + (float) (1.0/(60*256)) );
            type[i] = String.valueOf(i);


            //System.out.println("num "+sen[0]+" seno "+Math.sin(sen[0]));

            //System.out.printf("%f\n ", tempo[i]);
            float s = (float) (vm / 2);
            for (int x = 0; x<12 ; x++){
                somatorio[x] = (double) (coss[x] * Math.cos( 2*Math.PI * ((x+1)*60) *tempo[i])) + (sen[x] * Math.sin(-2*Math.PI * ((x+1)*60) *tempo[i]));
                s += somatorio[x];
                //System.out.println("S-> "+s);
            }
            val[i] = (double) ((s *(2.0)) / 256.0);
            if (i < 5){
//                for(int x = 0; x < 12; x++)
//                    System.out.println("Somaotorio[" + x + "] " + somatorio[x] );
//                System.out.println("Val - "+val[i] + " " + s) ;
            }
            t=tempo[i];
        }



        for (int i=0; i<256; i++){
            series.add(i, val[i]);
        }

        for (int i=0; i<256; i++){
            series2.add(i-100, val[i]-100);
        }

        final XYSeriesCollection data = new XYSeriesCollection(series);
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "XY Series Demo",
            "X",
            "Y",
            data,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    *
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) throws SQLException {

        final XYSeriesDemo demo = new XYSeriesDemo("XY Series Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}