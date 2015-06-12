/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testePersistencia;

import modelo.TipoOnda;
import modelo.TipoPadrao;
import persistencia.TipoOndaDAO;
import persistencia.TipoPadraoDAO;

/**
 *
 * @author rebonatto
 */
public class testeTipoPadrao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        TipoPadraoDAO dao = new TipoPadraoDAO();
        TipoPadrao tipo = new TipoPadrao();

        tipo.setDesc("AAEEE");
        tipo.setCodTipoPadrao(2);

        if (dao.Insere(tipo)){
            System.out.println("inserido");
            int chave = dao.ultimaChave();
            System.out.println(chave);
          }

        else{
            System.out.println("deu erro" + dao.getMensagem());

        }

        for(TipoPadrao m : dao.lista())
             System.out.println(m.getCodTipoPadrao()+ "  " + m.getDesc());

    }        
}
