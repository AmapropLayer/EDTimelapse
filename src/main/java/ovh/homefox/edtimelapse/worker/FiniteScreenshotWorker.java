/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ovh.homefox.edtimelapse.worker;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import ovh.homefox.edtimelapse.EDTimelapse;

/**
 * Classe de gestion d'un worker à durée limitée.
 * @author aymer
 */
public class FiniteScreenshotWorker extends BaseScreenshotWorker {

    public FiniteScreenshotWorker(long globalLength, long timeBetweenEachScreenshot, JProgressBar timelapseProgressBar, EDTimelapse frame) {
        super(globalLength, timeBetweenEachScreenshot, timelapseProgressBar, frame);
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        Date current = new Date();
        long finish;
        finish = current.getTime() + globalLength;
        while(current.getTime() + PRESSING < finish && !this.isCancelled()){
            try {
                pressingKeys();
                updateProgressBar((int)(100-((finish-current.getTime())*100)/globalLength));
                Thread.sleep(timeBetweenEachScreenshot);
                current = new Date();
            } catch (InterruptedException e) {
                Logger.getLogger(FiniteScreenshotWorker.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        resetProgress();
        if(this.isCancelled()){
            optionPanes.displayCancelledTask();
        }else{
            optionPanes.displaySuccessfulScreenshots();
        }
        return null;
    }
    
}
