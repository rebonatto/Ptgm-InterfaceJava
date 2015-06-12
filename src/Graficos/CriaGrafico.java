/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graficos;

import Uteis.Configuracoes;
import Uteis.Cores;
import Uteis.EncontraPadroes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;
import modelo.CapturaAtual;
import modelo.HarmAtual;
import modelo.HarmPadrao;
import modelo.OndaPadrao;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author rebonatto
 */

public class CriaGrafico {           
    /*
    TIRADOVM: utilizado para verificar se o firmware retirou o valor medio das amostras e fez ajustes na FFT
    0 -> não tirou a VM
    1 -> tirou a VM
    */
    
    private static int TIRADOVM = 1;
    
    public static JFreeChart createChartBarras (Object obj, boolean padrao, boolean valores){        
        JFreeChart ret = null;
        BeanGrafico bean =  null;
        if (obj instanceof CapturaAtual){
            bean = Obj2CapturaAtual(obj);                        
        }
        if (obj instanceof OndaPadrao){
            bean = Obj2OndaPadrao(obj);            
        }
        ret = criaGraficoBarras(bean, padrao, valores);
        return (ret);
    }
    
    public static JFreeChart createChartLinhas (Object obj, boolean padrao){        
        if (obj instanceof CapturaAtual){
            BeanGrafico bean = Obj2CapturaAtual(obj);
            return ( criaGraficoLinhas(bean, padrao) );
        }
        if (obj instanceof OndaPadrao){
            BeanGrafico bean = Obj2OndaPadrao(obj);
            return ( criaGraficoLinhas(bean, padrao) );   
        }
        return (null);
    }
        
    public static JFreeChart criaGraficoBarras(BeanGrafico bean, boolean padrao, boolean valores)  {            
        String eixoy = new String("Normalised Module ");
        // verifica se é fase ou fuga
        if  (bean.getCodEvento().getCodEvento() == 1){ // evento 1 é de fuga
            eixoy = eixoy.concat("(mA)");
        }
        else{
            eixoy = eixoy.concat("(A)");
        }
        if (bean.getHarm().isEmpty()){
            DefaultCategoryDataset result = new DefaultCategoryDataset() ;                                       
                            
            // create the chart...
            final JFreeChart vazio  = ChartFactory.createBarChart(
                "Sem harmonicas Calculadas", // chart title
                "Frequencias (Hz)"         , // x axis label
                eixoy,                       // y axis label
                result,                      // data
                PlotOrientation.VERTICAL,
                false,                       // include legend
                false,                       // tooltips
                false                        // urls
            );
            final CategoryPlot plot = vazio.getCategoryPlot();
            plot.setBackgroundPaint(Color.WHITE);
            
            return vazio;
        }
                                
        final CategoryDataset dataset = newDatasetBarras(bean, padrao);
        
        final JFreeChart result = ChartFactory.createBarChart(
            "",                          // chart title
            "Frequencies (Hz)       ",     // x axis label
            eixoy,   // y axis label
            dataset,                    // data
            PlotOrientation.VERTICAL,
            padrao,                     // include legend
            false,                     // tooltips
            false                      // urls
        );

        result.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = result.getCategoryPlot();
        
        // Definindo valores no eixo y
        double max = (double) DatasetUtilities.findMaximumRangeValue(dataset);
        
        //plot.getRangeAxis().setRange( 0 , max + max * 0.005);
        CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
        //xAxis.set
        
        plot.setBackgroundPaint(Color.WHITE);
        //plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        
        CategoryItemRenderer itemRerender;
     
         //Aki fica o codigo para colocar o valor na barra  
     /*
        if (valores){
            
            itemRerender = plot.getRenderer();  

            itemRerender.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", new DecimalFormat("0.00")));  
            itemRerender.setItemLabelsVisible(true); 
        }
       */ 
        return result;
    }
    
    public static JFreeChart criaGrafico2Barras(CapturaAtual onda1, CapturaAtual onda2)  {       
        String eixoy = new String("Modulo Normalizado ");
        // verifica se é fase ou fuga
        if  (onda1.getCodEvento().getCodEvento() == 1){ // evento 1 é de fuga
            eixoy = eixoy.concat("(mA)");
        }
        else{
            eixoy = eixoy.concat("(A)");
        }
        if ( onda1.getHarmAtual().isEmpty() || onda1.getHarmAtual().isEmpty() ){
            DefaultCategoryDataset result = new DefaultCategoryDataset() ;                                       
                        
            // create the chart...
            final JFreeChart vazio  = ChartFactory.createBarChart(
                "Sem harmonicas Calculadas",  // chart title
                "Frequencias (Hz)"       ,    // x axis label
                eixoy,                       // y axis label
                result,                    // data
                PlotOrientation.VERTICAL,
                false,                     // include legend
                false,                     // tooltips
                false                      // urls
            );
            final CategoryPlot plot = vazio.getCategoryPlot();
            plot.setBackgroundPaint(Color.WHITE);
            
            return vazio;
        }
                                
        final CategoryDataset dataset = newDataset2Barras(onda1, onda2);
        
        final JFreeChart result = ChartFactory.createBarChart(
            "",                          // chart title
            "Frequencias (Hz)       ",     // x axis label
            eixoy,   // y axis label
            dataset,                    // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            false,                     // tooltips
            false                      // urls
        );

        result.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = result.getCategoryPlot();
        
        // Definindo valores no eixo y
        double max = (double) DatasetUtilities.findMaximumRangeValue(dataset);
        
        //plot.getRangeAxis().setRange( 0 , max + max * 0.005);
        CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
        //xAxis.set
        
        plot.setBackgroundPaint(Color.WHITE);
        //plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
     
        return result;
    }
    
