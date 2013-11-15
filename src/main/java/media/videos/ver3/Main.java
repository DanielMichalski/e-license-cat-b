package media.videos.ver3;


/**
 * Author: Daniel
 * Date: 15.11.13
 */
public class Main {
    public static void main(String[] args) {
        VideoFrame frame = new VideoFrame();

        VideoPanel videoPanel = frame.getmOnscreenPicture();


        VideoCodec videoCodec = new VideoCodec(videoPanel);
    }
}
