/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testePersistencia;

/**
 *
 * @author cliente
 */
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;



public class ExemploRelogio extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private Relogio lblRelogio = null;

    /**
     * This is the default constructor
     */
    public ExemploRelogio() {
        super();
        initialize();
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
        this.setSize(300, 200);
        this.setContentPane(getJContentPane());
        this.setTitle("JFrame");
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            lblRelogio = new Relogio();
            lblRelogio.setHorizontalAlignment(SwingConstants.CENTER);
            lblRelogio.setVerticalAlignment(SwingConstants.CENTER);
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(lblRelogio, BorderLayout.CENTER);
        }
        return jContentPane;
    }
  public static void main(String[]args) {

      new  ExemploRelogio().setVisible(true);  }  
}
