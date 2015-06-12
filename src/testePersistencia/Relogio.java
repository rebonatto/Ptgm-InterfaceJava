/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testePersistencia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;
import javax.swing.JLabel;

/**
 *
 * @author cliente
 */
public class Relogio extends JLabel {

    public Relogio() {
        super();
        timerAtualizacao = new Timer (1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setText (df.format(new Date()));
            }
        });
        timerAtualizacao.start();
    }
    public void setFormato (String formato) {
        df = new SimpleDateFormat (formato);
    }
    private Timer timerAtualizacao;
    private DateFormat df = new SimpleDateFormat ("HH:mm:ss");
}

