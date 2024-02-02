package com.danielmichalski.elicense.ui.choose_category.model;

import com.danielmichalski.elicense.model.Module;

import javax.swing.*;
import java.util.List;

/**
 * Author: Daniel
 */
public class ModuleListModel extends AbstractListModel<Module> {
    private List<Module> modules;

    public ModuleListModel(List<Module> modules) {
        this.modules = modules;
    }

    @Override
    public int getSize() {
        return modules.size();
    }

    @Override
    public Module getElementAt(int index) {
        return modules.get(index);
    }
}
