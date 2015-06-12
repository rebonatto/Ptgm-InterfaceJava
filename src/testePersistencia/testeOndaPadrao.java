/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * @author Eduardo
 * Modified by: rebonatto
 */
 
package testePersistencia;

import Uteis.Conversoes;
import modelo.CapturaAtual;
import modelo.Equipamento;
import modelo.HarmPadrao;
import modelo.OndaPadrao;
import modelo.TipoOnda;
import modelo.TipoPadrao;
import persistencia.CapturaAtualDAO;
import persistencia.EquipamentoDAO;
import persistencia.OndaPadraoDAO;

/**
 *
 * @author Eduardo
 * Modified by: rebonatto
 */
 
public class testeOndaPadrao {

    public static void main(String[] args) {
        // TODO code application logic here
        OndaPadraoDAO dao = new OndaPadraoDAO();
        EquipamentoDAO eqdao = new EquipamentoDAO();
        Equipamento equip = new Equipamento();
        CapturaAtual cap = new CapturaAtual();
        CapturaAtualDAO capdao = new CapturaAtualDAO();
        

//        EventosDAO evdao = new EventosDAO();
//        
//        System.out.println(evdao.localiza(1).getCodEvento() +" " + evdao.localiza(1).getDesc());
//        System.out.println(evdao.getMensagem());
//        
//        System.out.println();               
        
        equip.setCodEquip(2);
        TipoPadrao tip = new TipoPadrao();
        TipoOnda tipo = new TipoOnda();
        tipo.setCodTipoOnda(2);
        tip.setCodTipoPadrao(1);
        
        cap = capdao.localiza(5);
        
        //dao.Insere(cap, tip);
        //System.out.println(dao.getMensagem());        
        
        for(OndaPadrao op : dao.lista(equip, tipo)){
            System.out.println("Codigo " + op.getCodOndaPadrao());
            System.out.println("Tomada: " + op.getCodTomada().getCodTomada() + " " + op.getCodTomada().getDesc() );
            System.out.println("TipoOnda " + op.getCodTipoOnda().getCodTipoOnda() + " " + op.getCodTipoOnda().getDesc() );
            System.out.println("Equipamento " + op.getCodEquip().getCodEquip()+ " " + op.getCodEquip().getDesc() );            
            System.out.println("ValorMedio " + op.getValorMedio()+ " Offset " + op.getOffset() );
            System.out.println("gain " + op.getGain()+ " eficaz " + op.getEficaz());
            System.out.println("Data " + Conversoes.CalendarToDate(op.getDataPadrao() ));
            System.out.println("Hora " + Conversoes.CalendarToTime(op.getDataPadrao()));
            /*
            for(HarmPadrao hp : op.getHarmPadrao())
                System.out.println("Harmonica " + hp.getHarmonica() + " Sen " + hp.getSen() + " Cos " + hp.getCos());
            */
            System.out.println("----------------------------------");
        }
        
        System.out.println(dao.getMensagem());
    
    //    System.out.println(dao.getMensagem());
        
        /*
        System.out.println("\nOnda 1");
        CapturaOndaAtual coa = dao.localiza(1);
        System.out.println(dao.getMensagem());
        System.out.println("Codigo " + coa.getCodOndaAtual() );
        System.out.println("Tomada " + coa.getCodTomada().getCodTomada() + " " + coa.getCodTomada().getDesc() );
        System.out.println("Tipo " + coa.getCodTipoOnda().getCodTponda()+ " " + coa.getCodTipoOnda().getDesc() );
        System.out.println("Equipamento " + coa.getCodEquipamento().getCodEquip()+ " " + coa.getCodEquipamento().getDesc() );
        System.out.println("Evento " + coa.getCodEvento().getCodEvento()+ " " + coa.getCodEvento().getDesc() );
        System.out.println("ValorMedio " + coa.getValorMedio()+ " Offset " + coa.getOffset() );
        System.out.println("gain " + coa.getGain()+ " eficaz " + coa.getEficaz());
        System.out.println("Data " + coa.getDataAtual() );
        for(OndaAtual oa : coa.getHarmAtual())
            System.out.println("Harmonica " + oa.getHarmonica() + " Sen " + oa.getSen() + " Cos " + oa.getCoss());
                
        */        
        
//        equip.setDesc("TESTEEEEE");
//        mdl.setCodModelo(5);
//        tp.setCodTipo(7);
//        mca.setCodMarca(31);
//        
//        equip.setCodMarca(mca);
//        equip.setCodModelo(mdl);
//        equip.setCodTipo(tp);
//        equip.setDataUltimaFalha(Calendar.getInstance());
//        equip.setDataUltimaManutencao(Calendar.getInstance());   
//        equip.setRfid("123456");
//        equip.setCodPatrimonio("Meu");
//
////        if (eqdao.Insere(equip)){
////            System.out.println("inserido");
////            int chave = dao.ultimaChave();
////            System.out.println(chave);
////          }
////
////        else{
////            System.out.println("deu erro" + eqdao.getMensagem());
////
////        }
//
//        for(Equipamento eq : eqdao.lista())
//             System.out.println(eq.getCodEquip() + "  " + eq.getDesc() + "  " + eq.getCodMarca().getCodMarca());

       // System.out.println("Lista");
//        for(Equipamento equip : eqdao.lista()){
//            System.out.println(equip.getCodEquip());
//            System.out.println(equip.getCodMarca().getCodMarca());
//            System.out.println(equip.getCodModelo().getCodModelo());
//            System.out.println(equip.getCodTipo().getCodTipo());
//            System.out.println(equip.getDesc());
//
//
//            System.out.println("--------------------------------------");
//        }

    }

   }
