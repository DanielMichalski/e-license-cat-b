package ui.help.view;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 */
public class HelpDialog extends JDialog implements Disposable {

    public static final int WIDTH = 270;
    public static final int HEIGHT = 230;

    public HelpDialog() {
        setUpDialog();
        initializeComponents();
    }

    private void setUpDialog() {
        setResizable(false);
        setModal(true);
        setTitle("Informacje");
        setSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        HelpPanel helpPanel = new HelpPanel(this);
        add(helpPanel);
    }

    @Override
    public void disposeDialog() {
        dispose();
    }
}
