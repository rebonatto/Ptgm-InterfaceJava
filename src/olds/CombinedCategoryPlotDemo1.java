package olds;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import persistencia.Conexao;




/**
 * A demo for the {@link CombinedDomainCategoryPlot} class.
 */
public class CombinedCategoryPlotDemo1 extends ApplicationFrame {
    private static int MAXTOMADA = 12;
    private static int TEMPOATUALIZATABELA = 2000;
    private int tomada[] = new int[MAXTOMADA];
    private static int sala = 0;
    private String mensagem;
    double sen[] = new double[12];
    double senp[] = new double[12];
    double coss[] = new double[12];
    double cossp[] = new double[12];
    double somatorio[] = new double[12];
    double somatoriop[] = new double[12];
    String type[] = new String[256];
    double val[] = new double[256];
    double valP[] = new double[256];
    int codharmonica = 0;
    int codharmonicaP = 0;
    double vm = 0;
    double vmP = 0;
    float tempo[] = new float[256];
    int tom = 0;
    int testegraf = 0;
    int codequip[] = new int[MAXTOMADA];
    int tomadas[] = new int[MAXTOMADA];
    double offset[] = new double[MAXTOMADA];
    double valormedio[] = new double[MAXTOMADA];
    double eficaz[] = new double[MAXTOMADA];
    double gain[] = new double[MAXTOMADA];
    int codonda = 0;
    int ondacod[] = new int[MAXTOMADA];
    int tomad = 0;

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public CombinedCategoryPlotDemo1(final String title) throws SQLException {

        super(title);

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }

    /**
     * Creates a dataset.
     *
     * @return A dataset.
     */
    public CategoryDataset createDataset1() throws SQLException {

        DefaultCategoryDataset result = new DefaultCategoryDataset();

        for (int i = 0; i < 256; i++) {
            val[i] = 0;
            valP[i] = 0;
        }


        int _tomada = 1;


       String sql = "select distinct oa.sen as sena, oa.coss as cosa, "
               + "op.sen as senp, op.cos as cosp,  oa.codharmonica as cod, "
               + "cap.valormedio as vm, capp.valormedio as vmp, "
               + "cap.eficaz as eficaza, capp.eficaz as eficazp, capp.codondapadrao, cap.codondaatual, "
               + "cap.offset as offseta, capp.offset as offsetp, "
               + "cap.gain as gaina, capp.gain as gainp "
               + "from capturaondaatual cap, ondaatual oa , "
               + "ondapadrao op, capturaondapadrao capp "
               + "where oa.codcapturaatual =  "
               + "(select max(cap.codondaatual) "
               + "from capturaondaatual cap "
               + "where cap.codequipamento = 5 "
               + "and cap.codtipoonda = 2) "
               + "and op.codcapturapadrao = "
               + "(select max(capp.codondapadrao) "
               + "from capturaondapadrao capp  "
               + "where capp.codequipamento = 5 "
               + "and capp.codtipoonda = 2) "
               + "and oa.codharmonica = op.codharmonica "
               + "and cap.codondaatual = oa.codcapturaatual "
               + "and capp.codondapadrao = op.codcapturapadrao "
               + "and cap.codtipoonda = capp.codtipoonda ";

        // row keys...
        final String series1 = "Reconstruida";
        final String series2 = "Original";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        //ps.setInt(1, _tomada);
        //ps.setInt(2, _tomada);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            codharmonica = rs.getInt("cod");
            sen[codharmonica -1] = rs.getDouble("sena");
            coss[codharmonica -1] = rs.getDouble("cosa");
            senp[codharmonica -1] = rs.getDouble("senp");
            cossp[codharmonica -1] = rs.getDouble("cosp");
            vm = rs.getDouble("vm");
            vmP = rs.getDouble("vmp");
        }

        float t = 0;
        //double vm = 638325;

        for (int i = 0; i < 256; i++) {
            if (i == 0) {
                tempo[i] = t = 0;
            } else {
                tempo[i] = (t + (float) (1.0 / (60 * 256)));
            }
            type[i] = String.valueOf(i);
            //System.out.println("num "+sen[0]+" seno "+Math.sin(sen[0]));
            //System.out.printf("%f\n ", tempo[i]);

            float s = (float) (vm / 2);
            float s2 = (float) (vmP / 2);
            for (int x = 0; x < 12; x++) {
                s += (double) (coss[x] * Math.cos(2 * Math.PI * ((x + 1) * 60) * tempo[i])) + (sen[x] * Math.sin(-2 * Math.PI * ((x + 1) * 60) * tempo[i]));
                s2 += (double) (cossp[x] * Math.cos(2 * Math.PI * ((x + 1) * 60) * tempo[i])) + (senp[x] * Math.sin(-2 * Math.PI * ((x + 1) * 60) * tempo[i]));
            }

            val[i] = (double) ((s * (2.0)) / 256.0);
            valP[i] = (double) ((s2 * (2.0)) / 256.0);
//            if (i < 5) {
//                for(int x = 0; x < 12; x++)
//                    System.out.println("Somaotorio[" + x + "] " + somatorio[x] );
//                System.out.println("Val - "+val[i] + " " + s) ;
//            }
            t = tempo[i];
        }

        for (int i = 0; i < 256; i++) {
            
            result.addValue(val[i], series1, type[i]);
            result.addValue(valP[i], series2, type[i]);
            //System.out.println("val "+val[i]+ "type "+type[i]);

        }

        return result;




    }

    /**
     * Creates a dataset.
     *
     * @return A dataset.
     */
    

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
     * Creates a chart.
     *
     * @return A chart.
     */
    private JFreeChart createChart() throws SQLException {

        final CategoryDataset dataset2 = createDataset1();
        final NumberAxis rangeAxis1 = new NumberAxis("Value");
        rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis1.setAutoRangeIncludesZero(false);
        final LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
        renderer1.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        final CategoryPlot subplot1 = new CategoryPlot(dataset2, null, rangeAxis1, renderer1);
        subplot1.setDomainGridlinesVisible(true);

        

        final CategoryAxis domainAxis = new CategoryAxis("Category");
        final CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);
        plot.add(subplot1, 2);


        final JFreeChart result = new JFreeChart(
            "Combined Domain Category Plot Demo",
            new Font("SansSerif", Font.BOLD, 12),
            plot,
            true
        );
  //      result.getLegend().setAnchor(Legend.SOUTH);
        return result;

    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) throws SQLException {

        final String title = "Combined Category Plot Demo 1";
        final CombinedCategoryPlotDemo1 demo = new CombinedCategoryPlotDemo1(title);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}