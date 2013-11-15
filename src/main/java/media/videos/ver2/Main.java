package media.videos.ver2;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * Author: Daniel
 * Date: 15.11.13
 */
public class Main {
    public static void main(String[] args) {
        VideoFrame videoFrame = new VideoFrame();
        videoFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        videoFrame.setVisible(true);
    }
}
