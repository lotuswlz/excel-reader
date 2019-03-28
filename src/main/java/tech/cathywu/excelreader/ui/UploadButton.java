package tech.cathywu.excelreader.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UploadButton extends JButton {

    public UploadButton(ActionListener actionListener) {
        super("Choose File");
        this.addActionListener(actionListener);
    }
}
