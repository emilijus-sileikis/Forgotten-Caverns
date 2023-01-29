package entity;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Fireball;
import object.OBJ_Key;
import object.OBJ_Rock;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int standCount = 0;
    public boolean attackCanceled = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInvSize = 20;

    public Player (GamePanel gp, KeyHandler keyH) {

        super (gp);

        this. keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 32; //8
        solidArea.y = 40; //16
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32; //32
        solidArea.height = 32; //32

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }

    public void setDefaultValues () {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 3;
        direction = "down";

        // Player status
        level = 1;
        maxHealth = 6;
        health = maxHealth;
        maxMana = 4;
        mana = maxMana;
        ammo = 10;
        strength = 1; // More str = more DMG
        dexterity = 1; // More dex = less DMG taken
        exp = 0;
        nextLevelExp = 10;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        projectile = new OBJ_Fireball(gp);
//        projectile = new OBJ_Rock(gp);
        attack = getAttack(); // Total attack = str + weapon
        defence = getDefence(); // Total def = dex + shield
    }

    public void setItems() {

        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));
    }

    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackVal;
    }

    public int getDefence() {
        return  defence = dexterity * currentShield.defenceVal;
    }

    public void getPlayerImage () {

        up1 = setup("/res/player/boy_up_1", gp.tileSize*2, gp.tileSize*2);
        up2 = setup("/res/player/boy_up_2", gp.tileSize*2, gp.tileSize*2);
        up3 = setup("/res/player/boy_up_3", gp.tileSize*2, gp.tileSize*2);
        down1 = setup("/res/player/boy_down_1", gp.tileSize*2, gp.tileSize*2);
        down2 = setup("/res/player/boy_down_2", gp.tileSize*2, gp.tileSize*2);
        down3 = setup("/res/player/boy_down_3", gp.tileSize*2, gp.tileSize*2);
        left1 = setup("/res/player/boy_left_1", gp.tileSize*2, gp.tileSize*2);
        left2 = setup("/res/player/boy_left_2", gp.tileSize*2, gp.tileSize*2);
        left3 = setup("/res/player/boy_left_3", gp.tileSize*2, gp.tileSize*2);
        right1 = setup("/res/player/boy_right_1", gp.tileSize*2, gp.tileSize*2);
        right2 = setup("/res/player/boy_right_2", gp.tileSize*2, gp.tileSize*2);
        right3 = setup("/res/player/boy_right_3", gp.tileSize*2, gp.tileSize*2);
    }

    public void getPlayerAttackImage() {

        if (currentWeapon.type == type_sword) {
            if (currentWeapon.name.equals("Ice Sword")) {
                attUp1 = setup("/res/player/boy_attack_up_1_ice", gp.tileSize*2, gp.tileSize*2);
                attUp2 = setup("/res/player/boy_attack_up_2_ice", gp.tileSize*2, gp.tileSize*2);
                attDown1 = setup("/res/player/boy_attack_down_1_ice", gp.tileSize*2, gp.tileSize*2);
                attDown2 = setup("/res/player/boy_attack_down_2_ice", gp.tileSize*2, gp.tileSize*2);
                attLeft1 = setup("/res/player/boy_attack_left_1_ice", gp.tileSize*2, gp.tileSize*2);
                attLeft2 = setup("/res/player/boy_attack_left_2_ice", gp.tileSize*2, gp.tileSize*2);
                attRight1 = setup("/res/player/boy_attack_right_1_ice", gp.tileSize*2, gp.tileSize*2);
                attRight2 = setup("/res/player/boy_attack_right_2_ice", gp.tileSize*2, gp.tileSize*2);
            }
            if (currentWeapon.name.equals("Normal Sword")) {
                attUp1 = setup("/res/player/boy_attack_up_1", gp.tileSize*2, gp.tileSize*2);
                attUp2 = setup("/res/player/boy_attack_up_2", gp.tileSize*2, gp.tileSize*2);
                attDown1 = setup("/res/player/boy_attack_down_1", gp.tileSize*2, gp.tileSize*2);
                attDown2 = setup("/res/player/boy_attack_down_2", gp.tileSize*2, gp.tileSize*2);
                attLeft1 = setup("/res/player/boy_attack_left_1", gp.tileSize*2, gp.tileSize*2);
                attLeft2 = setup("/res/player/boy_attack_left_2", gp.tileSize*2, gp.tileSize*2);
                attRight1 = setup("/res/player/boy_attack_right_1", gp.tileSize*2, gp.tileSize*2);
                attRight2 = setup("/res/player/boy_attack_right_2", gp.tileSize*2, gp.tileSize*2);
            }
        }
        if (currentWeapon.type == type_axe) {
            attUp1 = setup("/res/player/boy_axe_up_1", gp.tileSize*2, gp.tileSize*2);
            attUp2 = setup("/res/player/boy_axe_up_2", gp.tileSize*2, gp.tileSize*2);
            attDown1 = setup("/res/player/boy_axe_down_1", gp.tileSize*2, gp.tileSize*2);
            attDown2 = setup("/res/player/boy_axe_down_2", gp.tileSize*2, gp.tileSize*2);
            attLeft1 = setup("/res/player/boy_axe_left_1", gp.tileSize*2, gp.tileSize*2);
            attLeft2 = setup("/res/player/boy_axe_left_2", gp.tileSize*2, gp.tileSize*2);
            attRight1 = setup("/res/player/boy_axe_right_1", gp.tileSize*2, gp.tileSize*2);
            attRight2 = setup("/res/player/boy_axe_right_2", gp.tileSize*2, gp.tileSize*2);
        }
    }

    public void update () {

        if (attacking) {
            attacking();
        }
        else if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed || keyH.spacePressed) {

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

            if (!collisionOn && !keyH.enterPressed && !keyH.spacePressed) {

                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
                spriteCounter++;
            }

            gp.keyH.enterPressed = false;

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

        if (gp.keyH.shotPressed && !projectile.alive && shotAvailableCounter == 30 && projectile.haveResource(this)) {

            // Set projectile coords //TODO FIX COORDINATES!!!!! (Pakeist photo, kad butu 32X64, 64X32 arba kazka kito)
            projectile.set(worldX+25, worldY+30, direction, true, this);

            // Subtract mana
            projectile.subtractResource(this);

            // Add to list
            gp.projectileList.add(projectile);

            shotAvailableCounter = 0;

//            gp.playSE(9);
        }

        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 120) { //120 = 2s, default 60
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (shotAvailableCounter < 30) { shotAvailableCounter++; }
        if (health > maxHealth) { health = maxHealth; }
        if (mana > maxMana) { mana = maxMana; }
    }

    private void attacking() {

        spriteCounter++;

        if (spriteCounter <= 7) { //5
            spriteNum = 1;
        }
        if (spriteCounter > 7 && spriteCounter <= 25) {
            spriteNum = 2;

            // get current areas
            int currentWorldX= worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust players area to attackArea
            switch (direction) {
                case "up" -> worldY -= attackArea.height;
                case "down" -> worldY += attackArea.height;
                case "left" -> worldX -= attackArea.width;
                case "right" -> worldX += attackArea.width;
            }

            // assign attack area to solid
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            // check collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack);

            // restore original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject (int i) {

        if (i != 999) {

            // PickupOnly items
            if (gp.obj[i].type == type_pickupOnly) {

                gp.obj[i].use(this);
                gp.obj[i] = null;
            }

            // INV items
            else {
                String text;

                if (inventory.size() != maxInvSize) {
                    inventory.add(gp.obj[i]);
                    gp.playSE(1);
                    text = "You found " + gp.obj[i].name + "!";
                }
                else {
                    text = "Your inventory is full!";
                }
                gp.ui.addMessage(text);
                gp.obj[i] = null;
            }
        }
    }

    public void interactNPC (int i) {

        if (i != 999) {

            if (gp.keyH.enterPressed) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
        else {
            if (gp.keyH.enterPressed) {
                gp.playSE(6);
                attacking = true;
            }
        }
        gp.keyH.enterPressed = false;
    }

    public void contactMonster(int i) {

        if (i != 999) {

            if (!invincible && !gp.monster[i].dying) {
                gp.playSE(5);

                int damage = gp.monster[i].attack - defence;
                if (damage < 0) {
                    damage = 0;
                }

                health -= damage;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i, int attack) {

        if (i != 999) {

            if (!gp.monster[i].invincible) {

                gp.playSE(5);

                int damage = attack - gp.monster[i].defence;
                if (damage < 0) {
                    damage = 0;
                }

                gp.monster[i].health -= damage;
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if (gp.monster[i].health <= 0) {
                    gp.monster[i].dying = true;
                    exp += gp.monster[i].exp;
                    gp.ui.addMessage("+" + gp.monster[i].exp + " EXP");
                    checkLevelUp();
                }
            }
        }
    }

    public void checkLevelUp() {

        if (exp >= nextLevelExp) {
            level++;
            nextLevelExp = nextLevelExp*2;
            maxHealth += 2;
            maxMana += 1;
            strength++;
            dexterity++;
            attack = getAttack();
            defence = getDefence();

            gp.playSE(7);
            gp.ui.addMessage("You have reached level: " + level + "!");
            //gp.gameState = gp.dialogueState;
            //gp.ui.currentDialogue = "You have reached level: " + level + "!";
        }
    }

    public void selectItem () {

        int itemIndex = gp.ui.getItemIndexOnSlot();

        if (itemIndex < inventory.size()) {

            Entity selectedItem = inventory.get(itemIndex);

            if (selectedItem.type == type_sword || selectedItem.type == type_axe) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if (selectedItem.type == type_shield) {
                currentShield = selectedItem;
                defence = getDefence();
            }
            if (selectedItem.type == type_consumable) {
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
    }

    public void draw (Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (!attacking) {
                    if (spriteNum == 1) { image = up1; }
                    if (spriteNum == 2) { image = up2; }
                    if (spriteNum == 3) { image = up3; }
                }
                if (attacking) {
                    if (spriteNum == 1) { image = attUp1; }
                    if (spriteNum == 2) { image = attUp2; }
                }
            }
            case "down" -> {
                if (!attacking) {
                    if (spriteNum == 1) { image = down1; }
                    if (spriteNum == 2) { image = down2; }
                    if (spriteNum == 3) { image = down3; }
                }
                if (attacking) {
                    if (spriteNum == 1) { image = attDown1; }
                    if (spriteNum == 2) { image = attDown2; }
                }
            }
            case "left" -> {
                if (!attacking) {
                    if (spriteNum == 1) { image = left1; }
                    if (spriteNum == 2) { image = left2; }
                    if (spriteNum == 3) { image = left3; }
                }
                if (attacking) {
                    if (spriteNum == 1) { image = attLeft1; }
                    if (spriteNum == 2) { image = attLeft2; }
                }
            }
            case "right" -> {
                if (!attacking) {
                    if (spriteNum == 1) { image = right1; }
                    if (spriteNum == 2) { image = right2; }
                    if (spriteNum == 3) { image = right3; }
                }
                if (attacking) {
                    if (spriteNum == 1) { image = attRight1; }
                    if (spriteNum == 2) { image = attRight2; }
                }
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
