/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.awt.BorderLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author BRudzki
 */
public class Document extends JInternalFrame{
    public final static int WIDTH = 500, HEIGHT = 300;
    private JDesktopPane parent;
    private Editor editor;
    public Document(JDesktopPane parent, DocumentsList list, String name){
        super(name,true,true,true);
        
        this.parent = parent;
        
        editor = new Editor();
        add(new EditorMenu(editor), BorderLayout.NORTH);
        
        JScrollPane scroll = new JScrollPane(editor);
        add(scroll,BorderLayout.CENTER);
    
        parent.add(this);
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                list.setCurrentDocument(Document.this);
            }

            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                list.setCurrentDocument(null);
            }     
        });
        
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        reinit();
    }
    
    public void save(){
        System.out.print("\n"+getTitle()+" :\n");
        System.out.print(editor.getText());
        
        String sb = editor.getText();
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File(getTitle()));
        
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter fw = new FileWriter(chooser.getSelectedFile()+".txt");
                fw.write(sb.toString());
                fw.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void reinit(){
        setLocation(0, 0);
        setSize(WIDTH,HEIGHT);
        moveToFront();
        setVisible(true);
    }
    
    public void delete(){
        parent.remove(this);
        parent.repaint();
    }
}
