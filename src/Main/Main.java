package Main;

import Display.Display;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;

/**
 * Created by ilnurgazizov on 16.09.15.
 */
public class Main {
    public static void main(String[] args) {

        Display.create(800, 600, "Tanks", 0xff00ff00);

        // таймер
        Timer t = new Timer(1000 / 60, new AbstractAction() {

            public void actionPerformed(ActionEvent e){
                Display.clear();
                Display.render();
                Display.swapBuffers();
            }
        });

        t.setRepeats(true);
        t.start();
    }
}
