package media.videos.ver1;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class VideoFrame extends JFrame {

    /**
     * To avoid a warning...
     */

    private static final long serialVersionUID = -4752966848100689153L;
    private final ImageComponent mOnscreenPicture;


    /**
     * Create the frame
     */

    public VideoFrame() {
        super();
        mOnscreenPicture = new ImageComponent();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(mOnscreenPicture);
        this.setVisible(true);
        this.pack();
    }

    public void setImage(final BufferedImage aImage) {
        mOnscreenPicture.setImage(aImage);
    }

    public class ImageComponent extends JComponent {
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
                ImageComponent.this.mImage = newImage;
                final Dimension newSize = new Dimension(mImage.getWidth(null),
                        mImage.getHeight(null));
                if (!newSize.equals(mSize)) {
                    ImageComponent.this.mSize = newSize;
                    VideoFrame.this.setSize(mImage.getWidth(null), mImage.getHeight(null));
                    VideoFrame.this.setVisible(true);
                }
                repaint();
            }
        }

        public ImageComponent() {
            mSize = new Dimension(0, 0);
            setSize(mSize);
        }

        public synchronized void paint(Graphics g) {
            if (mImage != null)
                g.drawImage(mImage, 0, 0, this);
        }
    }
}