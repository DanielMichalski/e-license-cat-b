package media.videos;

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

        VideoPanel videoPanel = getmOnscreenPicture();

        VideoCodec videoCodec = new VideoCodec(videoPanel, "PICT0577_ZS_ITS.mp4");

        remove(videoPanel);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Ok"));
        add(panel);
        revalidate();
    }

    public VideoPanel getmOnscreenPicture() {
        return mOnscreenPicture;
    }
}