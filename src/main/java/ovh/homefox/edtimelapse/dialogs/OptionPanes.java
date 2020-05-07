/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ovh.homefox.edtimelapse.dialogs;

import javax.swing.JOptionPane;

/**
 *
 * @author aymer
 */
public class OptionPanes {
    
    public boolean displaySuccessfulScreenshots(){
        JOptionPane.showMessageDialog(null, "The program finished taking screenshots!", "Finished!", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    
    public boolean displayCancelledTask(){
        JOptionPane.showMessageDialog(null, "You successfully cancelled the task.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    
    public boolean displayFormError(){
        JOptionPane.showMessageDialog(null, 
                "Interval must be set to a value higher than 5s.\n"
                + "You should set the interval value according to your pc performances.\n"
                + "If there is no duration, this software will keep running until you manually stop it.",
                "Error!", 
                JOptionPane.ERROR_MESSAGE);
        return true;
    }
    
    public boolean displaySuccessfulEncoding(){
        JOptionPane.showMessageDialog(null, "The program finished encoding!", "Finished!", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
}
