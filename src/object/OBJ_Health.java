package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Health extends Entity {

    GamePanel gp;

    public OBJ_Health(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_consumable;
        name = "Health potion";
        value = 5;
        down1 = setup("/res/objects/health", gp.tileSize, gp.tileSize);
        descr = "[Health potion]\nRestores your health \nby " + value + ".";
    }

    public void use(Entity entity) {

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the " + name + "!\n" +
                "Your health was restored by " + value + ".";
        entity.health += value;

        gp.playSE(2);
    }
}
