package tech.cathywu.excelreader.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SaveButton extends JButton implements ActionListener {

    private List<String> files;

    public SaveButton() {
        super("Process");
        this.addActionListener(this);
    }

    public void receiveFile(List<String> files) {

        this.files = files;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(files);
    }
}
