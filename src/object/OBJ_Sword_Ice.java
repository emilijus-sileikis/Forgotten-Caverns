package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Ice extends Entity {

    public OBJ_Sword_Ice(GamePanel gp) {
        super(gp);

        name = "Ice Sword";
        down1 = setup("/res/objects/sword_ice",gp.tileSize,gp.tileSize);
        attackVal = 2;
        descr = "[" + name + "]\nAn ice infused \nsword.";
    }
}
