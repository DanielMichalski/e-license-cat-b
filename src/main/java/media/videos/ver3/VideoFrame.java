package media.videos.ver3;

import javax.swing.*;

public class VideoFrame extends JFrame {

    private static final long serialVersionUID = -4752966848100689153L;
    private final VideoPanel mOnscreenPicture;

    public VideoFrame() {
        mOnscreenPicture = new VideoPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(mOnscreenPicture);
        this.setVisible(true);
        setLocationRelativeTo(null);

        setSize(650, 390);
    }

    public VideoPanel getmOnscreenPicture() {
        return mOnscreenPicture;
    }
}