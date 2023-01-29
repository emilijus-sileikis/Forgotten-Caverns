package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Gold;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

import java.util.Random;

public class MON_GreenSlime extends Entity {

    GamePanel gp;

    public MON_GreenSlime(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Green Slime";
        speed = 1;
        maxHealth = 4;
        health = maxHealth;
        attack = 5;
        defence = 0;
        exp = 2;
        projectile = new OBJ_Rock(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void  getImage() {

        up1 = setup("/res/monster/green_slime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/monster/green_slime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/monster/green_slime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/monster/green_slime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/monster/green_slime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/monster/green_slime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/monster/green_slime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/monster/green_slime_down_2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(100)+1;

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <=100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }

        int i = new Random().nextInt(100)+1;
        if (i > 99 && !projectile.alive && shotAvailableCounter == 30) {
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }
    }

    public void damageReaction() {

        actionLockCounter = 0;
        direction = gp.player.direction;
    }

    public void checkDrop() {

        int i = new Random().nextInt(100)+1;

        if (i < 50) {
            dropItem(new OBJ_Coin_Gold(gp));
        }
        if (i >= 50 && i < 75) {
            dropItem(new OBJ_Heart(gp));
        }
        if (i >= 75 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp));
        }
    }
}
