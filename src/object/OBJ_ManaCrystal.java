package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ManaCrystal extends Entity {

    GamePanel gp;

    public OBJ_ManaCrystal(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Mana Crystal";
        image = setup("/res/objects/mana_full", gp.tileSize, gp.tileSize);
        image2 = setup("/res/objects/mana_empty", gp.tileSize, gp.tileSize);
    }
}