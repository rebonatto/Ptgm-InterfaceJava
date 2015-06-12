/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Uteis.Conversoes;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author rebonatto
 */
public class testeConversoes {
    
    @Test
    public void TestaInteiroparatempo() {
        System.out.println("51\t" + Conversoes.IntSegundosToTempo(51));        
        System.out.println("86451\t" + Conversoes.IntSegundosToTempo(86451));        
        System.out.println("806451\t" + Conversoes.IntSegundosToTempo(806451));        
        System.out.println("36443\t" + Conversoes.IntSegundosToTempo(36443));        
        System.out.println("0\t" + Conversoes.IntSegundosToTempo(0));        
        System.out.println("151\t" + Conversoes.IntSegundosToTempo(151));    
        System.out.println("-1560\t" + Conversoes.IntSegundosToTempo(-1560));        
        System.out.println("1560\t" + Conversoes.IntSegundosToTempo(1560));        
    }
    
    @Test
    public void TestaTempoparaInteiro() {
        
        
        System.out.println("2d 0h 0:51\t" + Conversoes.TempoToIntSegundos("2d 0h 0:51"));        
        System.out.println("1d 2h 0:51\t" + Conversoes.TempoToIntSegundos("1d 2h 0:51"));
        System.out.println("0d 0h 10:51\t" + Conversoes.TempoToIntSegundos("0d 0h 10:51"));
        
        System.out.println("");
        System.out.println("0d 0h 0:51\t" + Conversoes.TempoToIntSegundos("0d 0h 0:51"));
        System.out.println("1d 0h 0:51\t" + Conversoes.TempoToIntSegundos("1d 0h 0:51"));                               
        System.out.println("9d 8h 0:51\t" + Conversoes.TempoToIntSegundos("9d 8h 0:51"));                       
        System.out.println("0d 10h 7:23\t" + Conversoes.TempoToIntSegundos("0d 10h 7:23"));        
        System.out.println("0d 0h 0:0\t" + Conversoes.TempoToIntSegundos("0d 0h 0:0"));        
        System.out.println("0d 0h 2:31\t" + Conversoes.TempoToIntSegundos("0d 0h 2:31"));
        System.out.println("0d 0h -26:0\t" + Conversoes.TempoToIntSegundos("0d 0h -26:0"));
        System.out.println("0d 0h 26:0\t" + Conversoes.TempoToIntSegundos("0d 0h 26:0"));
        
        
    }
    
    public testeConversoes() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

}
