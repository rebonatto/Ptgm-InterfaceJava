/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testePersistencia;

import Uteis.EncontraPadroes;
import java.util.ArrayList;
import java.util.Collection;
import modelo.CapturaAtual;
import modelo.Equipamento;
import modelo.OndaPadrao;
import persistencia.CapturaAtualDAO;
import persistencia.OndaPadraoDAO;

/**
 *
 * @author rebonatto
 */
public class testeFindPadrao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        OndaPadraoDAO dao = new OndaPadraoDAO();
        CapturaAtualDAO capdao = new CapturaAtualDAO();
        
        
        Collection <OndaPadrao> lista = new ArrayList<>();
        
        CapturaAtual cap = capdao.localiza(4975560);
        
        OndaPadrao op = EncontraPadroes.minDifValorMedio(cap);
        if (op == null)
            System.out.println("Nulo");
        else
            System.out.println(op.getCodOndaPadrao());
        
        lista = dao.lista(cap.getCodEquip());
        
        for (OndaPadrao o : lista)
            System.out.println("Cod " + o.getCodOndaPadrao() + " Vm " + o.getValorMedio());
    }
}
