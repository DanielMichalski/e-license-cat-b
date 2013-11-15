package media.videos.ver2;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class VideoFrame extends JFrame {
    private final VideoPanel mOnscreenPicture;

    public VideoFrame() {


        setSize(700, 500);
        mOnscreenPicture = new VideoPanel();
        getContentPane().add(mOnscreenPicture);

        VideoCodec videoCodec = new VideoCodec(this);
    }

    public void setImage(final BufferedImage aImage) {
        mOnscreenPicture.setImage(aImage);
    }
}