/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ovh.homefox.edtimelapse.worker;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import ovh.homefox.edtimelapse.EDTimelapse;
import ovh.homefox.edtimelapse.dialogs.OptionPanes;

/**
 * Classe de base pour les workers.
 * @author aymer
 */
public abstract class BaseScreenshotWorker extends SwingWorker<Void, Void>{

    /**
     * Durée de la prise de screenshots.
     */
    protected final long globalLength;
    /**
     * Intervalle entre deux screenshots.
     */
    protected final long timeBetweenEachScreenshot;
    /**
     * ProgressBar à faire évoluer.
     */
    private final JProgressBar timelapseProgressBar;
    /**
     * Frame à  gérer.
     */
    private final EDTimelapse frame;
    /**
     * Touche "Alt" du clavier.
     */
    private final int ALT = KeyEvent.VK_ALT;
    /**
     * Touche "F10" du clavier.
     */
    private final int FTEN = KeyEvent.VK_F10;
    /**
     * Durée de pression des touches.
     */
    protected final int PRESSING = 100;
    /**
     * OptionsPanes pouvant être affichés.
     */
    protected final OptionPanes optionPanes = new OptionPanes();
    /**
     * Robot pour permettre la pression des touches.
     */
    private Robot robot;
    
    /**
     * Constructeur pour un worker n'ayant pas de durée.
     * @param timeBetweenEachScreenshot Intervalle des captures d'écran.
     * @param timelapseProgressBar ProgressBar à gérer.
     * @param frame  Frame à manipuler.
     */
    public BaseScreenshotWorker(long timeBetweenEachScreenshot, JProgressBar timelapseProgressBar, EDTimelapse frame){
        this.globalLength = 0;
        this.timeBetweenEachScreenshot = timeBetweenEachScreenshot;
        this.timelapseProgressBar = timelapseProgressBar;
        this.frame = frame;
        initRobot();
    }
    
    /**
     * Constructeur pour un worker ayant une durée limitée.
     * @param globalLength Durée totale.
     * @param timeBetweenEachScreenshot Intervalle des captures d'écran.
     * @param timelapseProgressBar ProgressBar à mettre à jour.
     * @param frame Frame à manipuler.
     */
    public BaseScreenshotWorker(long globalLength, long timeBetweenEachScreenshot, JProgressBar timelapseProgressBar, EDTimelapse frame){
        this.globalLength = globalLength;
        this.timeBetweenEachScreenshot = timeBetweenEachScreenshot;
        this.timelapseProgressBar = timelapseProgressBar;
        this.frame = frame;
        initRobot();
    }
    
    /**
     * Fonction d'initialisation du robot.
     */
    private void initRobot(){
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(FiniteScreenshotWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Fonction héritée à override.
     * @return Rien.
     * @throws Exception 
     */
    @Override
    protected abstract Void doInBackground() throws Exception;
    
    /**
     * Fonction de remise à zéro de la progressBar.
     */
    protected void resetProgress(){
        timelapseProgressBar.setIndeterminate(false);
        timelapseProgressBar.setValue(0);
        timelapseProgressBar.setString(null);
        frame.activateStart();
    }
    
    /**
     * Fonction de mise en "Indéterminé" de la progressBar.
     */
    protected void setInfiniteProgressBar(){
        timelapseProgressBar.setIndeterminate(true);
        timelapseProgressBar.setString("Running...");
    }
    
    /**
     * Fonction de mise à jour de la progressBar.
     * @param value Valeur à mettre
     */
    protected void updateProgressBar(int value){
        timelapseProgressBar.setValue(value);
    }
    
    /**
     * Fonction de la combinaison des touches pour la prise de screenshots.
     * @throws InterruptedException 
     */
    protected void pressingKeys() throws InterruptedException{
        robot.keyPress(ALT);
        robot.keyPress(FTEN);
        Thread.sleep(PRESSING);
        robot.keyRelease(ALT);
        robot.keyRelease(FTEN);
    }
    
}
