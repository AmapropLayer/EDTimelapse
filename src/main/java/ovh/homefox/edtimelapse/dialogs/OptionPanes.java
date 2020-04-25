/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ovh.homefox.edtimelapse.dialogs;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

/**
 *
 * @author aymer
 */
public class OptionPanes {
    
    public boolean displaySuccessfulScreenshots(){
        JOptionPane.showMessageDialog(null, "The program finished taking screenshots!");
        return true;
    }
    
    public boolean displayProgressBar(){
        JProgressBar progress = new JProgressBar(1, 100);
        String remaining = "Time remaining: ";
        Object[] message = {
            "Time remaining: ",
            "Progress: ", progress
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Taking screenshots...", JOptionPane.OK_CANCEL_OPTION);
        return (option == JOptionPane.OK_OPTION);
    }
}
