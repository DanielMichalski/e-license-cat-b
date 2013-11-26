package ui.login.logic;

import ui.login.view.IWindowCloser;
import ui.splash_screen.SplashScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        saveFormData();

        iWindowCloser.close();

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    new SplashScreen();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Wystąpił błąd: " + e,
                            "Informacja",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
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
