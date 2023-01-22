package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Wood extends Entity {

    public OBJ_Shield_Wood(GamePanel gp) {
        super(gp);

        name = "Wooden Shield";
        down1 = setup("/res/objects/shield_wood",gp.tileSize*2, gp.tileSize*2);
        defenceVal = 1;
    }
}
