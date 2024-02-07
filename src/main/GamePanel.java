package main;

import entity.Isaac;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // Configuración de la pantalla
    final int originalTitleSize = 32; // 32x32 Personajes
    final int scale = 3; // Rescalar el tam 32x3 = 96

    public final int tileSize = originalTitleSize*scale; // 48x48
    public final int maxScreenCol = 15;
    public final int maxScreenRow = 9;
    public final int screenWidth = tileSize*maxScreenCol; //768 pixeles
    public final int screenHeight = tileSize*maxScreenRow; //576 pixeles

    //Config MUNDO
    public final int maxWorldCol = 15;
    public final int maxWorldRow = 9;
    public final int worldWidth = tileSize*maxWorldCol;
    public final int worldHeight = tileSize*maxWorldRow;

    //FPS
    int FPS = 60;

    //La clase thread nos permite crear el bucle del juego.
    //Key handler es el control del personaje
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Isaac isaac1 = new Isaac(this,keyH);
    public SuperObject obj[] = new SuperObject[10];

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));//tam establecido
        this.setBackground(Color.BLACK); //Color del fondo
        this.setDoubleBuffered(true); //Mejor rendimiento
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setupGame(){
        aSetter.setObject();
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    //El metodo run es llamado por Thread para ejucutar el bucle del juego.
    @Override
    public void run() {
        //Dibujamos algo cada 0.016666 segundos
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){
            //Metodo delta para la actualizacion del dibujado
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1){
                //Update de la informacion de la ubicacion del personaje
                update();
                //Dibujar en pantalla la informacion en pantalla
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update(){
        isaac1.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        //Imprimir el mapa
        tileM.draw(g2);
        //Imprimir objetos
        for (int i = 0; i < obj.length; i++)
            if(obj[i] !=null)
                obj[i].draw(g2,this);
        //Imprimir a Isaac
        isaac1.draw(g2);

        g2.dispose();
    }
}
