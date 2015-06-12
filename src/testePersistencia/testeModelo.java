/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testePersistencia;

import modelo.Modelo;
import persistencia.ModeloDAO;

/**
 *
 * @author cliente
 */
public class testeModelo {

    public static void main(String[] args) {
        // TODO code application logic here
        ModeloDAO dao = new ModeloDAO();
        Modelo mdl = new Modelo();

        //mdl.setDesc("TESTEEEEE");
        //mdl.setCodModelo(6);

//        if (dao.Insere(mdl)){
//            System.out.println("inserido");
//            int chave = dao.ultimaChave();
//            System.out.println(chave);
//          }
//
//        else{
//            System.out.println("deu erro" + dao.getMensagem());
//
//        }

        for(Modelo m : dao.lista())
             System.out.println(m.getCodModelo() + "  " + m.getDesc());

    }



}
