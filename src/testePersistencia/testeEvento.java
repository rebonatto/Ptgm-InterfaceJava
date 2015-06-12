/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testePersistencia;

import modelo.Evento;
import persistencia.EventoDAO;

/**
 *
 * @author rebonatto
 */
public class testeEvento {

     public static void main(String[] args) {
        // TODO code application logic here
         EventoDAO dao = new EventoDAO();
         Evento obj = new Evento();

/*
         obj.setDesc("TESTEEEEE");
        obj.setCodMarca(6);

        if (dao.Insere(obj)){
            System.out.println("inserido");
            int chave = dao.ultimaChave();
            System.out.println(chave);
          }

        else{
            System.out.println("deu erro" + dao.getMensagem());

        }
*/
        for(Evento e : dao.lista())
             System.out.println(e.getCodEvento()+ "  " + e.getDesc() + " " + e.getMontaFO());
         System.out.println(dao.getMensagem());
    }


}
