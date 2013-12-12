package ui.splash_screen;

import ui.exam.view.ExamFrame;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.util.List;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class SplashScreen {
    private JDialog dialog;
    private JProgressBar progress;

    public SplashScreen() throws MalformedURLException {
        initUI();
    }

    public void initUI() throws MalformedURLException {
        showSplashScreen();
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {

            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(10);
                    publish(i);
                }
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                progress.setValue(chunks.get(chunks.size() - 1));
            }

            @Override
            protected void done() {
                showFrame();
            }

        };
        worker.execute();
    }

    protected void hideSplashScreen() {
        dialog.setVisible(false);
        dialog.dispose();
    }

    protected void showSplashScreen() throws MalformedURLException {
        dialog = new JDialog((Frame) null);
        dialog.setSize(250, 15);
        dialog.setModal(false);
        dialog.setUndecorated(true);
        JLabel background = new JLabel();
        background.setLayout(new BorderLayout());
        dialog.add(background);
        JLabel text = new JLabel("Loading, please wait...");
        text.setForeground(Color.WHITE);
        text.setBorder(BorderFactory.createEmptyBorder(100, 50, 100, 50));
        //background.add(text);
        progress = new JProgressBar();
        background.add(progress, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    protected void showFrame() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ExamFrame examFrame = new ExamFrame(dialog);
                examFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                examFrame.setVisible(true);
            }
        });

    }
}