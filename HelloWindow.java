import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HelloWindow {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My Notepad");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced" , Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane,BorderLayout.CENTER);

        // Status bar (word + character counter)
        JLabel statusLabel = new JLabel("Words: 0 | Characters: 0");
        frame.add(statusLabel, BorderLayout.SOUTH);

        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = textArea.getText();
                int words = text.isBlank() ? 0 : text.trim().split("\\s+").length;
                int chars = text.length();
                statusLabel.setText("Words: " + words + " | Characters: " + chars);
            }
        });
     
        JMenuBar menubar = new JMenuBar();

        JMenu formatMenu = new JMenu("Format");
        JMenuItem colorItem = new JMenuItem("Text Color....");

        colorItem.addActionListener(e ->{ 
            Color chosen = JColorChooser.showDialog(frame, "Choose text color", textArea.getForeground());
            if (chosen != null ){
                textArea.setForeground(chosen);
            }
       
        });
        formatMenu.add(colorItem);
        menubar.add(formatMenu);

        JMenuItem fontItem = new JMenuItem("Font Size 20");
        fontItem.addActionListener(e -> textArea.setFont(new Font("Monospaced", Font.PLAIN,20)));
        formatMenu.add(fontItem);

        JMenu editMenu = new JMenu("Edit");
        JMenuItem cutItem = new JMenuItem("Cut");
        cutItem.addActionListener(e-> textArea.cut());

        JMenuItem copyItem = new JMenuItem("Copy");
        copyItem.addActionListener(e-> textArea.copy());

        JMenuItem pasteItem = new JMenuItem("Paste");
        pasteItem.addActionListener(e-> textArea.paste());

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        //help menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e-> {
            JOptionPane.showMessageDialog(frame, "My Notepad", "About",JOptionPane.INFORMATION_MESSAGE);

        });
        helpMenu.add(aboutItem);

        //Add menus to menubar
        menubar.add(editMenu);
        menubar.add(helpMenu);

        //Attach menu bar to frame
        frame.setJMenuBar(menubar);
        
        frame.setVisible(true );
    }
}