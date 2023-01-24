package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Ice extends Entity {

    public OBJ_Shield_Ice(GamePanel gp) {
        super(gp);

        name = "Ice Shield";
        down1 = setup("/res/objects/shield_ice",gp.tileSize, gp.tileSize);
        defenceVal = 2;
        descr = "[" + name + "]\nIce infused \nshield.";
    }
}
