package object;

import entity.Entity;
import main.GamePanel;

//TODO REDRAW HEARTS

public class OBJ_Heart extends Entity {

    GamePanel gp;

    public OBJ_Heart (GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = "Heart";
        value = 2;
        down1 = setup("/res/objects/heart_full", gp.tileSize, gp.tileSize);
        image = setup("/res/objects/heart_full", (int) (gp.tileSize*1.5), (int) (gp.tileSize*1.5));
        image2 = setup("/res/objects/heart_half", (int) (gp.tileSize*1.5), (int) (gp.tileSize*1.5));
        image3 = setup("/res/objects/heart_empty", (int) (gp.tileSize*1.5), (int) (gp.tileSize*1.5));
    }

    public void use(Entity entity) {

        gp.playSE(2);
        gp.ui.addMessage("Health +" + value);
        entity.health += value;
    }
}
