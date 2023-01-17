package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Gate extends Entity {

    public OBJ_Gate (GamePanel gp) {

        super(gp);

        name = "Gate";
        down1 = setup("/res/objects/gate");
        collision = true;
    }
}
