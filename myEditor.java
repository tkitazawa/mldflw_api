import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.io.*;

public class myEditor extends JFrame {

    Container myContainer = null;

    public myEditor(String title) {
        super(title);
        myContainer = this.getContentPane();
        myContainer.setLayout(new BorderLayout());
        final JTextArea myText = new JTextArea();
        myText.setLineWrap(true);
        JScrollPane myScroll = new JScrollPane(myText);
        myContainer.add(myScroll, BorderLayout.CENTER);
        final JFileChooser myFileChooser = new JFileChooser();

        JMenuBar myMenuBar;
        JMenu myFileMenu;
        JMenu myEditMenu;
        JMenuItem myNewMItem;
        JMenuItem myOpenMItem;
        JMenuItem mySaveMItem;
        JMenuItem myExitMItem;
        JMenuItem myCopyMItem;
        JMenuItem myPasteMItem;
        JMenuItem myCutMItem;
        JMenuItem mySelectMItem;
 
        myMenuBar = new JMenuBar();
        myMenuBar.setBorder(BorderFactory.createBevelBorder(
            BevelBorder.RAISED));
        setJMenuBar(myMenuBar);

        myFileMenu = new JMenu("File");
        
        myFileMenu.setMnemonic('F');

        myNewMItem = new JMenuItem("New", new ImageIcon
            ("new.gif"));
        myNewMItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                myText.setText("");
            }
        });
        myFileMenu.add(myNewMItem);

        myOpenMItem = new JMenuItem("Open", new ImageIcon
            ("open.gif"));
        myOpenMItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int intRet = myFileChooser.showOpenDialog(
                    myEditor.this);
                if (intRet == JFileChooser.APPROVE_OPTION) {
                    try {
                        String strLine;
                        File myFile = myFileChooser.getSelectedFile();
                        BufferedReader myReader = new BufferedReader(
                            new FileReader(myFile.getAbsolutePath()));
                        myText.setText(myReader.readLine());
                        while ((strLine = myReader.readLine()) != null) {
                            myText.append("\n" + strLine);
                        }
                        myReader.close();
                    } catch (IOException ie) {
                    }
                }
            }
        });
        myFileMenu.add(myOpenMItem);

        mySaveMItem = new JMenuItem("Save", new ImageIcon
            ("save.gif"));
        mySaveMItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int intRet = myFileChooser.showSaveDialog(
                    myEditor.this);
                if (intRet == JFileChooser.APPROVE_OPTION) {
                    try {
                        File myFile = myFileChooser.getSelectedFile();
                        PrintWriter myWriter = new PrintWriter(
                            new BufferedWriter(new FileWriter(
                                myFile.getAbsolutePath())));
                        myWriter.write(myText.getText());
                        myWriter.close();
                    } catch (IOException ie) {
                    }
                }
            }
        });
        myFileMenu.add(mySaveMItem);
        
        myFileMenu.addSeparator();

        myExitMItem = new JMenuItem("Exit", new ImageIcon
            ("exit.gif"));
        myExitMItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        myFileMenu.add(myExitMItem);

        myMenuBar.add(myFileMenu);

        myEditMenu = new JMenu("Edit");

        myEditMenu.setMnemonic('E');

        myCopyMItem = new JMenuItem("Copy", new ImageIcon
            ("copy.gif"));
        myCopyMItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                myText.copy();
            }
        });
        myEditMenu.add(myCopyMItem);

        myPasteMItem = new JMenuItem("Paste", new ImageIcon
            ("paste.gif"));
        myPasteMItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                myText.paste();
            }
        });
        myEditMenu.add(myPasteMItem);

        myCutMItem = new JMenuItem("Cut", new ImageIcon
            ("cut.gif"));
        myCutMItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                myText.cut();
            }
        });
        myEditMenu.add(myCutMItem);

        mySelectMItem = new JMenuItem("Select", new ImageIcon
            ("select.gif"));
        mySelectMItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if(myText.getText().length() >= 0)
                    myText.select(0, myText.getText().length());
            }
        });
        myEditMenu.add(mySelectMItem);

        myMenuBar.add(myEditMenu);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                System.exit(0);
            }
        });

        try {
            UIManager.setLookAndFeel(
                "javax.swing.plaf.metal.MetalLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
        }
 
        this.setSize(500, 300);
        this.setVisible(true);
    }
 
    public static void main (String s[]) {
        myEditor myApp = new myEditor("MyEditor");
    }
}
