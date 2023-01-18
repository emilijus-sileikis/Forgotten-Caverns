package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int standCount = 0;

    public Player (GamePanel gp, KeyHandler keyH) {

        super (gp);

        this. keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8; //8
        solidArea.y = 16; //16
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32; //32
        solidArea.height = 32; //32

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues () {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 3;
        direction = "down";

        // Player status
        maxHealth = 6;
        health = maxHealth;
    }

    public void getPlayerImage () {

        up1 = setup("/res/player/boy_up_1");
        up2 = setup("/res/player/boy_up_2");
        up3 = setup("/res/player/boy_up_3");
        down1 = setup("/res/player/boy_down_1");
        down2 = setup("/res/player/boy_down_2");
        down3 = setup("/res/player/boy_down_3");
        left1 = setup("/res/player/boy_left_1");
        left2 = setup("/res/player/boy_left_2");
        left3 = setup("/res/player/boy_left_3");
        right1 = setup("/res/player/boy_right_1");
        right2 = setup("/res/player/boy_right_2");
        right3 = setup("/res/player/boy_right_3");
    }

    public void update () {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {

            if (keyH.upPressed) { direction = "up"; }
            else if (keyH.downPressed) { direction = "down"; }
            else if (keyH.leftPressed) { direction = "left"; }
            else if (keyH.rightPressed) { direction = "right"; }

            // Check collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // Check obj collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // NPC collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // Monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // Event
            gp.eHandler.checkEvent();

            if (!collisionOn && !keyH.enterPressed) {

                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }

            }

            gp.keyH.enterPressed = false;

            spriteCounter++;

            if (spriteCounter > 15) {
                if (spriteNum == 1) { spriteNum = 2; }
                else if (spriteNum == 2) { spriteNum = 1; }
                else if (spriteNum == 3) { spriteNum = 2; }
                spriteCounter = 0;
            }
        }
        else {

            standCount++;

            if (standCount == 20) {
                spriteNum = 3;
                standCount = 0;
            }
        }

        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 120) { //120 = 2s, default 60
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void pickUpObject (int i) {}

    public void interactNPC (int i) {

        if (i != 999) {

            if (gp.keyH.enterPressed || gp.keyH.spacePressed) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
        gp.keyH.spacePressed = false;
    }

    public void contactMonster(int i) {

        if (i != 999) {

            if (!invincible) {
                health -= 1;
                invincible = true;
            }
        }
    }

    public void draw (Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) { image = up1; }
                if (spriteNum == 2) { image = up2; }
                if (spriteNum == 3) { image = up3; }
            }
            case "down" -> {
                if (spriteNum == 1) { image = down1; }
                if (spriteNum == 2) { image = down2; }
                if (spriteNum == 3) { image = down3; }
            }
            case "left" -> {
                if (spriteNum == 1) { image = left1; }
                if (spriteNum == 2) { image = left2; }
                if (spriteNum == 3) { image = left3; }
            }
            case "right" -> {
                if (spriteNum == 1) { image = right1; }
                if (spriteNum == 2) { image = right2; }
                if (spriteNum == 3) { image = right3; }
            }
        }

        int x = screenX;
        int y = screenY;

        if (screenX > worldX) {x = worldX;}
        if (screenY > worldY) {y = worldY;}

        int rightOffset = gp.screenWidth - screenX;
        if (rightOffset > gp.worldWidth - worldX) {x = gp.screenWidth - (gp.worldWidth - worldX);}

        int bottomOffset = gp.screenHeight - screenY;
        if (bottomOffset > gp.worldHeight - worldY) {y = gp.screenHeight - (gp.worldHeight - worldY);}

        // Set transparent while invincible
        if (invincible) { g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); }

        g2.drawImage(image, x, y, null);

        // Reset opacity
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