    /* Grafico com multiplas formas de onda para uso em comparações */
    public static JFreeChart criaGraficoComparaBarras(Vector <CapturaAtual> fugas)  {            
        String eixoy = new String("Normalised Module ");
        // verifica se é fase ou fuga
        if  (fugas.get(0).getCodEvento().getCodEvento() == 1){ // evento 1 é de fuga
            eixoy = eixoy.concat("(mA)");
        }
        else{
            eixoy = eixoy.concat("(A)");
        }
        if ( fugas.isEmpty() ){
            DefaultCategoryDataset result = new DefaultCategoryDataset() ;                                       
                        
            // create the chart...
            final JFreeChart vazio  = ChartFactory.createBarChart(
                "No Waveform Generated",  // chart title
                "Frequencies (Hz)"       ,     // x axis label
                eixoy,   // y axis label
                result,                    // data
                PlotOrientation.VERTICAL,
                true,                     // include legend
                false,                     // tooltips
                false                      // urls
            );
            final CategoryPlot plot = vazio.getCategoryPlot();
            plot.setBackgroundPaint(Color.WHITE);
            
            return vazio;
        }
                                
        final CategoryDataset dataset = newDatasetComparaBarras(fugas);
        
        final JFreeChart result = ChartFactory.createBarChart(
            "",                          // chart title
            "Frequencies (Hz)       ",   // x axis label
             eixoy,                       // y axis label
             dataset,                    // data
             PlotOrientation.VERTICAL,
             true,                     // include legend
             false,                     // tooltips
             false                      // urls
        );

        result.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = result.getCategoryPlot();
        ((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
        
        // Definindo valores no eixo y
        double max = (double) DatasetUtilities.findMaximumRangeValue(dataset);
        
        //plot.getRangeAxis().setRange( 0 , max + max * 0.005);
        CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
        //xAxis.set
        
        plot.setBackgroundPaint(Color.WHITE);
        //plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        
        
        for(int i = 0; i < dataset.getRowCount(); i++){
            result.getCategoryPlot().getRenderer(0).setSeriesPaint(i, Cores.getCorGrafico(i));              
        }
        
/*        Aki para graficos monocromáticos

        result.getCategoryPlot().getRenderer(0).setSeriesPaint(0, Color.BLACK);
        result.getCategoryPlot().getRenderer(0).setSeriesPaint(1, Color.LIGHT_GRAY);
*/                
        return result;
    }
    
    public static JFreeChart criaGraficoLinhas(BeanGrafico bean, boolean padrao) {
        String eixoy = new String("Current ");
        // verifica se é fase ou fuga
        if  (bean.getCodEvento().getCodEvento() == 1){ // evento 1 é de fuga
            eixoy = eixoy.concat("(mA)");
        }
        else{
            eixoy = eixoy.concat("(A)");
        }
        if (bean.getHarm().isEmpty()){
            XYSeriesCollection  result = new XYSeriesCollection();       
            XYSeries series1 = new XYSeries("Vazia");                   
            result.addSeries(series1);      
            
            // create the chart...            
            final JFreeChart vazio = ChartFactory.createXYLineChart(
                "No Waveform", // chart title
                "Time (ms)",                  // x axis label
                eixoy,               // y axis label
                result,                         // data
                PlotOrientation.VERTICAL,
                false,                        // include legend
                false,                        // tooltips
                false                         // urls
            );
            final XYPlot plot = vazio.getXYPlot();
            plot.setBackgroundPaint(Color.WHITE);
            return vazio;
        }
        XYDataset dataset = newDatasetOnda(bean, padrao);
                        
        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "",                        // chart title
            "Time (ms)",              // x axis label
            eixoy,           // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            padrao,                     // include legend
            false,                     // tooltips
            false                      // urls
        );
        chart.setBackgroundPaint(Color.white);
       
        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        
        // Definindo valores no eixo y
        double min = (double) DatasetUtilities.findMinimumRangeValue(dataset);
        double max = (double) DatasetUtilities.findMaximumRangeValue(dataset); 
        /*
        System.out.println("Minimo " + min);
        System.out.println("Máximo " + max);
        
        plot.getRangeAxis().setRange(min - min * 0.005, //min
                                     max + min * 0.005);
        */
         
        plot.setBackgroundPaint(Color.WHITE);
    //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
                
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        
        renderer.setBaseStroke(new BasicStroke(0.5f));
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);
        if (padrao){
            renderer.setSeriesLinesVisible(1, true);
            renderer.setSeriesShapesVisible(1, false);
        }
        
        plot.setRenderer(renderer);
                
        return chart;        
    }      
    
