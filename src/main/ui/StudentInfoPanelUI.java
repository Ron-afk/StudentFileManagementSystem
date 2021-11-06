package ui;

import javax.swing.*;

public class StudentInfoPanelUI extends JScrollPane {
    private boolean textEditable = true;
    private JPanel panel;
    private JScrollPane scrollPane;

    public StudentInfoPanelUI() {

    }

    public void testFieldEditable(boolean editable) {
        this.textEditable = editable;
    }
}
