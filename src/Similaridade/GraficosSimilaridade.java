/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Similaridade;

import Uteis.Configuracoes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import modelo.CapturaAtual;
import modelo.HarmAtual;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author rebonatto
 */
public class GraficosSimilaridade {
        /*
    TIRADOVM: utilizado para verificar se o firmware retirou o valor medio das amostras e fez ajustes na FFT
    0 -> não tirou a VM
    1 -> tirou a VM
    */
    
    private static int TIRADOVM = 1;
    
 public static JFreeChart criaGrafico2LinhasComDeslocada(CapturaAtual onda1, CapturaAtual onda2, int deslocamento, double correlacao, Paint cor1, Paint cor2) {
        String eixoy = new String("Current ");
        // verifica se é fase ou fuga
        if  (onda1.getCodEvento().getCodEvento() == 1){ // evento 1 é de fuga
            eixoy = eixoy.concat("(mA)");
        }
        else{
            eixoy = eixoy.concat("(A)");
        }
        XYDataset dataset = newDataset2OndasComDeslocada(onda1, onda2, deslocamento, correlacao); 
                        
        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
             null,                        // chart title
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
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
                
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        
        renderer.setBaseStroke(new BasicStroke(1.0f));
        for(int i = 0; i < dataset.getSeriesCount(); i++){
            renderer.setSeriesLinesVisible(i, true);
            renderer.setSeriesShapesVisible(i, false);
        }   
        // Ajustar para todas as cores padrão, pelo menos 10
        renderer.setSeriesPaint(0, cor1);
        renderer.setSeriesPaint(1, cor2);    
        
        /* Código para ter linhas tracejadas 
        renderer.setSeriesStroke(1, 
                new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.CAP_ROUND,
                                20.0f, new float[] {5.0f}, 0.0f) );
               
        renderer.setDrawSeriesLineAsPath(true); 
        /* Final do Codigo para tracejadas */
        
        plot.setRenderer(renderer);
                
        return chart;        
    }   
 
 
 public static XYDataset newDataset2OndasComDeslocada(CapturaAtual onda1, CapturaAtual onda2, int deslocamento, double correlacao){
        //metodo que cria um CategoryDataset para ser usado em grafico de linhas                
        float valOnda1[] = new float[Configuracoes.PONTOSONDA];   
        float valOnda2[] = new float[Configuracoes.PONTOSONDA];
        float sen1[] = new float[Configuracoes.HARMONICAS];
        float cos1[] = new float[Configuracoes.HARMONICAS];
        float sen2[] = new float[Configuracoes.HARMONICAS];
        float cos2[] = new float[Configuracoes.HARMONICAS];
        int i;

        float s1, s2, tempo[] = new float[Configuracoes.PONTOSONDA];
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
        
        XYSeries series1 = new XYSeries(String.valueOf(onda1.getCodCaptura()));
        XYSeries series3 = new XYSeries("Shift " + String.valueOf(onda2.getCodCaptura()) );
                        
        tempo[0] = 0;  //(float) 1 / 60;
        for (i = 0; i < Configuracoes.PONTOSONDA; i++) { 
            if (TIRADOVM == 0){
                s1 = onda1.getValorMedio() / 2;
                s2 = onda2.getValorMedio() / 2;
            }
            else{            
                s1 = onda1.getValorMedio() ;
                s2 = onda2.getValorMedio() ;
            }

            for (int x = 0; x < Configuracoes.HARMONICAS; x++) {
                if (TIRADOVM ==0){
                    s1 += (float) (sen1[x] * Math.sin(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i])) + 
                            (cos1[x] * Math.cos(-2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i]));
                    s2 += (float) (sen2[x] * Math.sin(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i])) + 
                            (cos2[x] * Math.cos(-2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i]));
                }
                else {                
                    s1 += (float) (sen1[x] * Math.sin(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i])) + 
                            (cos1[x] * Math.cos(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i]));
                    s2 += (float) (sen2[x] * Math.sin(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i])) + 
                            (cos2[x] * Math.cos(2 * Math.PI * ((x + 1) * Configuracoes.FREQBASE) * tempo[i]));
                }
            }
            if (TIRADOVM == 0){
                valOnda1[i] = (int) ((s1 * (2.0)) / 256.0);
                valOnda1[i] = (valOnda1[i] - onda1.getOffset() ) ;
                valOnda2[i] = (int) ((s2 * (2.0)) / 256.0);
                valOnda2[i] = (valOnda2[i] - onda2.getOffset() ) ;

            }
            else{
                valOnda1[i] = (s1);
                valOnda2[i] = (s2 );
            }
            valOnda1[i] = (valOnda1[i]) / onda1.getGain();            
            valOnda2[i] = (valOnda2[i] ) / onda2.getGain();

            series1.add(tempo[i] * 1000, valOnda1[i]);            
            
            if(i == (Configuracoes.PONTOSONDA -1)){
                break;
            }
            tempo[i+1] = (tempo[i] + (float) (1.0 / (60 * 256)));
        }
        
        int j = 0;
        if(correlacao < 0){
            for (i = 0; i < Configuracoes.PONTOSONDA; i++) {
                if((i+deslocamento) <= (Configuracoes.PONTOSONDA - 1)){
                    series3.add(tempo[i] * 1000, -1 * valOnda2[i+deslocamento]);
                }else{
                    series3.add(tempo[i] * 1000, -1 * valOnda2[j++]);
                }   
            } 
        }   
        else{
            for (i = 0; i < Configuracoes.PONTOSONDA; i++) {
                if((i+deslocamento) <= (Configuracoes.PONTOSONDA - 1)){
                    series3.add(tempo[i] * 1000, valOnda2[i+deslocamento]);
                }else{
                    series3.add(tempo[i] * 1000, valOnda2[j++]);
                }   
            }
        }
        
        result.addSeries(series1);
        result.addSeries(series3);

        return result;
    }
}
