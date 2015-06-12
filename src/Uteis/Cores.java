/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Uteis;

import java.awt.Color;
import java.awt.Paint;
import javax.swing.JTable;
import modelo.CapturaAtual;

/**
 *
 * @author rebonatto
 */
public class Cores {
    public static Paint getCorGrafico(int c){    
        switch (c){            
            case 1: return Color.RED;
            case 2: return Color.GREEN;    
            case 3: return Color.BLUE;
            case 4: return Color.YELLOW;
            case 5: return Color.CYAN;
            case 6: return Color.MAGENTA;
            case 7: return Color.GRAY;
            case 8: return Color.DARK_GRAY;                            
        }
        return Color.BLACK;                                    
    }
    
    public static int getposicao(JTable tabela, int codcaptura){
        int cod;
        for(int i =0; i < tabela.getRowCount(); i++){
            cod  = (Integer) tabela.getValueAt(i, 0);
            if ( cod == codcaptura)
                return i;
        }
        return -1;
    }
}
