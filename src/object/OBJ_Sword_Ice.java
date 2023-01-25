package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Ice extends Entity {

    public OBJ_Sword_Ice(GamePanel gp) {
        super(gp);

        type = type_sword;
        name = "Ice Sword";
        down1 = setup("/res/objects/sword_ice",gp.tileSize,gp.tileSize);
        attackVal = 2;
        attackArea.width = 40;
        attackArea.height = 40;
        descr = "[" + name + "]\nAn ice infused \nsword.";
    }
}
