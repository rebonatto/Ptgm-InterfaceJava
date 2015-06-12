/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testePersistencia;


import javax.swing.JOptionPane;

/**
 *
 * @author rebonatto
 */
public class Testedecimais {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        float valor, valor2=4986.8847F;
        Float v = new Float(0F);
        String str = new String();
        /*
        DecimalFormat df =  new DecimalFormat("0.000E0");       
        */
        while (true){
             str = JOptionPane.showInputDialog("Informe o valor que deseja formatar: ");
            if (str.indexOf('.') == -1)
                JOptionPane.showMessageDialog(null,"Formato errado - Deve conter pelo menos 1 ponto");
            else
                break;
        }
        try{
            v = Float.parseFloat(str);
            JOptionPane.showMessageDialog(null, FormataSignificativos(v, 3 ), "Com 3 digitos significativos", JOptionPane.INFORMATION_MESSAGE);
        } catch( Exception ex){
            JOptionPane.showMessageDialog(null,"Formato errado");
            System.exit(1);
        }                                
        
                
        /*
        System.out.println("Oi " + df.format(valor));
        System.out.println("Opa " + nf.format(valor2));
                */
        //System.out.println();                       
    }
    
    public static String FormataSignificativos(float valor, int digitos){
        int cont = 0;
        float x = valor;                
        String ret, aux;
                                        
        if (valor > 1.0){
            while (x > 1.0){
                x /= 10.0;                
                cont++;                
            }
        }
       
        ret = (new Float(valor)).toString();
        ret = ret.substring(0, ret.indexOf('.'));
        
        if (cont >= digitos){ // Os digitos antes da virgula são maiores
            return (ret);
        }                
        
        aux = new String(new Float(valor).toString());
                
        
        ret = ret + '.';
                
        for(int i = aux.indexOf('.')+1; i < aux.length(); i++){                          
            cont++;
            ret = ret + aux.charAt(i);
            if (cont == digitos)
                break;
        }
        
        if (cont < digitos){
            ret = ret.concat(stuffzero(digitos-cont));
        }            
                
        return ret;
    }

    
    

    
    private static String stuffzero(int quantos){
        String ret = new String("");
        String zero = new String("0");
        
        for(int i=0; i < quantos; i++)
            ret = ret.concat(zero);
                
        return ret;
    }

    
        public static String FormataNotacaoCientifica(float d, int casas){
        //String ret = new String();
        int cont = 1;
        float x = d;
        int ponto = 0, flag=0;
        String ret, adicional;
                        
        System.out.println("Original " + x);
        
        if (d > 1.0)
            cont--;
        
        while(x < 1.0){            
            x *= 10.0;            
            if (x < 1) 
                cont++; 
            else {                
                flag = 1;
                break;
            }            
        }
        
        System.out.println(cont);
        
        ret = Float.toString(x);
        ponto = ret.indexOf('.');
        
        if (ponto+casas+1 > ret.length()){            
            adicional = stuffzero(ponto+casas+1 - ret.length());
            ret = ret.concat(adicional);                      
        }
        else{
            ret = ret.substring(0, ponto + casas+1) ;
        }
        if (cont != 0)        
            ret = ret + "E" + (flag == 1 ? "-" : "+") + Integer.toString(cont);  
                
        return ret;
    }

    
}
