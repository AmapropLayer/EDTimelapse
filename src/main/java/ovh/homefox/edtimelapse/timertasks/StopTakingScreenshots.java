/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ovh.homefox.edtimelapse.timertasks;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import ovh.homefox.edtimelapse.dialogs.OptionPanes;

/**
 *
 * @author aymer
 */
public class StopTakingScreenshots extends TimerTask {
    Timer timerToStop;
    JButton buttonToActivate;
    OptionPanes optionPanes = new OptionPanes();

    public StopTakingScreenshots(Timer timerToStop, JButton buttonToActivate) {
        this.timerToStop = timerToStop;
        this.buttonToActivate = buttonToActivate;
    }
    
    @Override
    public void run() {
        timerToStop.cancel();
        timerToStop.purge();
        buttonToActivate.setEnabled(true);
        optionPanes.displaySuccessfulScreenshots();
    }
}
