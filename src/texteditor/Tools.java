/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

/**
 *
 * @author BRudzki
 */
public class Tools extends JToolBar{
    public final static int ICON_WIDTH=35, ICON_HEIGHT=40;
    private DocumentsList documents;
    private JDesktopPane desktop;
    public Tools(JDesktopPane desktop){
        this.desktop = desktop;
        try {
            add(createButton("../images/addIcon.png",addAction));
            add(createButton("../images/saveIcon.jpg", saveAction));
            add(createButton("../images/closeIcon.png", closeAction));
            
            documents = new DocumentsList();
            JScrollPane scroll = new JScrollPane(documents, 
                            JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            add(scroll);
            
        } catch (IOException ex) {
            Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private ActionListener addAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog("Input name of document");
            if(name==null)return;
            
            if(name.equals("")){
                JOptionPane.showMessageDialog(null, "Name can not be empty", "ERROR",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(documents.exists(name)){
                JOptionPane.showMessageDialog(null, "Document: "+name+", already exists!", "ERROR",JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            documents.addDocument(new Document(desktop, documents, name));
        }
    };
    
    private ActionListener saveAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            documents.saveCurrentDocument();
        }
    };
    
    private ActionListener closeAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            documents.deleteCurrentDocument();
        }
    };
    
    private JButton createButton(String iconPath, ActionListener action) throws IOException{
        Image img = ImageIO.read(new File(iconPath));
        Image newImg = img.getScaledInstance( ICON_WIDTH, ICON_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ;  

        JButton button = new JButton( new ImageIcon( newImg ));
        button.addActionListener(action);
        button.setContentAreaFilled(false); 
        
        return button;  
    }
}
