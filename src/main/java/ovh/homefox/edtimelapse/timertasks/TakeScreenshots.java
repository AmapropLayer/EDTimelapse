/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ovh.homefox.edtimelapse.timertasks;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.TimerTask;

/**
 *
 * @author aymer
 */
public class TakeScreenshots extends TimerTask {
    final int ALT = KeyEvent.VK_ALT;
    final int FTEN = KeyEvent.VK_F10;

    @Override
    public void run() {
        try {
            Robot robot = new Robot();
            robot.keyPress(ALT);
            robot.keyPress(FTEN);
            robot.keyRelease(ALT);
            robot.keyRelease(FTEN);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
