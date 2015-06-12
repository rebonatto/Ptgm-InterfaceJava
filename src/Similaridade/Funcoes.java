/*
    Funções usada para calculo de similaridade entre formas de onda
    Desenvolvidas por Mauricio Schimitz em TCC orientado por Marcelo Trindade Rebonatto
*/

package Similaridade;
import Graficos.BeanGrafico;
import Graficos.BeanHarmonica;
import Graficos.CriaGrafico;
import Uteis.Configuracoes;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import modelo.CapturaAtual;
import org.jfree.data.xy.XYDataset;

public class Funcoes {
    
    public static double casasDouble(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
    
    public static double desvioPadrao (List<Double> onda, double media){
        double elevar, desvio = 0, tamanho = onda.size();
        int i;
        for(i=0; i<tamanho; i++){
            elevar = onda.get(i) - media;
            desvio += (elevar * elevar);
        }
        desvio /= (tamanho-1);
        desvio = Math.sqrt(desvio);
        return desvio;
    }
    
    public static double variancia (List<Double> onda){
        double media = 0, elevar, variancia = 0, tamanho = onda.size();
        int i;
        for(i=0; i<tamanho; i++){
            media += (onda.get(i));
        }
        media /= tamanho;
        for(i=0; i<tamanho; i++){
            elevar = onda.get(i) - media;
            variancia += (elevar * elevar);
        }
        variancia /= (tamanho-1);
        return variancia;
    }
    
    public static double[] pearson (List<Double> onda1List, List<Double> onda2List){
        double soma1, soma2, media1 = 0.0, media2 = 0.0, pearsonaux;
        double[] pearson = new double[3];
        int tamanho, primeiracorr = 0;
        
        //compara as duas listas e verifica qual é a menor (deveriam ser iguais)
        if (onda1List.size() > onda2List.size()){
            tamanho = onda2List.size();
        }else{
            tamanho = onda1List.size();
        }
        
        //obtém a média dos valores
        for(int i=0; i<tamanho; i++){
            media1 += onda1List.get(i);
            media2 += onda2List.get(i);
        }
        media1 /= tamanho;
        media2 /= tamanho;
        
        double desviopadrao1 = desvioPadrao(onda1List, media1);
        double desviopadrao2 = desvioPadrao(onda2List, media2); 
        
        //for para deslocar onda
        for(int j=0; j<tamanho; j++){
            pearsonaux = 0;
            //for para percorrer onda
            for(int i=0; i<tamanho; i++){
               soma1 = (onda1List.get(i) - media1)/desviopadrao1;
               if((i+j) < tamanho){
                    soma2 = (onda2List.get(i+j) - media2)/desviopadrao2;
               }
               else{
                   soma2 = (onda2List.get(i+j-tamanho) - media2)/desviopadrao2;
               }
               pearsonaux += (soma1 * soma2);
            }
            pearsonaux /= (tamanho-1);
            
            //System.out.println("\nPearson Posição " + j + " : " + pearsonaux);
            
            //teste para verificar se pearson aumentou
            soma1 = Math.abs(pearsonaux);
            soma2 = Math.abs(pearson[0]);
            if(soma1 > soma2){
                //armazena melhor pearson deslocando a onda
                pearson[0] = pearsonaux;
                //armazena deslocamento
                pearson[1] = j;
                //armazena pearson sem deslocar onda
                if(primeiracorr == 0){
                    pearson[2] = pearsonaux;
                    primeiracorr = 1;
                }
            }
            //se achar uma correlação negativa e positiva igual, armazena a positiva
            else{
                if(soma1 == soma2){
                    if(pearsonaux > pearson[0]){
                        //System.out.println("Entrou Pearson Igual: ");
                        //armazena melhor pearson deslocando a onda
                        pearson[0] = pearsonaux;
                        //armazena deslocamento
                        pearson[1] = j;
                    }
                }
            }
        }
        return pearson;
    }
    
    public static double[] pearson2 (List<Double> onda1List, List<Double> onda2List){
        double soma1, soma2, media1 = 0, media2 = 0, pearsonaux, soma3, soma4;
        double[] pearson = new double[3];

        int tamanho, primeiracorr = 0;
        //compara as duas listas e verifica qual é a menor (deveriam ser iguais)
        if (onda1List.size() > onda2List.size()){
            tamanho = onda2List.size();
        }else{
            tamanho = onda1List.size();
        }
        //obtém a média dos valores
        for(int i=0; i<tamanho; i++){
            media1 += onda1List.get(i);
            media2 += onda2List.get(i);
        }
        media1 /= tamanho;
        media2 /= tamanho;
        
        for(int j=0; j<tamanho; j++){
            pearsonaux = 0;
            soma3 = 0;
            soma4 = 0;
            for(int i=0; i<tamanho; i++){
               soma1 = (onda1List.get(i) - media1);
               if((i+j) < tamanho){
                    soma2 = (onda2List.get(i+j) - media2);
               }
               else{
                   soma2 = (onda2List.get(i+j-tamanho) - media2);
               }
               pearsonaux += (soma1 * soma2);
               soma3 += soma1 * soma1;
               soma4 += soma2 * soma2;
            }
            soma3 = Math.sqrt(soma3);
            soma4 = Math.sqrt(soma4);
            soma3 *= soma4;
            pearsonaux /= soma3;
            //System.out.println("\nPearson Posição " + j + " : " + pearson);
            
            if(Math.abs(pearsonaux) > Math.abs(pearson[0])){
                //armazena melhor pearson deslocando a onda
                pearson[0] = pearsonaux;
                //armazena deslocamento
                pearson[1] = j;
                //armazena pearson sem deslocar onda
                if(primeiracorr == 0){
                    pearson[2] = pearsonaux;
                    primeiracorr = 1;
                }
            }
            //se achar uma correlação negativa e positiva igual, armazena a positiva
            else{
                if(Math.abs(pearsonaux) == Math.abs(pearson[0])){
                    if(pearsonaux > pearson[0]){
                        //armazena melhor pearson deslocando a onda
                        pearson[0] = pearsonaux;
                        //armazena deslocamento
                        pearson[1] = j;
                    }
                }
            }
        }
        return pearson;
    }
    
    public static double[] spearman (List<Double> onda1List, List<Double> onda2List){  
        double soma1, soma2, media1 = 0.0, media2 = 0.0, spearman, spearmanaux1, spearmanaux2;
        double[] maxspearman = new double[3];
        int tamanho, primeirospear = 0;
        
        //compara as duas listas e verifica qual é a menor (deveriam ser iguais)
        if (onda1List.size() > onda2List.size()){
            tamanho = onda2List.size();
        }else{
            tamanho = onda1List.size();
        }
        
        //obtem a media dos valores
        for(int i=0; i<tamanho; i++){
            media1 += onda1List.get(i);
            media2 += onda2List.get(i);
        }
        media1 /= tamanho;
        media2 /= tamanho;
        
        //for para deslocar onda
        for(int j=0; j<tamanho; j++){
            spearman = 0.0;
            spearmanaux1 = 0.0;
            spearmanaux2 = 0.0;
            //for para percorrer onda
            for(int i=0; i<tamanho; i++){
               soma1 = onda1List.get(i) - media1;
               if((i+j) < tamanho){
                    soma2 = onda2List.get(i+j) - media2;
               }
               else{
                   soma2 = onda2List.get(i+j-tamanho) - media2;
               }
               spearman += soma1 * soma2;
               spearmanaux1 += soma1 * soma1;
               spearmanaux2 += soma2 * soma2;
            }
            
            spearmanaux1 *= spearmanaux2;
            spearmanaux1 = Math.sqrt(spearmanaux1);
            spearman /= spearmanaux1;
            
            //System.out.println("\nSpearman Posição " + j + " : " + spearman);
            
            //teste melhor spearman
            soma1 = Math.abs(spearman);
            soma2 = Math.abs(maxspearman[0]);
            if(soma1 > soma2){
                //armazena melhor spearman deslocando a onda
                maxspearman[0] = spearman;
                //armazena deslocamento
                maxspearman[1] = j;
                //armazena spearman sem deslocar onda
                if(primeirospear == 0){
                    maxspearman[2] = spearman;
                    primeirospear = 1;
                }
            }
            //se achar uma correlação negativa e positiva igual, armazena a positiva
            else{
                if(soma1 == soma2){
                    if(spearman > maxspearman[0]){
                        //System.out.println("Entrou Spearman Igual: ");
                        //armazena melhor pearson deslocando a onda
                        maxspearman[0] = spearman;
                        //armazena deslocamento
                        maxspearman[1] = j;
                    }
                }
            }
        }
        return maxspearman;
    }
    
    public static double bhattacharyya (List<Double> onda1List, List<Double> onda2List){ 
        double soma1 = 0.0, soma2 = 0.0;
        double media1, media2, bhat, aux;
        int tamanho;
        //compara as duas listas e verifica qual é a menor (deveriam ser iguais)
        if (onda1List.size() > onda2List.size()){
            tamanho = onda2List.size();
        }else{
            tamanho = onda1List.size();
        }
        //obtém a média dos valores
        for(int i=0; i<tamanho; i++){
            soma1 += onda1List.get(i);
            soma2 += onda2List.get(i);
        }
        media1 = soma1 / tamanho;
        media2 = soma2 / tamanho;
        
        //quadrado da variancia da primeira dividido pelo quadrado da variancia da segunda
        bhat = Math.pow(variancia(onda1List), 2) / Math.pow(variancia(onda2List), 2);
        //quadrado da variancia da segunda dividido pelo quadrado da variancia da primeira
        bhat += Math.pow(variancia(onda2List), 2) / Math.pow(variancia(onda1List), 2);
        //soma 2
        bhat += 2;
        //divide por quatro
        bhat /= 4;
        //ln do resultado
        bhat = Math.log(bhat);
        //divide por 4
        bhat /= 4;
        
        //quadrado das medias subtraidas
        aux = Math.pow(media1 - media2, 2);
        //divide pelo quadrado da soma das variancias
        aux /= Math.pow(variancia(onda1List), 2) + Math.pow(variancia(onda2List), 2);
        //divide por 4
        aux /= 4;
        
        //adiciona os valores
        bhat += aux;

        return bhat;
    }
    
    public static double rmsdsemdesl (List<Double> onda1List, List<Double> onda2List){ 
        double rmsd = 0.0, aux;
        int tamanho;
        
        //compara as duas listas e verifica qual é a menor (deveriam ser iguais)
        if (onda1List.size() > onda2List.size()){
            tamanho = onda2List.size();
        }else{
            tamanho = onda1List.size();
        }

        for(int i=0; i<tamanho; i++){
            aux = onda1List.get(i) - onda2List.get(i);
            aux *= aux;
            rmsd += aux;
        }
        rmsd /= tamanho;
        rmsd = Math.sqrt(rmsd);
        
        return rmsd;
    }
    
    public static double[] rmsd (List<Double> onda1List, List<Double> onda2List){ 
        double[] rmsd = new double[3];
        double aux, rmsdaux;
        int tamanho, primeirormsd = 0, i;
        
        //compara as duas listas e verifica qual é a menor (deveriam ser iguais)
        if (onda1List.size() > onda2List.size()){
            tamanho = onda2List.size();
        }else{
            tamanho = onda1List.size();
        }
        
        rmsd[0] = 10000000.0;
        for(int j=0; j<tamanho; j++){
            rmsdaux = 0.0;
            for(i=0; i<tamanho; i++){
                if((i+j) < tamanho){
                    aux = onda1List.get(i) - onda2List.get(i+j);
                    aux *= aux;
                    rmsdaux += aux;
                }
                else{
                    aux = onda1List.get(i) - onda2List.get(i+j-tamanho);
                    aux *= aux;
                    rmsdaux += aux;
                }
            }
            //System.out.println("RMSD Soma: " + rmsdaux);
            rmsdaux /= tamanho;
            //System.out.println("RMSD / Tamanho: " + rmsdaux);
            rmsdaux = Math.sqrt(rmsdaux);
            //System.out.println("RMSD Raiz: " + rmsdaux);
            
            if(rmsdaux < rmsd[0]){
                //armazena melhor rmsd deslocando a onda
                rmsd[0] = rmsdaux;
                //armazena deslocamento
                rmsd[1] = j;
                //armazena rmsd sem deslocar onda
                if(primeirormsd == 0){
                    rmsd[2] = rmsdaux;
                    primeirormsd = 1;
                }
            }
        }
        return rmsd;
    }
    
    public static double[] rmsdNormalizado (List<Double> onda1List, List<Double> onda2List){ 
        double[] rmsd = new double[3];
        double aux, rmsdaux, maior, menor;
        int tamanho, primeirormsd = 0, i;
        
        //compara as duas listas e verifica qual é a menor (deveriam ser iguais)
        if (onda1List.size() > onda2List.size()){
            tamanho = onda2List.size();
        }else{
            tamanho = onda1List.size();
        }
        
        maior = onda1List.get(0);
        menor = onda1List.get(0);
        
        for(i=0; i<tamanho; i++){
            if(onda1List.get(i) < menor){
                menor = onda1List.get(i);
            }
            if(onda1List.get(i) > maior){
                maior = onda1List.get(i);
            }            
        }
        
        rmsd[0] = 10000000.0;
        for(int j=0; j<tamanho; j++){
            rmsdaux = 0.0;
            for(i=0; i<tamanho; i++){
                if((i+j) < tamanho){
                    aux = onda1List.get(i+j) - onda2List.get(i);
                    aux *= aux;
                    rmsdaux += aux;
                }
                else{
                    aux = onda1List.get(i+j-tamanho) - onda2List.get(i);
                    aux *= aux;
                    rmsdaux += aux;
                }
            }
            rmsdaux /= tamanho;
            rmsdaux = Math.sqrt(rmsdaux);
            rmsdaux /= maior - menor;
            if(rmsdaux < rmsd[0]){
                //armazena melhor rmsd deslocando a onda
                rmsd[0] = rmsdaux;
                //armazena deslocamento
                rmsd[1] = j;
                //armazena rmsd sem deslocar onda
                if(primeirormsd == 0){
                    rmsd[2] = rmsdaux;
                    primeirormsd = 1;
                }
            }
        }
        return rmsd;
    }
    
    public static double[] rmsdCV (List<Double> onda1List, List<Double> onda2List){ 
        double[] rmsd = new double[3];
        double aux, rmsdaux, media;
        int tamanho, primeirormsd = 0, i;
        
        //compara as duas listas e verifica qual é a menor (deveriam ser iguais)
        if (onda1List.size() > onda2List.size()){
            tamanho = onda2List.size();
        }else{
            tamanho = onda1List.size();
        }
        
        media = 0.0;
        
        for(i=0; i<tamanho; i++){
            media += onda1List.get(i);
        }
        
        media /= tamanho;
        
        //System.out.println("Media: " + media);
        
        rmsd[0] = 10000000.0;
        for(int j=0; j<tamanho; j++){
            rmsdaux = 0.0;
            for(i=0; i<tamanho; i++){
                if((i+j) < tamanho){
                    aux = onda1List.get(i+j) - onda2List.get(i);
                    aux *= aux;
                    rmsdaux += aux;
                }
                else{
                    aux = onda1List.get(i+j-tamanho) - onda2List.get(i);
                    aux *= aux;
                    rmsdaux += aux;
                }
            }
            rmsdaux /= tamanho;
            rmsdaux = Math.sqrt(rmsdaux);
            rmsdaux /= media;
            
            
            if(rmsdaux < rmsd[0]){
                //armazena melhor rmsd deslocando a onda
                rmsd[0] = rmsdaux;
                //armazena deslocamento
                rmsd[1] = j;
                //armazena rmsd sem deslocar onda
                if(primeirormsd == 0){
                    rmsd[2] = rmsdaux;
                    primeirormsd = 1;
                }
            }
        }
        return rmsd;
    }
    
    public static double[] minimosQuadrados (List<Double> onda1List, List<Double> onda2List){
        double[] soma = new double[2];
        double[] minimos = new double[2];
        double b = 0.0, baux = 0.0, a;
        int tamanho;
        //compara as duas listas e verifica qual é a menor (deveriam ser iguais)
        if (onda1List.size() > onda2List.size()){
            tamanho = onda2List.size();
        }else{
            tamanho = onda1List.size();
        }
        //obtém a média dos valores
        for(int i=0; i<tamanho; i++){
            soma[0] += onda1List.get(i);
            soma[1] += onda2List.get(i);
        }
        
        for(int i=0; i<tamanho; i++){
            b += onda1List.get(i) * onda2List.get(i);
            baux += onda1List.get(i) * onda1List.get(i);
        }
        b *= 13;
        baux *= 13;
        b -= soma[0] * soma[1];
        baux -= soma[0] * soma[0];
        b /= baux;
        
        a = (soma[1] - (b * soma[0]))/tamanho;
        minimos[0] = a;
        minimos[1] = b;
        
        //y = a + b*x
        
        return minimos;
    }
    
    public static List<Double> somaOnda (List<Double> onda1List, List<Double> onda2List, int porcentagem){
        double soma;
        List<Double> ondaList = new ArrayList<>();
        for(int i=0; i<onda1List.size(); i++){
            soma = (onda2List.get(i) * porcentagem)/100;
            //System.out.println("Soma1: " + soma);
            soma += onda1List.get(i);
            //System.out.println("Soma2: " + soma);
            ondaList.add(soma);
        }
        return ondaList;
    }
    
    public static CapturaAtual somaHarm (CapturaAtual codOnda1, CapturaAtual codOnda2, int percentual, boolean somar){
        int j, i;
        float sen[] = new float[Configuracoes.HARMONICAS];
        float cos[] = new float[Configuracoes.HARMONICAS];
        
        BeanGrafico bg1 = CriaGrafico.Obj2CapturaAtual(codOnda1);
        BeanGrafico bg2 = CriaGrafico.Obj2CapturaAtual(codOnda2);
        
        j=0;
        for (BeanHarmonica oa : bg1.getHarm()){
            sen[j] = oa.getSen();
            cos[j] = oa.getCos();
            //System.out.println(j + " Seno: " + sen[j]);
            j++;         
        }
        
        j=0;
        if(somar){
            for (BeanHarmonica oa : bg2.getHarm()){
                if(sen[j] < 0){
                    if(oa.getSen() < 0){
                        sen[j] += oa.getSen() * percentual / 100;
                    }
                    else{
                        sen[j] += (oa.getSen()*-1) * percentual / 100;
                    }
                }else{
                    if(oa.getSen() < 0){
                        sen[j] += (oa.getSen()*-1) * percentual / 100;
                    }
                    else{
                        sen[j] += oa.getSen() * percentual / 100;
                    }
                }
                
                if(cos[j] < 0){
                    if(oa.getCos() < 0){
                        cos[j] += oa.getCos() * percentual / 100;
                    }
                    else{
                        cos[j] += (oa.getCos()*-1) * percentual / 100;
                    }
                }else{
                    if(oa.getCos() < 0){
                        cos[j] += (oa.getCos()*-1) * percentual / 100;
                    }
                    else{
                        cos[j] += oa.getCos() * percentual / 100;
                    }
                }  
                //System.out.println(j + " Seno2: " + oa.getSen() + " Soma: " + sen[j]);
                j++;
            } 
        }else{
            for (BeanHarmonica oa : bg2.getHarm()){
                sen[j] -= oa.getSen() * percentual / 100;
                cos[j] -= oa.getCos() * percentual / 100;
                j++;        
            } 
        }
           
        Collection <BeanHarmonica> nova = new ArrayList<>();
        for(i = 0; i < Configuracoes.HARMONICAS; i++ ){
            BeanHarmonica b = new BeanHarmonica();
            
            b.setCodHarmonica(i+1);
            b.setCodigo(bg1.getCodigo());
            b.setCos(cos[i]);
            b.setSen(sen[i]);
            nova.add(b);
        }
        
        bg1.setHarm(nova);
        return CriaGrafico.BeanGrafico2CapturaAtual(bg1);
    }
    
    public static List<Double> retListO (CapturaAtual codOnda){
        BeanGrafico bg = CriaGrafico.Obj2CapturaAtual(codOnda);
        XYDataset onda = Graficos.CriaGrafico.newDatasetOnda(bg, false);
        List<Double> ondaList = new ArrayList<>();
        for(int i=0; i<onda.getItemCount(0); i++){
            ondaList.add(onda.getYValue(0, i));
        }
        return ondaList;
    }
    /*
    public static List<Double> retListH (CapturaAtual codOnda, String nome){
        CategoryDataset data = Graficos.CriaGrafico.newDatasetBarra(codOnda, nome);
        
        List<Double> ondaList = new ArrayList<>();
        for(int i=0; i<data.getColumnCount(); i++){
            ondaList.add((double) (data.getValue(0, i)));
        }
        return ondaList;
    }
    */
}
