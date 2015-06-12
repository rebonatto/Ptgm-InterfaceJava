/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Uteis;

import java.util.ArrayList;
import java.util.Collection;
import modelo.CapturaAtual;
import modelo.HarmPadrao;
import modelo.OndaPadrao;
import persistencia.OndaPadraoDAO;

/**
 *
 * @author rebonatto
 */
public class EncontraPadroes {    
    
    public static OndaPadrao minDifValorMedio(CapturaAtual cap){
        OndaPadraoDAO dao = new OndaPadraoDAO();
        OndaPadrao bean = null;
        
        float dif, aux;        
        
        Collection <OndaPadrao> lista = new ArrayList<>();
        lista = dao.lista(cap.getCodEquip(), cap.getCodTipoOnda());
        dif = Float.MAX_VALUE;
                
        for(OndaPadrao ond : lista){
            aux = Math.abs(cap.getValorMedio() - ond.getValorMedio());
            
            if (aux < dif ){                
                bean = ond;
                dif = aux;
            }
        }
                
        return ( bean );
    }
    
    private static OndaPadrao CopiaOndaPadrao(OndaPadrao veio){   
        OndaPadrao vai = new OndaPadrao();
        
        vai.setCodOndaPadrao(veio.getCodOndaPadrao());
        vai.setCodTipoOnda(veio.getCodTipoOnda());
        vai.setCodTomada(veio.getCodTomada());
        vai.setCodEquip(veio.getCodEquip());       
        vai.setCodTipoPadrao(veio.getCodTipoPadrao());
        vai.setValorMedio(veio.getValorMedio());
        vai.setEficaz(veio.getEficaz());
        vai.setGain(veio.getGain());        
        vai.setOffset(veio.getOffset());        
        vai.setDataPadrao(veio.getDataPadrao());
        Collection <HarmPadrao> lista = new ArrayList<>();
        for(HarmPadrao inhp : veio.getHarmPadrao()){
            HarmPadrao outhp = new HarmPadrao();
            outhp.setCodOndaPadrao(inhp.getCodOndaPadrao());
            outhp.setHarmonica(inhp.getHarmonica());
            outhp.setSen(inhp.getSen());
            outhp.setCos(inhp.getCos());
            lista.add(outhp);
        }
        vai.setHarmPadrao(lista);            
        return (vai);
    }
}
