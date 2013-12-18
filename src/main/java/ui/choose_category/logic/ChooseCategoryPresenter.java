package ui.choose_category.logic;

import database.provider.QuestionsProvider;
import model.Module;
import model.SpecialistQuestion;
import model.StandardQuestion;
import ui.learning.view.LearningFrame;
import ui.main_menu.view.MainMenuFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Author: Daniel
 */
public class ChooseCategoryPresenter {
    private JList<Module> moduleJList;
    private JButton choosebtn;

    class ChooseBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (moduleJList.getSelectedIndex() > -1) {
                ListModel<Module> model = moduleJList.getModel();
                Module module = model.getElementAt(moduleJList.getSelectedIndex());
                showLearningFrame(module);
            } else {
                showWarnInfo();
            }
        }

        private void showLearningFrame(Module module) {
            QuestionsProvider questionsProvider =
                    QuestionsProvider.getInstance();

            final List<StandardQuestion> stanQuestionsByModule =
                    questionsProvider.getStanQuestionsByModule(module);

            final List<SpecialistQuestion> specQuestionsByModule =
                    questionsProvider.getSpecQuestionsByModule(module);

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    LearningFrame learningFrame = new LearningFrame(
                            stanQuestionsByModule, specQuestionsByModule);
                    learningFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                    learningFrame.setVisible(true);
                }
            });
        }
    }

    private void showWarnInfo() {
        JOptionPane.showMessageDialog(
                null,
                "Nie wybrano kategorii",
                "Informacja",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public WindowListener getWindowListener() {
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        MainMenuFrame mv = new MainMenuFrame();
                        mv.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                        mv.setVisible(true);
                    }
                });
            }
        };
    }

    public void setModuleJList(JList<Module> moduleJList) {
        this.moduleJList = moduleJList;
    }

    public void setChoosebtn(JButton chooseBtn) {
        chooseBtn.addActionListener(new ChooseBtnListener());
    }
}
