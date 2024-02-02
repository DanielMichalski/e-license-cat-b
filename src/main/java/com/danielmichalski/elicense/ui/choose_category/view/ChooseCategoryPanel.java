package com.danielmichalski.elicense.ui.choose_category.view;

import com.danielmichalski.elicense.database.provider.ModuleProvider;
import com.danielmichalski.elicense.model.Module;
import com.danielmichalski.elicense.ui.choose_category.model.ModuleListModel;
import com.danielmichalski.elicense.util.Const;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Author: Daniel
 */
public class ChooseCategoryPanel extends JPanel {
    private JList<Module> moduleJList;
    private JButton choosebtn;

    public ChooseCategoryPanel() {
        setUpPanel();
        initializeComponents();
    }

    private void setUpPanel() {
        setLayout(new BorderLayout());
        setBackground(Const.Colors.BACKGROUND_COLOR);
    }

    private void initializeComponents() {
        ModuleProvider moduleProvider =
                ModuleProvider.getInstance();

        List<Module> allModules =
                moduleProvider.getAllModules();

        ModuleListModel model = new ModuleListModel(allModules);

        moduleJList = new JList<Module>(model);
        moduleJList.setFont(new Font("Arial", Font.PLAIN, 14));
        moduleJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(moduleJList);


        BtnPanel btnPanel = new BtnPanel();

        add(scrollPane, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

    class BtnPanel extends JPanel {
        public BtnPanel() {
            setBackground(Const.Colors.BACKGROUND_COLOR);
            choosebtn = new JButton("Wybierz");
            add(choosebtn);
        }
    }

    public JButton getChoosebtn() {
        return choosebtn;
    }

    public JList<Module> getModuleJList() {
        return moduleJList;
    }
}
