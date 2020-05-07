/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ovh.homefox.edtimelapse.worker;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import nu.pattern.OpenCV;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.videoio.VideoWriter;
import ovh.homefox.edtimelapse.EDTimelapse;
import ovh.homefox.edtimelapse.dialogs.OptionPanes;

/**
 * Classe permettant la génération d'une vidéo à partir d'images.
 * @author aymer
 */
public class VideoEncodingWorker extends SwingWorker<Void, Void>{

    private final int fps;
    private final String screenshotsPath;
    private final String videoPath;
    private final String videoName;
    private VideoWriter videoWriter;
    private Size frameSize;
    private JProgressBar progressBar;
    private OptionPanes optionPanes = new OptionPanes();
    private final EDTimelapse frame;
    
    public VideoEncodingWorker(int fps, String screenshotsPath, String videoPath, String videoName, JProgressBar progressBar, EDTimelapse frame){
        this.fps = fps;
        this.screenshotsPath = screenshotsPath;
        this.videoPath = videoPath;
        this.videoName = videoName;
        this.progressBar = progressBar;
        this.frame = frame;
        init();
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        File folder = new File(screenshotsPath);
        if(!videoWriter.isOpened()) {
            videoWriter.release();
            throw new IllegalArgumentException("Video Writer Exception: VideoWriter not opened,"
                    + "check parameters.");
        }
        File[] files = folder.listFiles((File dir, String name) -> name.endsWith(".bmp"));
        int folderSize = files.length;
        int i = 0;
        while(i < folderSize && !this.isCancelled()){
            File fileEntry = files[i];
            if(fileEntry.isFile() && fileEntry.getName().endsWith(".bmp")){
                progressBar.setValue((i*100)/(folderSize-1));
                videoWriter.write(convertFrameToMat(fileEntry));
            }
            i++;
        }
        videoWriter.release();
        frame.activateStart();
        if(this.isCancelled()){
            optionPanes.displayCancelledTask();
        }else{
            optionPanes.displaySuccessfulEncoding();
        }
        return null;
    }
    
    /**
     * Permet d'initialiser l'encodage vidéo.
     */
    private void init() {
        OpenCV.loadLocally();
        boolean isRGB = true;
        frameSize = new Size(7680, 4320);
        videoWriter = new VideoWriter(videoPath + System.getProperty("file.separator") +  videoName + ".mp4", 
                VideoWriter.fourcc('x', '2', '6', '4'), 
                fps, 
                frameSize, 
                isRGB);
    }
    
    /**
     * Fonction de conversion d'une image en Mat.
     * @param frame Image à convertir.
     * @return Une Mat contenant l'image.
     * @throws IOException 
     */
    private Mat convertFrameToMat(File frame) throws IOException{
        BufferedImage image = ImageIO.read(frame);//new BufferedImage(7680, 4320, BufferedImage.TYPE_3BYTE_BGR);
        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, pixels);
        return mat;
    }
    
}
