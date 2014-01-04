package ui.login.logic;

import database.dao.TextsDao;
import database.provider.QuestionsProvider;
import ui.login.view.IWindowCloser;
import ui.splash_screen.SplashScreen;
import util.PeselValidator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Author: Daniel
 * Date: 24.11.13
 */
public class LoginPresenter {
    private IWindowCloser iWindowCloser;

    private JTextField firstNameTF;
    private JTextField lastNameTF;
    private JTextField peselTF;

    public LoginPresenter(IWindowCloser iWindowCloser) {
        this.iWindowCloser = iWindowCloser;
    }

    class LoginBtnListenr implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            PeselValidator peselValidator =
                    new PeselValidator(peselTF.getText());

            if (isFormComplete()) {
                if (peselValidator.isValid()) {
                    loginUser();
                } else {
                    pokazInfoNiepoprawnyPesel();
                }
            } else {
                pokazInfoUzupelnijDane();
            }
        }
    }

    private void pokazInfoUzupelnijDane() {
        JOptionPane.showMessageDialog(null,
                "Uzupełnij wszystkie wymagane dane",
                "Informacja",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void pokazInfoNiepoprawnyPesel() {
        JOptionPane.showMessageDialog(
                null,
                "Podano niepoprawny PESEL",
                "Informacja",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public WindowListener getCloseBtnListener() {
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showCloseConfirmDialog();
            }
        };
    }

    private void showCloseConfirmDialog() {
        UIManager.put("OptionPane.yesButtonText", TextsDao.getText("yesButtonLbl"));
        UIManager.put("OptionPane.noButtonText", TextsDao.getText("noButtonLbl"));
        int answer = JOptionPane.showConfirmDialog(
                null,
                TextsDao.getText("view.confirmDialog.message"),
                TextsDao.getText("view.confirmDialog.title"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

        if (answer == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void loginUser() {
        shuffleQuestions();

        saveFormData();
        iWindowCloser.close();

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SplashScreen(1, null);
            }
        });
    }

    private void shuffleQuestions() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                QuestionsProvider questionsProvider =
                        QuestionsProvider.getInstance();
                questionsProvider.shuffleSelectedQuestions(3);
            }
        });

        thread.start();
    }

    private void saveFormData() {
        System.setProperty("firstName", firstNameTF.getText());
        System.setProperty("lastName", lastNameTF.getText());
        System.setProperty("pesel", peselTF.getText());
    }

    private boolean isFormComplete() {

        return !(firstNameTF.getText().trim().equals("") ||
                lastNameTF.getText().trim().equals("") ||
                peselTF.getText().trim().equals(""));

    }

    public void setFirstNameTF(JTextField firstNameTF) {
        this.firstNameTF = firstNameTF;
    }

    public void setLastNameTF(JTextField lastNameTF) {
        this.lastNameTF = lastNameTF;
    }

    public void setPeselTF(JTextField peselTF) {
        this.peselTF = peselTF;
    }

    public void setLoginBtn(JButton loginBtn) {
        loginBtn.addActionListener(new LoginBtnListenr());
    }
}
