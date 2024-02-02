package com.danielmichalski.elicense.ui.help.view;

import com.danielmichalski.elicense.util.IconUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: Daniel
 */
public class HelpPanel extends JPanel {

    public static final String DEVELOPER_EMAIL = "E-mail developera: kontakt@luckyit.org";
    public static final String OBSLUGA = "Obsługa: liwona@liwona.pl";

    Disposable disposable;

    public HelpPanel(Disposable disposable) {
        this.disposable = disposable;

        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        setLayout(null);
    }

    private void initializeComponents() {
        JLabel logo = new JLabel(IconUtils.getLogo());
        JLabel developerInfo = new JLabel(DEVELOPER_EMAIL);
        JLabel obsluga = new JLabel(OBSLUGA);
        BtnPanel btnPanel = new BtnPanel();

        logo.setBounds(25, 10, 200, 70);
        developerInfo.setBounds(20, 50, 250, 100);
        obsluga.setBounds(20, 75, 250, 100);
        btnPanel.setBounds(30, 150, 200, 50);

        add(logo);
        add(developerInfo);
        add(obsluga);
        add(btnPanel);
    }

    class BtnPanel extends JPanel {
        public BtnPanel() {
            JButton zamknijBtn = new JButton("Zamknij");
            zamknijBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    disposable.disposeDialog();
                }
            });
            add(zamknijBtn);
        }
    }
}
