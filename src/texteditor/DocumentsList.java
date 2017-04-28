/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author BRudzki
 */
public class DocumentsList extends JPanel{
    Map<Document, JButton> documents;
    private Document current;
    public DocumentsList(){
        super(new FlowLayout(FlowLayout.LEFT));
        //super(new GridLayout(1,5));
        add(Box.createHorizontalGlue());
        documents = new HashMap<Document, JButton>();
    }
    
    public boolean exists(String name){
        Iterator it = documents.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(((Document)pair.getKey()).getTitle().equals(name))
                return true;
        }
        return false;
    }
    
    public void addDocument(Document document){
        JButton documentButton = createDocumentButton(document);
        documents.put(document, documentButton);
        add(documentButton);
        
        getParent().validate();
        getParent().repaint();
    }
    
    public void setCurrentDocument(Document document){
        current = document;
    }
    
    public void saveCurrentDocument(){
        if(current == null){
            JOptionPane.showMessageDialog(null, "NO DOCUMENT SELECTED", "ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        current.save();
    }
    
    public void deleteCurrentDocument(){
        if(current == null){
            JOptionPane.showMessageDialog(null, "NO DOCUMENT SELECTED", "ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }
        JButton documentButton = documents.get(current);
        
        remove(documentButton);
        
        getParent().validate();
        getParent().repaint();
        
        current.delete();
        documents.remove(current);
        current = null;
    }
    
    private JButton createDocumentButton(Document document){
        JButton button = new JButton(document.getTitle());
        button.setToolTipText(document.getTitle());
        button.setPreferredSize(new Dimension(Tools.ICON_WIDTH*3, Tools.ICON_HEIGHT));
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                document.reinit();
                current = document;
            }
        });
        
        return button;
    }
}
