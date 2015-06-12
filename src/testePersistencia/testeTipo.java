/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * @author Eduardo
 * Modified by: rebonatto
 */

package testePersistencia;

import modelo.Tipo;
import persistencia.TipoDAO;

/**
 *
 * @author cliente
 */
public class testeTipo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
        TipoDAO dao = new TipoDAO();
        Tipo mdl = new Tipo();

        mdl.setDesc("TESTEEEEE");
        //mdl.setCodTipoOnda(6);

        if (dao.Insere(mdl)){
            System.out.println("inserido");
            int chave = dao.ultimaChave();
            System.out.println(chave);
          }

        else{
            System.out.println("deu erro" + dao.getMensagem());

        }

        for(Tipo m : dao.lista())
             System.out.println(m.getCodTipo()+ "  " + m.getDesc());

    }
        // TODO code application logic here
}
