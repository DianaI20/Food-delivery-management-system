package PresentationLayer;

import javax.swing.*;
import java.awt.*;

public class ImportFileFrame extends JFrame {
    Container mainPanel;
    JTextField csvPath;
    JButton importBtn;

    public ImportFileFrame() throws HeadlessException {

        this.setTitle("Import CSV file");
        mainPanel = this.getContentPane();
        mainPanel.setLayout(null);
        mainPanel.setSize(480,115);

        JLabel filePath = new JLabel("File path:");
        filePath.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        filePath.setLocation(20, 20);
        filePath.setSize(200, 20);
        mainPanel.add(filePath);

        csvPath = new JTextField();
        csvPath.setSize(250, 20);
        csvPath.setLocation(80, 20);
        mainPanel.add(csvPath);

        importBtn = new JButton("Import");
        importBtn.setSize(90, 20);
        importBtn.setLocation(340, 20);
        mainPanel.add(importBtn);

        this.setSize(480,115);
        this.setVisible(true);
    }

    public JTextField getCsvPath() {
        return csvPath;
    }

    public void setCsvPath(JTextField csvPath) {
        this.csvPath = csvPath;
    }

    public JButton getImportBtn() {
        return importBtn;
    }

    public void setImportBtn(JButton importBtn) {
        this.importBtn = importBtn;
    }
}
