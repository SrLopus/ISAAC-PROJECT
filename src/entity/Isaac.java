package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Isaac extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Isaac(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        hitBox = new Rectangle();
        hitBox.x = 16;
        hitBox.y = 32;
        hitBox.width = 56;
        hitBox.height = 56;

        setDefaultValues ();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize*7;
        worldY = gp.tileSize*4;
        speed = 8;
        direction = "down";
    }
    public void getPlayerImage(){

        up1 = setup("arriba1");
        up2 = setup("arriba2");
        down1 = setup("abajo1");
        down2 = setup("abajo2");
        left1 = setup("izquierda1");
        left2 = setup("izquierda2");
        right1 = setup("derecha1");
        right2 = setup("derecha2");

    }
    public BufferedImage setup(String imageName){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/isaac/"+imageName+".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }
    public void update() {
        //En que dirección nos vamos a mover
        if(keyH.arriba||keyH.abajo||keyH.izquierda|| keyH.derecha){
            if (keyH.arriba) {
                direction = "up";

            } else if (keyH.abajo) {
                direction = "down";

            } else if (keyH.izquierda) {
                direction = "left";

            } else if (keyH.derecha){
                direction = "right";

            }
            //Checkear si hay colision
            collisionOn = false;
            gp.cChecker.checkTile(this);
            //Si hay no se puede mover
            if(collisionOn == false){
                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
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
        g2.drawImage(image,worldX,worldY,gp.tileSize,gp.tileSize,null);
    }
}
