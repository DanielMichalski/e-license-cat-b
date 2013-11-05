package util;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 * Date: 05.11.13
 */
public class Utils {
    public static void setSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ignored) {
        } catch (InstantiationException ignored) {
        } catch (IllegalAccessException ignored) {
        } catch (UnsupportedLookAndFeelException ignored) {
        }
    }

    public static void setApplicationIcon(Window owner) {
        Toolkit toolkit = owner.getToolkit();

        Image IconImage = toolkit.createImage(Utils.class.getResource("/program_images/ikona.gif"));
        owner.setIconImage(IconImage);
    }
}
