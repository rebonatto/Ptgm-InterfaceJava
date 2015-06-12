/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testePersistencia;

import modelo.TipoOnda;
import persistencia.TipoOndaDAO;

/**
 *
 * @author cliente
 */
public class testeTipoOnda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
        TipoOndaDAO dao = new TipoOndaDAO();
        TipoOnda mdl = new TipoOnda();

        mdl.setDesc("TESTEEEEE");
        //mdl.setCodTipoOnda(6);
/*
        if (dao.Insere(mdl)){
            System.out.println("inserido");
            int chave = dao.ultimaChave();
            System.out.println(chave);
          }

        else{
            System.out.println("deu erro" + dao.getMensagem());

        }
*/
        for(TipoOnda m : dao.lista())
             System.out.println(m.getCodTipoOnda()+ "  " + m.getDesc());

    }
        // TODO code application logic here
}
