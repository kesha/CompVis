package kesha.compvis;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author v.kuravskiy
 */
public class App {

    static {
        String workingDir = System.getProperty("user.dir");
        String openCvLibDir = "\\opencv\\x64\\";
        System.load(workingDir + openCvLibDir + Core.NATIVE_LIBRARY_NAME + ".dll");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("start");
        Mat image = Highgui.imread("converis.png");
        Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2GRAY);
        showResult(image);
    }

    public static void showResult(Mat img) {
        Imgproc.resize(img, img, new Size(640, 480));
        MatOfByte matOfByte = new MatOfByte();
        Highgui.imencode(".png", img, matOfByte);
        byte[] byteArray = matOfByte.toArray();
        BufferedImage bufImage = null;
        try {
            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
            JFrame frame = new JFrame();
            frame.getContentPane().add(new JLabel(new ImageIcon(bufImage)));
            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
