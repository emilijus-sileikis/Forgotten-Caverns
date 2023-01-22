package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_Girl extends Entity {

    public NPC_Girl(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage () {

        up1 = setup("/res/npc/girl_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/npc/girl_up_2", gp.tileSize, gp.tileSize);
        up3 = setup("/res/npc/girl_up_3", gp.tileSize, gp.tileSize);
        down1 = setup("/res/npc/girl_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/npc/girl_down_2", gp.tileSize, gp.tileSize);
        down3 = setup("/res/npc/girl_down_3", gp.tileSize, gp.tileSize);
        left1 = setup("/res/npc/girl_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/npc/girl_left_2", gp.tileSize, gp.tileSize);
        left3 = setup("/res/npc/girl_left_3", gp.tileSize, gp.tileSize);
        right1 = setup("/res/npc/girl_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/npc/girl_right_2", gp.tileSize, gp.tileSize);
        right3 = setup("/res/npc/girl_right_3", gp.tileSize, gp.tileSize);
    }

    public void setDialogue () {

        dialogues[0] = "Hello, stranger.";
        dialogues[1] = "I am in need of your help.";
        dialogues[2] = "Some random text that sets \na quest...";
        dialogues[3] = "Are you up for the task?";
    }

    public void setAction () {

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
    }

    public void speak () {

        //TODO Do character specific stuff

        super.speak();
    }
}
