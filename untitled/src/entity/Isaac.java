package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Isaac extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Isaac(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues ();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 8;
        direction = "down";
    }
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/isaac/arriba1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/isaac/arriba2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/isaac/abajo1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/isaac/abajo2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/isaac/izquierda1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/isaac/izquierda2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/isaac/derecha1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/isaac/derecha2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update() {

        if(keyH.arriba||keyH.abajo||keyH.izquierda|| keyH.derecha){
            if (keyH.arriba) {
                direction = "up";
                y -= speed;
            } else if (keyH.abajo) {
                direction = "down";
                y += speed;
            } else if (keyH.izquierda) {
                direction = "left";
                x -= speed;
            } else if (keyH.derecha){
                direction = "right";
                x += speed;
            }
            spriteCounter++;
            if (spriteCounter>12){
                if (spriteNum==1)
                    spriteNum = 2;
                else if (spriteNum==2)
                    spriteNum = 1;
                spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //Ubicamos el personaje en la pantalla
        //g2.fillRect(x,y,gp.tileSize,gp.tileSize);
        BufferedImage image = null;

        switch (direction){
            case "up":
                if(spriteNum==1){
                    image=  up1;
                }
                if(spriteNum==2){
                    image=  up2;
                }
                break;
            case "down":
                if(spriteNum==1){
                    image=  down1;
                }
                if(spriteNum==2){
                    image=  down2;
                }
                break;
            case "left":
                if(spriteNum==1){
                    image=  left1;
                }
                if(spriteNum==2){
                    image=  left2;
                }
                break;
            case "right":
                if(spriteNum==1){
                    image=  right1;
                }
                if(spriteNum==2){
                    image=  right2;
                }
                break;
        }
        g2.drawImage(image,x,y,gp.tileSize, gp.tileSize,null);
    }
}
