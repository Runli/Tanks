package IO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

/**
 * Created by ilnurgazizov on 17.09.15.
 */
public class Input extends JComponent{

    private boolean[]   map;

    public Input(){

        map = new boolean[256];

        for (int i = 0; i < map.length; i++){

            final int KEY_CODE = i; // трюк, для использования в анонимус иннер классе

            // говорит что нажатие кнопок нужно ловить только когда окно игры в фокусе
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke(i, 0, false), i * 2 ); // когда кнопка нажата
            getActionMap().put(i * 2, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    map[KEY_CODE] = true;
                }
            });

            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put( KeyStroke.getKeyStroke(i, 0, true), i * 2 + 1); // когда кнопка отпущена
            getActionMap().put(i * 2 + 1, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    map[KEY_CODE] = false;
                }
            });


        }

    }

    public boolean[] getMap(){
        return Arrays.copyOf(map, map.length);
    }

    public boolean getKey(int keyCode){
        return map[keyCode];
    }

}
