/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author BRudzki
 */
public class MainWindow extends JFrame{
    public final static int WIDTH = 800, HEIGHT = 600;
    private JDesktopPane desktop;
    public MainWindow(){
        super("TextEditor");
        
        setLocation(100,100);
        setSize(WIDTH, HEIGHT);
        
        desktop = new JDesktopPane();
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(desktop);
        getContentPane().add(new Tools(desktop),BorderLayout.NORTH);
        getContentPane().add(new JPanel(),BorderLayout.WEST);
        getContentPane().add(new JPanel(),BorderLayout.EAST);
        
        JButton lAndFChange = new JButton("L&F");
        lAndFChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLookAndFeel();
            }
        });
        
        JPanel glassPane = (JPanel)getGlassPane();
        glassPane.setLayout(new BoxLayout(glassPane, BoxLayout.Y_AXIS));
        glassPane.add(Box.createVerticalGlue());
        glassPane.add(lAndFChange);
        glassPane.setVisible(true);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private boolean crossPlatformLAF = true;
    private void changeLookAndFeel(){
        try {
            if(crossPlatformLAF)
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            else
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            
            crossPlatformLAF = !crossPlatformLAF;
            SwingUtilities.updateComponentTreeUI(MainWindow.this);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
