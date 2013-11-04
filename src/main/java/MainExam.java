import view.exam_view.ExamFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 * Date: 04.11.13
 */
public class MainExam {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ExamFrame frame = new ExamFrame();
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
            }
        });

    }
}
