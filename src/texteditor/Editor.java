/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.util.LinkedList;
import javax.swing.JEditorPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author BRudzki
 */
public class Editor extends JEditorPane{
    public final static int MAX_MEMORY_SIZE = 1000;
    LinkedList<String>memory;
    private int idx = 0;
    private boolean myAction = false;
    public Editor(){
        memory = new LinkedList<>();
        memorise();
        
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {               
                memorise();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                memorise();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }
    
    private void memorise(){
        if(myAction)return;
        
        while(memory.size() > idx+1)memory.removeLast();
        while(memory.size() > MAX_MEMORY_SIZE)memory.removeFirst();
        
        memory.add(getText());
        idx = memory.size() -1;
    }
    
    public void undo(){
        if(idx - 1 >= 0){
            idx--;
            myAction = true;
            setText(memory.get(idx));
            myAction = false;
        }
    }
    
    public void redo(){
        if(idx + 1 < memory.size()){
            idx++;
            myAction = true;
            setText(memory.get(idx));
            myAction = false;
        }
    }
}
