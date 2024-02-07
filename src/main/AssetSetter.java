package main;

import object.OBJ_Chest;
import object.OBJ_Heart;
import object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject (){
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 4*gp.tileSize;
        gp.obj[0].worldY = 6*gp.tileSize;

        gp.obj[1] = new OBJ_Heart();
        gp.obj[1].worldX = 5*gp.tileSize;
        gp.obj[1].worldY = 6*gp.tileSize;

        gp.obj[2] = new OBJ_Chest();
        gp.obj[2].worldX = 3*gp.tileSize;
        gp.obj[2].worldY = 6*gp.tileSize;
    }
}
