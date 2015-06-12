/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testePersistencia;

import modelo.Procedimento;
import persistencia.ProcedimentoDAO;

/**
 *
 * @author cliente
 */
public class testeProced {

     public static void main(String[] args) {
        // TODO code application logic here
        ProcedimentoDAO dao = new ProcedimentoDAO();
        Procedimento obj = new Procedimento();

        obj.setDesc("opaaa");
        //obj.setCodProced(6);

        if (dao.Insere(obj)){
            System.out.println("inserido");
            int chave = dao.ultimaChave();
            System.out.println(chave);
          }

        else{
            System.out.println("deu erro" + dao.getMensagem());

        }

        for(Procedimento m : dao.lista())
             System.out.println(m.getCodProced() + "  " + m.getDesc());

    }


}
