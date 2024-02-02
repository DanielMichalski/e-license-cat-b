package com.danielmichalski.elicense.main;

import com.danielmichalski.elicense.ui.choose_category.view.ChooseCategoryFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Daniel
 */
public class ChoseCategoryTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ChooseCategoryFrame frame = new ChooseCategoryFrame();
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
