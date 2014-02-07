package ui.application_info.logic;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Author: Daniel
 */
public class ShowInfoPresenter {

    class SluchaczKiknięciaOk extends MouseAdapter {
        private JDialog dialog;

        public SluchaczKiknięciaOk(JDialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            dialog.dispose();
        }
    }

    public void setAboutApp(JLabel aboutApp, JDialog dialog) {
        aboutApp.addMouseListener(new SluchaczKiknięciaOk(dialog));
    }
}
