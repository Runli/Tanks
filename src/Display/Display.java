package Display;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ilnurgazizov on 16.09.15.
 */
public abstract class Display {

    private static boolean created = false; // Проверка создания окна
    private static JFrame window;
    private static Canvas content; // Лист рисования

    public static void create(int width, int height, String title){

        //Проверка
        if (created) return;

        window = new JFrame(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content = new Canvas();

        Dimension size = new Dimension(width, height);
        content.setPreferredSize(size); //лист имеет размер width and height

        window.setResizable(false); //нельзя менять размер окна
        window.getContentPane().add(content);
        window.pack(); // контент будет находится внутри окна без потерь
        window.setLocationRelativeTo(null); // меняем позицию окна в зависимости от переданного компонента
        window.setVisible(true);
    }

}
