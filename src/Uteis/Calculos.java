/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Uteis;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import modelo.CapturaAtual;
import modelo.HarmAtual;
import persistencia.CapturaAtualDAO;

/**
 *
 * @author rebonatto
 */
public class Calculos {

    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String[] args) {
        // TODO code application logic here
        CapturaAtualDAO dao =  new CapturaAtualDAO();
        
        Calendar inicio = Calendar.getInstance();
        Calendar fim = Calendar.getInstance();
        
        float vm, novoeficaz;
        
        inicio.set(2011, 00, 01);
        fim.set(2014, 11, 31); 
        
        Collection<CapturaAtual> lista = dao.listaFoFuga(200, inicio, fim);
        for (CapturaAtual cap : lista){    
            vm = Math.abs( (cap.getValorMedio() / 256 - cap.getOffset()) / cap.getGain() );
            System.out.println("Codcaptura: " + cap.getCodCaptura() + " Eficaz: " + cap.getEficaz() + " VM: " + vm);
            novoeficaz = AjustaRMSValorMedio(cap);
            System.out.println("Novo Eficaz " + AjustaRMSValorMedio(cap));
            //Collection <HarmAtual>  novo = AjustaHarmonicasVM(cap);                 
            
            for(HarmAtual h : cap.getHarmAtual()){
                System.out.println("[" + h.getHarmonica()*60 + "Hz] Seno: " + h.getSen() + 
                               "\tCosseno: " + h.getCos() + "\tModulo: " + modulo(h.getSen(), h.getCos(), cap.getGain()) + 
                               "\t % " +  ( (modulo(h.getSen(), h.getCos(), cap.getGain()) / cap.getEficaz() ) * 100 ) );
                        //                
            }
            //cap.setHarmAtual(novo);
            for(HarmAtual h : cap.getHarmAtual()){
                System.out.println("[" + h.getHarmonica()*60 + "Hz] NovoSeno: " + h.getSen() + 
                               "\tNovo Cosseno: " + h.getCos() + "\tModulo: " + modulo(h.getSen(), h.getCos(), cap.getGain()) +
                               "\t % " +  ( (modulo(h.getSen(), h.getCos(), cap.getGain()) / novoeficaz) * 100 ) ); 
            }
              
            break;
            
        }
    }   
    */
    
    public static float AjustaRMSValorMedio (CapturaAtual c){
        float res=0;
        float total=0, mod=0, vm=0, totalharm=0;
        
        if (c.getValorMedio()== 0)
            return c.getEficaz();
        // coloca o valor do VM
        vm = Math.abs( (c.getValorMedio() / 256 - c.getOffset()) / c.getGain() );        
        total = vm;
        
        // calcula e adiciona cada uma das barras
        for(HarmAtual h : c.getHarmAtual()){
            mod = modulo(h.getSen(), h.getCos(), c.getGain());
            totalharm += mod;
        }
        total += totalharm;
        res = (totalharm * c.getEficaz()) / total;
        return res;
    }
        
    
    public static float modulo(float sen, float cos, float gain){
        /* calcula o modulo, igual ao grafico do programa */
        float f = (float) Math.sqrt( sen * sen + cos * cos)  / 128  ;  
        f = f / gain;
                
        return f;
    }
    
    public static float Pico2RMS(float f){
        /* ajusta o valor de pico para RMS */
        f = f * f;
        f = f / 2;
        f =  (float) Math.sqrt(f);  
        return f;
    }
    
    
    /* Não é necessario usar
    public static Collection <HarmAtual> AjustaHarmonicasVM(CapturaAtual cap){
        float novoeficaz, somamodulos=0, novomodulo;
        float somatri, seno, coss;
        Collection <HarmAtual> res = new ArrayList<HarmAtual>();        
        
        //Calcula novo eficaz (tirando VM)
        novoeficaz = AjustaRMSValorMedio(cap);
        
        //Calcula soma das harmonicas
        for(HarmAtual h : cap.getHarmAtual())
            somamodulos += modulo(h.getSen(), h.getCos(), cap.getGain());
        
        // Ajusta valores pela proporção
        for (HarmAtual h : cap.getHarmAtual()){           
            //somatri = Math.abs((h.getSen() / 128 )/ cap.getGain()) + Math.abs((h.getCos() / 128 )/ cap.getGain());
            somatri = Math.abs(h.getSen() ) + Math.abs(h.getCos() );
            novomodulo = (novoeficaz * modulo(h.getSen(), h.getCos(), cap.getGain()) ) / somamodulos;
            coss = (h.getCos() * novoeficaz) / somatri;
            seno = (h.getSen() * novoeficaz) / somatri;
            HarmAtual aux = new HarmAtual();
            aux.setCodCaptura(cap.getCodCaptura());
            aux.setHarmonica(h.getHarmonica());
            aux.setSen(seno);
            aux.setCos(coss);
            res.add(aux);            
        }
        
        return (res);
    }
    */   
}
