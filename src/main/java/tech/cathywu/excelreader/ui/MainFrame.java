package tech.cathywu.excelreader.ui;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame implements ActionListener {

    private final JLabel filePathLabel;
    private final SaveButton saveButton = new SaveButton();

    private List<String> fileList = new ArrayList<>();

    public MainFrame() {
        super("Upload Excel");
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(new UploadButton(this), BorderLayout.NORTH);
        filePathLabel = new JLabel("");
        panel.add(filePathLabel, BorderLayout.CENTER);
        panel.add(saveButton, BorderLayout.SOUTH);
        this.add(panel);
    }

    public static void main(String[] args) {
        // Create and set up a frame window
        MainFrame cl = new MainFrame();
        cl.setSize(400, 200);
        cl.setVisible(true);
        cl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            String absolutePath = selectedFile.getAbsolutePath();
            fileList.add(absolutePath);
            saveButton.receiveFile(fileList);
            System.out.println("file selected: " + absolutePath);
            drawFileListLabel();
        }
    }

    private void drawFileListLabel() {

        StringBuffer content = new StringBuffer();
        content.append("<html>");

        fileList.forEach(file -> content.append("<pre>").append(file).append("</pre><br/>"));

        content.append("</html>");

        filePathLabel.setText(content.toString());

        filePathLabel.repaint();
    }
}