    public static JFreeChart criaGrafico2Linhas(CapturaAtual onda1, CapturaAtual onda2) {
        String eixoy = new String("Current ");
        // verifica se é fase ou fuga
        if  (onda1.getCodEvento().getCodEvento() == 1){ // evento 1 é de fuga
            eixoy = eixoy.concat("(mA)");
        }
        else{
            eixoy = eixoy.concat("(A)");
        }
        if (onda1.getHarmAtual().isEmpty() || onda2.getHarmAtual().isEmpty()){
            XYSeriesCollection  result = new XYSeriesCollection();       
            XYSeries series1 = new XYSeries("Vazia");                   
            result.addSeries(series1);      
            
            // create the chart...            
            final JFreeChart vazio = ChartFactory.createXYLineChart(
                "No Waveform", // chart title
                "Time (ms)",                  // x axis label
                eixoy,               // y axis label
                result,                         // data
                PlotOrientation.VERTICAL,
                false,                        // include legend
                false,                        // tooltips
                false                         // urls
            );
            final XYPlot plot = vazio.getXYPlot();
            plot.setBackgroundPaint(Color.WHITE);
            return vazio;
        }
        XYDataset dataset = newDataset2Ondas(onda1, onda2); 
                        
        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "",                        // chart title
            "Time (ms)",              // x axis label
            eixoy,           // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            false,                     // tooltips
            false                      // urls
        );

        chart.setBackgroundPaint(Color.white);
       
        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        
        // Definindo valores no eixo y
        double min = (double) DatasetUtilities.findMinimumRangeValue(dataset);
        double max = (double) DatasetUtilities.findMaximumRangeValue(dataset);         
        
        plot.getRangeAxis().setRange(min - min * 0.005, //min
                                     max + min * 0.005);
        plot.setBackgroundPaint(Color.WHITE);
    //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
                
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        
        renderer.setBaseStroke(new BasicStroke(0.5f));
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesShapesVisible(1, false);
                
        plot.setRenderer(renderer);
                
