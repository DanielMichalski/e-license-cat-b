package controller;

import util.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: Daniel
 * Date: 03.11.13
 */
public class ExamPresenter {
    private JButton yesBtn;
    private JButton noBtn;

    class YesBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            markBtn(yesBtn);
        }
    }

    class NoBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            markBtn(noBtn);
        }
    }

    private void markBtn(JButton whichButtonToMark) {
        unmarkBtns();
        whichButtonToMark.setBackground(Const.Colors.markedBtnBgColor);
    }

    private void unmarkBtns() {
        yesBtn.setBackground(Const.Colors.unmarkedBtnBgColor);
        noBtn.setBackground(Const.Colors.unmarkedBtnBgColor);
    }


    public void setYesBtn(JButton yesBtn) {
        this.yesBtn = yesBtn;
        this.yesBtn.addActionListener(new YesBtnListener());
    }

    public void setNoBtn(JButton noBtn) {
        this.noBtn = noBtn;
        this.noBtn.addActionListener(new NoBtnListener());
    }
}
