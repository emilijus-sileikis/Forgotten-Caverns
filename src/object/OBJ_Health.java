package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Health extends Entity {

    GamePanel gp;
    int val = 5;

    public OBJ_Health(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_consumable;
        name = "Health potion";
        down1 = setup("/res/objects/health", gp.tileSize, gp.tileSize);
        descr = "[Health potion]\nRestores your health \nby " + val + ".";
    }

    public void use(Entity entity) {

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the " + name + "!\n" +
                "Your health was restored by " + val + ".";
        entity.health += val;

        if (gp.player.health > gp.player.maxHealth) { gp.player.health = gp.player.maxHealth; }

        gp.playSE(2);
    }
}
