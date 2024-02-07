package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends SuperObject {
    public OBJ_Chest(){
        name = "Chest";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/New Piskel (5).png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
