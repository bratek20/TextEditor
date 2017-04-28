/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author BRudzki
 */
public class EditorMenu extends JMenuBar {
    private Editor editor;
    public EditorMenu(Editor editor) {
        this.editor = editor;
        JMenu menu = new JMenu("Edit");
        
        createUndoRedo(menu);
        
        menu.addSeparator();
        
        createBaseEditorFunctions(menu);
        
        add(menu);
    }
    
    private void createUndoRedo(JMenu menu){
        JMenuItem undo = new JMenuItem("Undo"); 
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editor.undo();
            }
        });
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        menu.add(undo);
        
        JMenuItem redo = new JMenuItem("Redo"); 
        redo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editor.redo();
            }
        });
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
        menu.add(redo);
    }
    
    private void createBaseEditorFunctions(JMenu menu){
        JMenuItem cut = new JMenuItem("Cut"); 
        cut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editor.cut();
            }
        });
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        menu.add(cut);
        
        JMenuItem copy = new JMenuItem("Copy"); 
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editor.copy();
            }
        });
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        menu.add(copy);
        
        JMenuItem paste = new JMenuItem("Paste"); 
        paste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editor.paste();
            }
        });
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        menu.add(paste);
    }
}
