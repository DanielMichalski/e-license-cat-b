package ui.splash_screen;

import model.Module;
import model.SpecialistQuestion;
import model.StandardQuestion;
import ui.exam.view.ExamFrame;
import ui.learning.view.LearningFrame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class SplashScreen {
    private int windowType;
    private Module module;
    private List<StandardQuestion> standardQuestions;
    private List<SpecialistQuestion> specialistQuestions;

    private JDialog dialog;
    private JProgressBar progress;

    public SplashScreen(int windowType,
                        Module module,
                        List<StandardQuestion> standardQuestions,
                        List<SpecialistQuestion> specialistQuestions) {

        this.module = module;
        this.standardQuestions = standardQuestions;
        this.specialistQuestions = specialistQuestions;
        this.windowType = windowType;
        initUI();
    }

    public void initUI() {
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
                hideSplashScreen();
                showFrame();
            }

        };
        worker.execute();
    }

    protected void hideSplashScreen() {
        dialog.setVisible(false);
        dialog.dispose();
    }

    protected void showSplashScreen() {
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
                if (windowType == 1) {
                    ExamFrame examFrame = new ExamFrame(dialog);
                    examFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                    examFrame.setVisible(true);
                }
                if (windowType == 2) {
                    LearningFrame learningFrame = new LearningFrame(module, standardQuestions, specialistQuestions);
                    learningFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                    learningFrame.setVisible(true);
                }
            }
        });

    }
}