package ui.login.logic;

import ui.exam.view.ExamFrame;
import ui.login.view.IWindowCloser;
import ui.preloader.PreloaderFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

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
            if (isFormComplete()) {
                loginUser();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Uzupełnij wszystkie wymagane dane",
                        "Informacja",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void loginUser() {
        //TODO dodac loader
        //iWindowCloser.close();

        saveFormData();

        PreloaderFrame frame = new PreloaderFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
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
