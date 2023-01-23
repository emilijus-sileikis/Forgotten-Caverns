package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);

        name = "Normal Sword";
        down1 = setup("/res/objects/sword_normal",gp.tileSize,gp.tileSize);
        attackVal = 1;
        descr = "[" + name + "]\nAn old ordinary \nsword.";
    }
}
