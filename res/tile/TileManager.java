package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[20];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/map1.txt");
    }

    public void getTileImage(){

            setup(0,"img_3",false);
            setup(1,"img_1",true);
            setup(2,"img_2",true);
            setup(3,"esquinaArribaIzquierda",true);
            setup(4,"img",false);
            setup(5,"img_4",false);
            setup(6,"img_5",false);
            setup(7,"img6",true);
            setup(8,"img7",true);
            setup(9,"img8",false);
            setup(10,"img9",false);
            setup(11,"img10",true);
            setup(12,"img11",false);
            setup(13,"img12",false);
            setup(14,"img13",false);
            setup(15,"img14",true);
            setup(16,"img15",true);

    }
    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName+".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap (String filePath){
        try{

            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                while (col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){

        }
    }

    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxWorldCol && row< gp.maxWorldRow){
            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image,x,y, null);
            col++;
            x+= gp.tileSize;

            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y+=gp.tileSize;
            }
        }
    }
}
