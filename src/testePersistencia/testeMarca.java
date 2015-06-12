/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testePersistencia;

import modelo.Marca;
import persistencia.MarcaDAO;

/**
 *
 * @author cliente
 * 
 */
public class testeMarca {

     public static void main(String[] args) {
        // TODO code application logic here
        MarcaDAO dao = new MarcaDAO();
        Marca obj = new Marca();

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

        for(Marca m : dao.lista())
             System.out.println(m.getCodMarca() + "  " + m.getDesc());

    }


}
