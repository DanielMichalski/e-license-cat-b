package test.main;

import ui.emax_info_dialog.model.NextActionType;
import ui.emax_info_dialog.view.ShowInfoDialog;

/**
 * Author: Daniel
 */
public class ShowInfoDialogTest {
    public static void main(String[] args) {
        ShowInfoDialog showInfoDialogTest = new ShowInfoDialog(NextActionType.start_exam);
        showInfoDialogTest.setVisible(true);
    }
}