        return chart;        
    } 
    
    public static JFreeChart criaGraficoComparaLinhas(Vector <CapturaAtual> fugas) {
        String eixoy = new String("Current ");
        // verifica se é fase ou fuga
        if  (fugas.get(0).getCodEvento().getCodEvento() == 1){ // evento 1 é de fuga
            eixoy = eixoy.concat("(mA)");
        }
        else{
            eixoy = eixoy.concat("(A)");
        }
        if (fugas.isEmpty()){
            XYSeriesCollection  result = new XYSeriesCollection();       
            XYSeries series1 = new XYSeries("Vazia");                   
            result.addSeries(series1);      
            
            // create the chart...            
            final JFreeChart vazio = ChartFactory.createXYLineChart(
                "No Waveform" ,                  // chart title
                "Time (ms)",                  // x axis label
                eixoy,               // y axis label
                result,                         // data
                PlotOrientation.VERTICAL,
                false,                        // include legend
                false,                        // tooltips
                false                         // urls
            );
            final XYPlot plot = vazio.getXYPlot();
            plot.setBackgroundPaint(Color.WHITE);
            return vazio;
        }
        XYDataset dataset = newDatasetComparaOndas(fugas);                 
                        
        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "",                        // chart title
            "Time (ms)",              // x axis label
            eixoy,           // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            false,                     // tooltips
            false                      // urls
        );

        chart.setBackgroundPaint(Color.white);
       
        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        
        // Definindo valores no eixo y
        double min = (double) DatasetUtilities.findMinimumRangeValue(dataset);
        double max = (double) DatasetUtilities.findMaximumRangeValue(dataset);         
        
        plot.getRangeAxis().setRange(min - min * 0.005, //min
                                     max + min * 0.005);
        plot.setBackgroundPaint(Color.WHITE);
    //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
                
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        
        renderer.setBaseStroke(new BasicStroke(0.5f));
        for(int i = 0; i < dataset.getSeriesCount(); i++){
            renderer.setSeriesLinesVisible(i, true);
            renderer.setSeriesShapesVisible(i, false);
            renderer.setSeriesPaint(i, Cores.getCorGrafico(i));
        }            
        
        /* Código para ter linhas tracejadas
        renderer.setSeriesStroke(1, 
                new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.CAP_ROUND,
                                20.0f, new float[] {5.0f}, 0.0f) );
               
        renderer.setDrawSeriesLineAsPath(true); 
        /* Final do Codigo para tracejadas */
                
        plot.setRenderer(renderer);
                
        return chart;        
    } 
     
    public static CategoryDataset newDatasetBarras(BeanGrafico bean, boolean padrao) {
        //metodo que cria um CategoryDataset para ser usado em grafico de Barras
        OndaPadrao ondp = null;
        CapturaAtual cap = null;
        
        float sen[] = new float[Configuracoes.HARMONICAS];
        float cos[] = new float[Configuracoes.HARMONICAS];
        float senpad[] = new float[Configuracoes.HARMONICAS];
        float cospad[] = new float[Configuracoes.HARMONICAS];
        float f;
        
        int j, i = 0;
        
        DefaultCategoryDataset result = new DefaultCategoryDataset() ;               
        
        j=0;
        for (BeanHarmonica oa : bean.getHarm()){
            sen[j] = oa.getSen();
            cos[j] = oa.getCos();
            j++;        
        }
        
        if (padrao){
            //Aqui encontra o padrao adequado
            cap = BeanGrafico2CapturaAtual(bean);
            ondp = EncontraPadroes.minDifValorMedio(cap);
            if (ondp != null){
                //pŕeenche vetores senpad e cospad
                j=0;
                for (HarmPadrao hp : ondp.getHarmPadrao()){
                    senpad[j] = hp.getSen();
                    cospad[j] = hp.getCos();
                    j++;        
                }                    
            }           
        }
                
        if (TIRADOVM == 0)
            result.addValue( Math.abs( (bean.getValorMedio() / 256 - bean.getOffset()) / bean.getGain() ), "Atual", "DC");
        else
            result.addValue( Math.abs( (bean.getValorMedio() ) / bean.getGain() ), "Atual", "DC");
        //System.out.print("ComponenteDC: " + Math.abs((bean.getValorMedio() / 256 - bean.getOffset()) / bean.getGain()));
        if (padrao){
            //adiciona o valor da componente DC do padrao
            if (ondp == null){
                result.addValue( 0, "Sem Padrão Cadastrado", "0");           
            }
            else{
                if (TIRADOVM == 0)
                    result.addValue( Math.abs( (ondp.getValorMedio() / 256 - ondp.getOffset()) / ondp.getGain() ), "Padrão", "DC");
                else
                    result.addValue( Math.abs( (ondp.getValorMedio() ) / ondp.getGain() ), "Padrão", "DC");
                //System.out.println("\t" + Math.abs( (ondp.getValorMedio() / 256 - ondp.getOffset()) / ondp.getGain() ));
            }            
        }

        //System.out.println("DC " + String.valueOf(Math.abs( (bean.getValorMedio() / 256 - bean.getOffset()) / bean.getGain() )));
        for (i = 0; i < Configuracoes.HARMONICAS; i++) {
            if (TIRADOVM == 0)
                f = (float) Math.sqrt( sen[i] * sen[i] + cos[i] * cos[i])  / 128  ;  
            else                
                f = (float) Math.sqrt( sen[i] * sen[i] + cos[i] * cos[i])  ;  
            f = f / bean.getGain();
            //result.addValue(f, "Atual", String.valueOf( i + 1 ) );
            //System.out.println("Harmonica " + i + " valor " + String.valueOf(f));
            result.addValue(f, "Atual", String.valueOf(Configuracoes.FREQBASE  * i + Configuracoes.FREQBASE) );
            //System.out.print("Harm" + String.valueOf((i+1)*60) + "Hz : " + f);
            if (padrao){
                // Adiciona o Valor do modulo da onda padrao
                if (ondp != null){
                    if (TIRADOVM == 0)
                        f = (float) Math.sqrt( senpad[i] * senpad[i] + cospad[i] * cospad[i] ) / 128  ;  
                    else
                        f = (float) Math.sqrt( senpad[i] * senpad[i] + cospad[i] * cospad[i] )  ;  
                    f = f / ondp.getGain();
                    result.addValue(f, "Padrão", String.valueOf(Configuracoes.FREQBASE  * i + Configuracoes.FREQBASE));
                    //System.out.println("\t: " + f);
                }
            }
        }
                                                             
        return result;
    }       
    
    public static CategoryDataset newDataset2Barras(CapturaAtual onda1, CapturaAtual onda2) {
        //metodo que cria um CategoryDataset para ser usado em grafico de Barras       
        float sen1[] = new float[Configuracoes.HARMONICAS];
        float cos1[] = new float[Configuracoes.HARMONICAS];
        float sen2[] = new float[Configuracoes.HARMONICAS];
        float cos2[] = new float[Configuracoes.HARMONICAS];
        float f1, f2;
        
        int j, i = 0;
        
        DefaultCategoryDataset result = new DefaultCategoryDataset() ;               
        
        j=0;
        for (HarmAtual oa : onda1.getHarmAtual()){
            sen1[j] = oa.getSen();
            cos1[j] = oa.getCos();
            j++;        
        }
        
        j=0;
        for (HarmAtual oa : onda2.getHarmAtual()){
            sen2[j] = oa.getSen();
            cos2[j] = oa.getCos();
            j++;        
        }
        
        if (TIRADOVM == 0){
            result.addValue( Math.abs( (onda1.getValorMedio() / 256 - onda1.getOffset()) / onda1.getGain() ), "First", "0");
            result.addValue( Math.abs( (onda2.getValorMedio() / 256 - onda2.getOffset()) / onda2.getGain() ), "Second", "0");
        }
        else {
            result.addValue( Math.abs( (onda1.getValorMedio() ) / onda1.getGain() ), "First", "0");
            result.addValue( Math.abs( (onda2.getValorMedio() ) / onda2.getGain() ), "Second", "0");
        }
        //System.out.print("ComponenteDC: " + Math.abs((bean.getValorMedio() / 256 - bean.getOffset()) / bean.getGain()));

        //System.out.println("DC " + String.valueOf(Math.abs( (bean.getValorMedio() / 256 - bean.getOffset()) / bean.getGain() )));
        for (i = 0; i < Configuracoes.HARMONICAS; i++) {
            if (TIRADOVM == 0)
                f1 = (float) Math.sqrt( sen1[i] * sen1[i] + cos1[i] * cos1[i])  / 128  ;  
            else
                f1 = (float) Math.sqrt( sen1[i] * sen1[i] + cos1[i] * cos1[i])    ;  
            f1 = f1 / onda1.getGain();
            
            if (TIRADOVM == 0)
                f2 = (float) Math.sqrt( sen2[i] * sen2[i] + cos2[i] * cos2[i])  / 128  ;  
            else
                f2 = (float) Math.sqrt( sen2[i] * sen2[i] + cos2[i] * cos2[i]);  
            f2 = f2 / onda2.getGain();
            //result.addValue(f, "Atual", String.valueOf( i + 1 ) );
            //System.out.println("Harmonica " + i + " valor " + String.valueOf(f));
            result.addValue(f1, "First", String.valueOf(Configuracoes.FREQBASE  * i + Configuracoes.FREQBASE) );
            //System.out.print("Harm" + String.valueOf((i+1)*60) + "Hz : " + f);
            result.addValue(f2, "Second", String.valueOf(Configuracoes.FREQBASE  * i + Configuracoes.FREQBASE));
        }
                                                             
        return result;
    }       
    
    public static CategoryDataset newDatasetComparaBarras(Vector<CapturaAtual> fugas) {
        //metodo que cria um CategoryDataset para ser usado em grafico de Barras       
        float sen[] = new float[Configuracoes.HARMONICAS];
        float cos[] = new float[Configuracoes.HARMONICAS];
        String freqs[] = new String[Configuracoes.HARMONICAS];
        float f1;
        String label;
        
        int i, j = 0;
        
        for (i = 0; i < Configuracoes.HARMONICAS; i++) 
            freqs[i] = String.valueOf(Configuracoes.FREQBASE  * i + Configuracoes.FREQBASE);
        
        
        DefaultCategoryDataset result = new DefaultCategoryDataset() ;               
        
        for(CapturaAtual cap : fugas){       
            
            label = String.valueOf(cap.getCodCaptura());
            
            for (i = 0; i < Configuracoes.HARMONICAS; i++) 
                sen[i] = cos[i] = 0;
            j=0;
            for (HarmAtual oa : cap.getHarmAtual()){
                sen[j] = oa.getSen();
                cos[j] = oa.getCos();
                j++;        
            }
            
            if (TIRADOVM == 0)
                result.addValue( Math.abs( (cap.getValorMedio() / 256 - cap.getOffset()) / cap.getGain() ), label, "DC");            
            else
                result.addValue( Math.abs( (cap.getValorMedio() ) / cap.getGain() ), label, "DC");            

            //System.out.println("DC " + String.valueOf(Math.abs( (bean.getValorMedio() / 256 - bean.getOffset()) / bean.getGain() )));
            for (i = 0; i < Configuracoes.HARMONICAS; i++) {
                if (TIRADOVM == 0)
                    f1 = (float) Math.sqrt( sen[i] * sen[i] + cos[i] * cos[i])  / 128  ;  
                else
                    f1 = (float) Math.sqrt( sen[i] * sen[i] + cos[i] * cos[i]) ;  
                f1 = f1 / cap.getGain();

                result.addValue(f1, label, freqs[i] );                
            }
        }                                                     
        return result;
    }       
       
    public static XYDataset newDatasetOnda(BeanGrafico bean, boolean padrao){
        //metodo que cria um CategoryDataset para ser usado em grafico de linhas
        OndaPadrao ondp = null;
        CapturaAtual cap = null;
        
        float val[] = new float[Configuracoes.PONTOSONDA];   
        float sen[] = new float[Configuracoes.HARMONICAS];
        float cos[] = new float[Configuracoes.HARMONICAS];
        float senpad[] = new float[Configuracoes.HARMONICAS];
        float cospad[] = new float[Configuracoes.HARMONICAS];
        int i = 0;

        float s, sp, tempo[] = new float[Configuracoes.PONTOSONDA + 1]; // mais um apenas para não dar erro de memoria
        float tempoi[] = new float[Configuracoes.PONTOSONDA + 1];
        XYSeriesCollection  result = new XYSeriesCollection();

        for (i = 0; i < Configuracoes.HARMONICAS; i++) {
            sen[i] = 0;
            cos[i] = 0;
            senpad[i] = 0;
            cospad[i] = 0;            
        }
        
        i=0;
        for (BeanHarmonica oa : bean.getHarm()){
            sen[i] = oa.getSen();
            cos[i] = oa.getCos();
            i++;
        }
        
        XYSeries series1 = new XYSeries("Actual");
        XYSeries series2 = null;
        
        if (padrao){
            //Aqui encontra o padrao adequado
            cap = BeanGrafico2CapturaAtual(bean);
            ondp = EncontraPadroes.minDifValorMedio(cap);
            if (ondp != null){
                //pŕeenche vetores senpad e cospad
                i=0;
                for (HarmPadrao hp : ondp.getHarmPadrao()){
                    senpad[i] = hp.getSen();
                    cospad[i] = hp.getCos();
                    i++;        
                }                    
                series2 = new XYSeries("Standard");
            }
            else
                series2 = new XYSeries("No Standard");           
        }              
                       
        tempo[0] = 0;  //(float) 1 / 60; 
        tempoi[0]=1/Configuracoes.FREQBASE;
        for (i = 0; i < Configuracoes.PONTOSONDA; i++) {                
            if (TIRADOVM == 0)
                s = (float) (bean.getValorMedio() / 2);
            else
                s = (float) bean.getValorMedio() ;
            

            for (int x = 0; x < Configuracoes.HARMONICAS; x++) {
                if (TIRADOVM == 0)
                    s += (float) (sen[x] * Math.sin(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i])) + 
                                 (cos[x] * Math.cos(-2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i]));
                else
                    s += (float) (sen[x] * Math.sin(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i])) + 
                                 (cos[x] * Math.cos(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i]) );
            }
            /* Para testes com valor de seno invertido.
            for (int x = 0; x < Configuracoes.HARMONICAS; x++) {
                s += (float) (sen[x] * Math.cos(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempoi[i])) + (cos[x] * Math.sin(-2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempoi[i]));
            } */
            if (TIRADOVM == 0){
                val[i] = (int) ((s * (2.0)) / 256.0);
                val[i] = (val[i] - bean.getOffset() ) ;
            }
            else
                val[i] = s;
            
            //val[i] = (val[i] ) / bean.getGain();
            val[i] = (val[i] ) / bean.getGain();
                        
            //System.out.println("Ponto" + i + ": " + val[i]);
            
            series1.add(tempo[i] * 1000, val[i]);     
            //System.out.println("Ponto\t" + i + "\tvalor\t" + val[i]);
            if (padrao){
                if (ondp == null){
                    if (i == 0){
                        series2.add(tempo[i] * 1000, 0 );
                    }
                } else{
                    if (TIRADOVM == 0)
                        sp = (float) (ondp.getValorMedio() / 2);
                    else
                        sp = (float) (ondp.getValorMedio() );

                    for (int x = 0; x < Configuracoes.HARMONICAS; x++) {
                        if (TIRADOVM == 0)
                            sp += (double) (senpad[x] * Math.sin(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempoi[i])) + 
                                           (cospad[x] * Math.cos(-2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempoi[i]));
                        else
                            sp += (double) (senpad[x] * Math.sin(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempoi[i])) + 
                                           (cospad[x] * Math.cos(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempoi[i]));
                    }
                    if (TIRADOVM == 0){
                        val[i] = (int) ((sp * (2.0)) / 256.0);                
                        val[i] = (val[i] - ondp.getOffset() ) ;
                    }
                    else
                        val[i] = sp;

                    val[i] = (val[i] ) / ondp.getGain();

                    series2.add(tempo[i] * 1000, val[i] );    
                    //System.out.println("\t " + val[i]);
                }
            }
            tempo[i+1] = (tempo[i] + (float) (1.0 / (Configuracoes.FREQBASE * 256)));        
            tempoi[i+1] = (tempoi[i] - (float) (1.0 / (Configuracoes.FREQBASE * 256)));        
        }
        
        result.addSeries(series1);
        if (padrao){
            result.addSeries(series2);
        }
        return result;
    }                    
    
    public static XYDataset newDataset2Ondas(CapturaAtual onda1, CapturaAtual onda2){
        //metodo que cria um CategoryDataset para ser usado em grafico de linhas                
        float valOnda1[] = new float[Configuracoes.PONTOSONDA];   
        float valOnda2[] = new float[Configuracoes.PONTOSONDA];
        float sen1[] = new float[Configuracoes.HARMONICAS];
        float cos1[] = new float[Configuracoes.HARMONICAS];
        float sen2[] = new float[Configuracoes.HARMONICAS];
        float cos2[] = new float[Configuracoes.HARMONICAS];
        int i;

        float s1, s2, sp, tempo[] = new float[Configuracoes.PONTOSONDA + 1]; // mais um apenas para não dar erro de memoria
        float tempoi[] = new float[Configuracoes.PONTOSONDA + 1];
        XYSeriesCollection  result = new XYSeriesCollection();

        for (i = 0; i < Configuracoes.HARMONICAS; i++) {
            sen1[i] = 0;
            cos1[i] = 0;
            sen2[i] = 0;
            cos2[i] = 0;            
        }
        
        i=0;
        for (HarmAtual oa : onda1.getHarmAtual()){
            sen1[i] = oa.getSen();
            cos1[i] = oa.getCos();
            i++;
        }
        
        i=0;
        for (HarmAtual oa : onda2.getHarmAtual()){
            sen2[i] = oa.getSen();
            cos2[i] = oa.getCos();
            i++;
        }
        
        XYSeries series1 = new XYSeries("First");
        XYSeries series2 = new XYSeries("Second");
                        
        tempo[0] = 0;  //(float) 1 / 60; 
        tempoi[0]=1/Configuracoes.FREQBASE;
        for (i = 0; i < Configuracoes.PONTOSONDA; i++) {  
            if (TIRADOVM == 0){
                s1 = (float) (onda1.getValorMedio() / 2);
                s2 = (float) (onda2.getValorMedio() / 2);
            }
            else{
                s1 = (float) (onda1.getValorMedio() );
                s2 = (float) (onda2.getValorMedio() );
            }

            for (int x = 0; x < Configuracoes.HARMONICAS; x++) {
                if (TIRADOVM == 0){
                    s1 += (float) (sen1[x] * Math.sin(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i])) + 
                                  (cos1[x] * Math.cos(-2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i]));
                    s2 += (float) (sen2[x] * Math.sin(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i])) + 
                                  (cos2[x] * Math.cos(-2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i]));
                }
                else{
                    s1 += (float) (sen1[x] * Math.sin(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i])) + 
                                  (cos1[x] * Math.cos(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i]));
                    s2 += (float) (sen2[x] * Math.sin(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i])) + 
                                  (cos2[x] * Math.cos(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i]));
                }
            }
            /* Para testes com valor de seno invertido.
            for (int x = 0; x < Configuracoes.HARMONICAS; x++) {
                s += (float) (sen[x] * Math.cos(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempoi[i])) + (cos[x] * Math.sin(-2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempoi[i]));
            } */
            if (TIRADOVM == 0){
                valOnda1[i] = (int) ((s1 * (2.0)) / 256.0);
                valOnda1[i] = (valOnda1[i] - onda1.getOffset() ) ;
            }
            else
                valOnda1[i] = s1 ;
            
            if (TIRADOVM == 0){
                valOnda2[i] = (int) ((s2 * (2.0)) / 256.0);
                valOnda2[i] = (valOnda2[i] - onda2.getOffset() );
            }
            else
                valOnda2[i] = s2;

            
            valOnda1[i] = valOnda1[i] / onda1.getGain();
            valOnda2[i] = valOnda2[i] / onda2.getGain();
            
            //System.out.println("i " + i + " Tempo " + tempo[i] + " valor " + val[i]);
            //System.out.print("Ponto" + i + ": " + valOnda1[i]);
            series1.add(tempo[i] * 1000, valOnda1[i]);            
            series2.add(tempo[i] * 1000, valOnda2[i]);            
            tempo[i+1] = (tempo[i] + (float) (1.0 / (Configuracoes.FREQBASE * 256)));        
            tempoi[i+1] = (tempoi[i] - (float) (1.0 / (Configuracoes.FREQBASE * 256)));        
        }
        
        result.addSeries(series1);
        result.addSeries(series2);

        return result;
    }    
    
    public static XYDataset newDatasetComparaOndas(Vector <CapturaAtual> fugas){
        //metodo que cria um CategoryDataset para ser usado em grafico de linhas                
        float valOnda[] = new float[Configuracoes.PONTOSONDA];           
        float sen[] = new float[Configuracoes.HARMONICAS];
        float cos[] = new float[Configuracoes.HARMONICAS];
        String label;
        int i;

        float s1, s2, sp, tempo[] = new float[Configuracoes.PONTOSONDA + 1]; // mais um apenas para não dar erro de memoria
        float tempoi[] = new float[Configuracoes.PONTOSONDA + 1];
        XYSeriesCollection  result = new XYSeriesCollection();

        for(CapturaAtual cap : fugas){
            label = String.valueOf(cap.getCodCaptura());
            for (i = 0; i < Configuracoes.HARMONICAS; i++) {
                sen[i] = cos[i] = 0;
            }

            i=0;
            for (HarmAtual oa : cap.getHarmAtual()){
                sen[i] = oa.getSen();
                cos[i] = oa.getCos();
                i++;
            }

            XYSeries series1 = new XYSeries(label);

            tempo[0] = 0;  //(float) 1 / 60; 
            tempoi[0]=1/Configuracoes.FREQBASE;
            for (i = 0; i < Configuracoes.PONTOSONDA; i++) {                
                if (TIRADOVM == 0)
                    s1 = (float) (cap.getValorMedio() / 2);
                else
                    s1 = (float) (cap.getValorMedio());

                for (int x = 0; x < Configuracoes.HARMONICAS; x++) {
                    if (TIRADOVM == 0)
                        s1 += (float) (sen[x] * Math.sin(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i])) + 
                                      (cos[x] * Math.cos(-2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i]));
                    else
                        s1 += (float) (sen[x] * Math.sin(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i])) + 
                                      (cos[x] * Math.cos(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i]));
                }
                /* Para testes com valor de seno invertido.
                for (int x = 0; x < Configuracoes.HARMONICAS; x++) {
                    s += (float) (sen[x] * Math.cos(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempoi[i])) + (cos[x] * Math.sin(-2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempoi[i]));
                } */
                if (TIRADOVM == 0){
                    valOnda[i] = (int) ((s1 * (2.0)) / 256.0);
                    valOnda[i] = (valOnda[i] - cap.getOffset() ) ;
                }
                else
                    valOnda[i] = (s1 );
                
                valOnda[i] = (valOnda[i]) / cap.getGain();

                series1.add(tempo[i] * 1000, valOnda[i]);                            
                tempo[i+1] = (tempo[i] + (float) (1.0 / (Configuracoes.FREQBASE * 256)));        
                tempoi[i+1] = (tempoi[i] - (float) (1.0 / (Configuracoes.FREQBASE * 256)));        
            }

            result.addSeries(series1);            
        }
        return result;
    }    
     
    public static BeanGrafico Obj2CapturaAtual(Object obj){   
        CapturaAtual cap = (CapturaAtual) obj;
        BeanGrafico bean = new BeanGrafico();
        bean.setCodigo(cap.getCodCaptura());
        bean.setCodEquip(cap.getCodEquip());
        bean.setCodEvento(cap.getCodEvento());
        bean.setCodTipoOnda(cap.getCodTipoOnda());
        bean.setCodTomada(cap.getCodTomada());
        bean.setData(cap.getDataAtual());
        bean.setEficaz(cap.getEficaz());
        bean.setGain(cap.getGain());
        bean.setValorMedio(cap.getValorMedio());
        bean.setOffset(cap.getOffset());
        Collection <BeanHarmonica> lista = new ArrayList<>();
        for(HarmAtual ha : cap.getHarmAtual()){
            BeanHarmonica bh = new BeanHarmonica();
            bh.setCodigo(ha.getCodCaptura());
            bh.setCodHarmonica(ha.getHarmonica());
            bh.setSen(ha.getSen());
            bh.setCos(ha.getCos());
            lista.add(bh);
        }
        bean.setHarm(lista);            
        return (bean);
    }
    
    public static BeanGrafico Obj2OndaPadrao(Object obj){   
        OndaPadrao ondp = (OndaPadrao) obj;
        BeanGrafico bean = new BeanGrafico();
        bean.setCodigo(ondp.getCodOndaPadrao());
        bean.setCodEquip(ondp.getCodEquip());
        bean.setCodTipoPadrao(ondp.getCodTipoPadrao());
        bean.setCodTipoOnda(ondp.getCodTipoOnda());
        bean.setCodTomada(ondp.getCodTomada());
        bean.setData(ondp.getDataPadrao());
        bean.setEficaz(ondp.getEficaz());
        bean.setGain(ondp.getGain());
        bean.setValorMedio(ondp.getValorMedio());
        bean.setOffset(ondp.getOffset());
        Collection <BeanHarmonica> lista = new ArrayList<>();
        for(HarmPadrao ha : ondp.getHarmPadrao()){
            BeanHarmonica bh = new BeanHarmonica();
            bh.setCodigo(ha.getCodOndaPadrao());
            bh.setCodHarmonica(ha.getHarmonica());
            bh.setSen(ha.getSen());
            bh.setCos(ha.getCos());
            lista.add(bh);
        }
        bean.setHarm(lista);            
        return (bean);
    }            
    
    public static CapturaAtual BeanGrafico2CapturaAtual(BeanGrafico obj){   
        CapturaAtual bean = new CapturaAtual();
        
        bean.setCodCaptura(obj.getCodigo());
        bean.setCodEquip(obj.getCodEquip());
        bean.setCodEvento(obj.getCodEvento());
        bean.setCodTipoOnda(obj.getCodTipoOnda());
        bean.setCodTomada(obj.getCodTomada());
        bean.setDataAtual(obj.getData());
        bean.setEficaz(obj.getEficaz());
        bean.setGain(obj.getGain());
        bean.setValorMedio(obj.getValorMedio());
        bean.setOffset(obj.getOffset());
        Collection <HarmAtual> lista = new ArrayList<>();
        for(BeanHarmonica bharm : obj.getHarm()){
            HarmAtual ha = new HarmAtual();
            ha.setCodCaptura(bharm.getCodigo());
            ha.setHarmonica(bharm.getCodHarmonica());
            ha.setSen(bharm.getSen());
            ha.setCos(bharm.getCos());
            lista.add(ha);
        }
        bean.setHarmAtual(lista);            
        return (bean);
    }

}


