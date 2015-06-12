/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package artigo;

import Uteis.Conversoes;
import java.util.Calendar;

/**
 *
 * @author rebonatto
 */
public class testeFugaDao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        artigo.FugaDAO fugadao = new artigo.FugaDAO();
        float media=0.0F, mediat;
        int cont=0;
        int cc=0, c0, c1, c2, c3, d;
        int errosf=0, errot=0;
        float s, soma=0;
        float maior=0, menor=0;
        
        Calendar c = Calendar.getInstance();
        Calendar p = Calendar.getInstance();
        p.set(2013, 02, 16);
        c.set(2013, 02, 20);
        
        System.out.println(Conversoes.CalendarToTime(c));
        System.out.println("Executando ...");
        c0 = c1 = c2 = c3 = 0;
        
        for(artigo.BeanFuga bf : fugadao.lista()){               
            //System.out.println(bf.getCaptura().getCodCaptura());
            if (bf.getCaptura().getEficaz() > 3){
                errosf ++;
                continue;
            }
            cont ++;
            if (cont == 1){
                maior = menor = bf.getCaptura().getEficaz();
            }
            else{
                if (bf.getCaptura().getEficaz() > maior)
                    maior = bf.getCaptura().getEficaz();
                if (bf.getCaptura().getEficaz() < menor)
                    menor = bf.getCaptura().getEficaz();
            }
                
            media += bf.getCaptura().getEficaz();
            if (bf.getTermino() != null){
                    
                  s =  (bf.getTermino().getTimeInMillis() - bf.getCaptura().getDataAtual().getTimeInMillis()) /1000  ;
                  d = (int) s;
                  //System.out.println(s);
                  switch (d){
                      case 0: c0++; break;
                      case 1: c1++; break;
                      case 2: c2++; break; 
                      case 3: c3++; break;
                  }
                              
                  /*
                   * 1 h = 60m * 60 s = 3600s
                   * 2h = 7200s
                   */
                  if (s > 7200 ){
                      errot ++;
                      continue;                      
                  }
//                System.out.println(bf.getTermino());
//                System.out.println(bf.getCaptura().getDataAtual());
                String st = Uteis.DiffTempo.DiferencaTempo(bf.getCaptura().getDataAtual(), bf.getTermino());
                
                //Integer i = new Integer(s.substring(5, 7));
                //if (i > 5)
                //System.out.println(i);
                /*
                    System.out.println("Fuga " + bf.getCaptura().getCodCaptura() 
                                     + " " + bf.getCaptura().getCodTomada() 
                                     + " Ini " + Conversoes.CalendarToTimeStamp(bf.getCaptura().getDataAtual()) 
                                     + " fim " + Conversoes.CalendarToTimeStamp(bf.getTermino()) 
                                     + " dif " + Uteis.DiffTempo.DiferencaTempo(bf.getCaptura().getDataAtual(), bf.getTermino())
                                     + " Eficaz " + bf.getCaptura().getEficaz());
                */
                /*
                System.out.println(bf.getCaptura().getCodCaptura() + "\t" + Conversoes.CalendarToTime(bf.getCaptura().getDataAtual()) + "\t" + 
                        Conversoes.CalendarToTime(bf.getTermino()) + "\t" + st + "\t" + s + "\t" + bf.getCaptura().getEficaz());
                        */ 
                soma += s ;
                cc ++;
            }
            else
                System.out.println("Fuga sem termino " + bf.getCaptura().getCodCaptura() );
            
        }
        media /= cont;
        System.out.println("Erros de eficaz " + errosf);
        System.out.println("Media Eficaz: " + media + " de " + cont + " eventos");
        System.out.println("Maior " + maior + " menor " + menor);
        mediat = soma / cc;
        System.out.println("Erros de tempo " + errot);
        System.out.println("Media Tempo: " + mediat + "s de " + cc + " eventos");
        System.out.println("Tempo 00:00:00: " + c0);
        System.out.println("Tempo 00:00:01: " + c1);
        System.out.println("Tempo 00:00:02: " + c2);
        System.out.println("Tempo 00:00:03: " + c3);
                
        System.out.println(Conversoes.CalendarToTime(c));
        c = Calendar.getInstance();
        System.out.println(Conversoes.CalendarToTime(c));

        System.out.println(fugadao.getMensagem());
    }
}
