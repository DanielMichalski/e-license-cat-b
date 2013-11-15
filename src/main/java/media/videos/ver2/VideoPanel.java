package media.videos.ver2;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 * Date: 15.11.13
 */
public class VideoPanel extends JPanel {
    /**
     * yeah... good idea to add this.
     */
    private static final long serialVersionUID = 5584422798735147930L;
    private Image mImage;
    private Dimension mSize;

    public void setImage(Image image) {
        SwingUtilities.invokeLater(new ImageRunnable(image));
    }

    public void setImageSize(Dimension newSize) {
    }

    private class ImageRunnable implements Runnable {
        private final Image newImage;

        public ImageRunnable(Image newImage) {
            super();
            this.newImage = newImage;
        }

        public void run() {
            VideoPanel.this.mImage = newImage;
            final Dimension newSize = new Dimension(mImage.getWidth(null),
                    mImage.getHeight(null));
            if (!newSize.equals(mSize)) {
                VideoPanel.this.mSize = newSize;
                VideoPanel.this.setSize(mImage.getWidth(null), mImage.getHeight(null));
                VideoPanel.this.setVisible(true);
            }
            repaint();
        }
    }

    public VideoPanel() {
        mSize = new Dimension(0, 0);
        setSize(mSize);
    }

    public synchronized void paint(Graphics g) {
        if (mImage != null)
            g.drawImage(mImage, 0, 0, this);
    }
}
