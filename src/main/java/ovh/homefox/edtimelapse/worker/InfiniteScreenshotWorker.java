/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ovh.homefox.edtimelapse.worker;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import ovh.homefox.edtimelapse.EDTimelapse;

/**
 * Classe de gestion d'un worker à durée illimitée.
 * @author aymer
 */
public class InfiniteScreenshotWorker extends BaseScreenshotWorker {

    public InfiniteScreenshotWorker(long timeBetweenEachScreenshot, JProgressBar timelapseProgressBar, EDTimelapse frame) {
        super(timeBetweenEachScreenshot, timelapseProgressBar, frame);
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        setInfiniteProgressBar();
        while(!this.isCancelled()){
            try {
                pressingKeys();
                Thread.sleep(timeBetweenEachScreenshot);
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
