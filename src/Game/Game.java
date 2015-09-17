package Game;

import Display.Display;
import IO.Input;
import Utils.Time;
import graphics.TextureAtlas;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by ilnurgazizov on 17.09.15.
 */
public class Game implements Runnable{

    public static final int     WIDTH           = 800;
    public static final int     HEIGTH          = 600;
    public static final String  TITLE           = "Tanks";
    public static final int     CLEAR_COLOR     = 0xff000000;
    public static final int     NUM_BUFFERS     = 3;

    public static final float   UPDATE_RATE     = 60.0f; // частота расчета физики
    public static final float   UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE; // Интервалы между update
    public static final long   IDLE_TIME       = 1; // даем "подышать" потоку, в миллисекундах

    public static final String ATLAS_FILE_NAME = "texture_atlas.png";

    private boolean             running;
    private Thread              gameThread;
    private Graphics2D          graphics;
    private Input               input;
    private TextureAtlas        atlas;

    //temp
    float x = 350;
    float y = 250;
    float delta = 0;
    float radius = 50;
    float speed = 3;

    //temp and

    // Конструктор
    public Game(){
        running = false;
        Display.create(WIDTH, HEIGTH, TITLE, CLEAR_COLOR, NUM_BUFFERS);
        graphics = Display.getGraphics();
        input = new Input();
        Display.addInputListener(input);
        atlas = new TextureAtlas(ATLAS_FILE_NAME);
    }

    // Начало игры (синронизован)
    public synchronized void start(){
        if (running) return;

        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Стоп игры (синронизован)
    public synchronized void stop(){
        if (!running) return;

        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cleanUp();
    }

    // Геометрия и физика игры
    private void update(){

        if (input.getKey(KeyEvent.VK_UP)) y -= speed;
        if (input.getKey(KeyEvent.VK_DOWN)) y += speed;
        if (input.getKey(KeyEvent.VK_LEFT)) x -= speed;
        if (input.getKey(KeyEvent.VK_RIGHT)) x += speed;

    }

    // После подсчета физики рисуем следующую сцену с помощью render()
    private void render(){
        Display.clear();
        graphics.setColor(Color.white);

        graphics.drawImage(atlas.cut(0, 0, 32, 32), 300, 300, null);

//        graphics.fillOval((int)(x + Math.sin(delta) * 200), (int)y, (int)(radius * 2), (int)(radius * 2));
        Display.swapBuffers();

    }

    @Override
    public void run() {

        int fps = 0;
        int upd = 0; // сколько апдейтов в сек
        int updl = 0; // сколько раз мы попытались догнать наш апдейт

        long count = 0;

        float delta = 0;

        long lastTime = Time.get();
        while (running){
            long now = Time.get();
            long elapssedTime = now - lastTime; // количество времени которое прошло с тех как наш while бежал в прошлый раз
            lastTime = now;

            count += elapssedTime;

            boolean render = false;
            delta  += ( elapssedTime / UPDATE_INTERVAL ); //количество апдейтов
            while (delta > 1) {
                update();
                upd++;
                delta--;
                if (render){
                    updl++;
                } else {
                    render = true;
                }
            }

            if (render) {
                render();
                fps++;
            } else {
                try {
                    Thread.sleep(IDLE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (count >= Time.SECOND){
                Display.setTitle(TITLE + " || Fps: " + fps + " | Upd: " + upd + " | Updl: " + updl);
                upd = 0;
                fps = 0;
                updl = 0;
                count = 0;
            }

        }
    }

    // Освобождение ресурсов
    private void cleanUp(){
        Display.destroy();
    }
}
