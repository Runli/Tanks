package Display;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

/**
 * Created by ilnurgazizov on 16.09.15.
 */
public abstract class Display {

    private static boolean created = false; // Проверка создания окна
    private static JFrame window;
    private static Canvas content; // Лист рисования

    private static BufferedImage buffer;
    private static int[] bufferData; // массив для бафер имэйджа
    private static Graphics bufferGraphics;
    private static int clearColor; // для очистки buffer

    //  temp
    private static float delta = 0;
    //  temp end


    public static void create(int width, int height, String title, int _clearColor){

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

        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
        bufferGraphics = buffer.getGraphics();

        clearColor = _clearColor;
        created = true;
    }

    public static void clear() {
        Arrays.fill(bufferData, clearColor); // заполняем одинаковыми элементами
    }

    public static void render() {
        bufferGraphics.setColor(new Color(0xff0000ff));
        bufferGraphics.fillOval((int)(350 + (Math.sin(delta) * 200)), 250, 100, 100);
        delta += 0.02f;
    }

    // метод менять то что мы видим внутри канваса на то что мы создали
    public static void swapBuffers(){
        Graphics g = content.getGraphics();
        g.drawImage(buffer, 0, 0, null);
    }

}
