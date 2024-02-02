package com.danielmichalski.elicense.ui.choose_category.logic;

import com.danielmichalski.elicense.model.Module;
import com.danielmichalski.elicense.ui.main_menu.view.MainMenuFrame;
import com.danielmichalski.elicense.ui.splash_screen.SplashScreen;
import com.danielmichalski.elicense.util.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Author: Daniel
 */
public class ChooseCategoryPresenter {
    private JList<Module> moduleJList;
    private Window window;

    public ChooseCategoryPresenter(Window window) {
        this.window = window;
    }

    class ChooseBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (moduleJList.getSelectedIndex() > -1) {
                window.dispose();
                ListModel<Module> model = moduleJList.getModel();
                Module module = model.getElementAt(moduleJList.getSelectedIndex());
                showLearningFrame(module);
            } else {
                showWarnInfo();
            }
        }

        private void showLearningFrame(final Module module) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new SplashScreen(2, module);
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
                        FileUtils.deleteTempFolderContent();

                        window.dispose();

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
