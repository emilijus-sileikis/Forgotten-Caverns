package main;

import entity.Entity;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font joystix;
    BufferedImage heart_full, heart_half, heart_empty, mana_full, mana_empty;
    public boolean messageOn = false;
    ArrayList <String> message = new ArrayList<>();
    ArrayList <Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int slotCol = 0;
    public int slotRow = 0;

    public UI (GamePanel gp) {
        this.gp = gp;

        InputStream is = getClass().getResourceAsStream("/res/font/joystix monospace.ttf");
        try {
            joystix = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        // HUD
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_empty = heart.image3;
        Entity mana = new OBJ_ManaCrystal(gp);
        mana_full =  mana.image;
        mana_empty =  mana.image2;
    }

    public void addMessage (String text) {
        message.add(text);
        messageCounter.add(0);
    }

    public void draw (Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(joystix);
        g2.setColor(Color.WHITE);

        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        if (gp.gameState == gp.playState) {
            drawPlayerHealth();
            drawMessage();
        }

        if (gp.gameState == gp.pauseState) {
            drawPlayerHealth();
            drawPauseScreen();
        }

        if (gp.gameState == gp.dialogueState) {
            drawPlayerHealth();
            drawDialogueScreen();
        }

        if (gp.gameState == gp.characterState) {
            drawCharacterScreen();
            drawInventory();
        }
    }

    private void drawInventory() {

        // frame
        int frameX = gp.tileSize*9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*6;
        int frameHeight = gp.tileSize*5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // slot
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gp.tileSize+3;

        // draw player items
        for (int i = 0; i < gp.player.inventory.size(); i++) {

            if (gp.player.inventory.get(i) == gp.player.currentWeapon ||
                gp.player.inventory.get(i) == gp.player.currentShield) {
                g2.setColor(Color.LIGHT_GRAY);
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }

            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);

            slotX += slotSize;

            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXStart;
                slotY += slotSize;
            }
        }

        // cursor
        int cursorX = slotXStart + (slotSize * slotCol);
        int cursorY = slotYStart + (slotSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        // draw cursor
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10,10);

        // Descr frame
        int dFrameY = frameY + frameHeight;
        int dFrameHeight = gp.tileSize*3;

        // Descr text
        int textX = frameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(15F));

        int itemIndex = getItemIndexOnSlot();

        if (itemIndex < gp.player.inventory.size()) {

            drawSubWindow(frameX, dFrameY, frameWidth, dFrameHeight);

            for (String line: gp.player.inventory.get(itemIndex).descr.split("\n")) {
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }

    }

    public int getItemIndexOnSlot() {
        return slotCol + (slotRow*5);
    }

    public void drawMessage() {

        int messageX = gp.tileSize;
        int messageY = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25F));

        for (int i = 0; i < message.size(); i++) {

            if (message.get(i) != null) {

                g2.setColor(Color.BLACK);
                g2.drawString(message.get(i), messageX+2, messageY+2);
                g2.setColor(Color.WHITE);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                messageY += 50;

                if (messageCounter.get(i) > 200) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    public void drawPlayerHealth () {

        int x = gp.tileSize/4; //gp.tileSize/2
        int y = gp.tileSize/6; //gp.tileSize/2
        int i = 0;

        // Empty hearts
        while (i < gp.player.maxHealth/2) {
            g2.drawImage(heart_empty, x, y, null);
            i++;
            x += 40; //gp.tileSize
        }

        // Reset
        x = gp.tileSize/4; //gp.tileSize/2
        y = gp.tileSize/6; //gp.tileSize/2
        i = 0;

        // Paint current hearts
        while (i < gp.player.health) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.health) { g2.drawImage(heart_full, x, y, null); }
            i++;
            x += 40;
        }

        // Draw max mana
        x = (gp.tileSize/2);
        y = gp.tileSize + 10;
        i = 0;
         while (i < gp.player.maxMana) {
             g2.drawImage(mana_empty, x, y, null);
             i++;
             x += 25;
         }

         // Draw mana
        x = (gp.tileSize/2);
        y = gp.tileSize + 10;
        i = 0;
        while (i < gp.player.mana) {
            g2.drawImage(mana_full, x, y, null);
            i++;
            x += 25;
        }

    }

    public void drawTitleScreen () {

        // BG color
        g2.setColor(new Color(10,10,80));
        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);

        // Title name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
        String text = "Forgotten";
        String text2 = "Caverns";
        int x = getXforCenteredText(text);
        int y = gp.tileSize*3;

        // Shadow effect for text
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+8, y-18);
        g2.drawString(text2, x+60, y+60);

        // Title color
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y-30);
        g2.drawString(text2, x+50, y+50);

        // Logo
        x = gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize*2;
        //TODO po to pakeist i game logo!!!!
        g2.drawImage(gp.player.down3, x, y, gp.tileSize*2, gp.tileSize*2, null);

        // Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize*3.5;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            //TODO padaryt kazka graziau nei >
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            //TODO padaryt kazka graziau nei >
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            //TODO padaryt kazka graziau nei >
            g2.drawString(">", x-gp.tileSize, y);
        }
    }

    public void drawPauseScreen () {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "GAME PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen () {

        // Window
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawCharacterScreen() {

        // Create frame
        final int frameX = gp.tileSize-40;
        final int frameY = gp.tileSize-40;
        final int frameWidth = gp.tileSize*6;
        final int frameHeight = gp.tileSize*10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(20F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;

        // Names
        g2.drawString("LVL", textX, textY);
        textY += lineHeight;
        g2.drawString("HP", textX, textY);
        textY += lineHeight;
        g2.drawString("MP", textX, textY);
        textY += lineHeight;
        g2.drawString("STR", textX, textY);
        textY += lineHeight;
        g2.drawString("DEX", textX, textY);
        textY += lineHeight;
        g2.drawString("ATT", textX, textY);
        textY += lineHeight;
        g2.drawString("DEF", textX, textY);
        textY += lineHeight;
        g2.drawString("EXP", textX, textY);
        textY += lineHeight;
        g2.drawString("Next LVL", textX, textY);
        textY += lineHeight;
        g2.drawString("Gold", textX, textY);
        textY += lineHeight + 10;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 10;
        g2.drawString("Shield", textX, textY);

        // Vals
        int tailX = (frameX + frameWidth) - 30;
        // Reset textY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.health + "/" + gp.player.maxHealth);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defence);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - (gp.tileSize-10), textY-20, null);
        textY += gp.tileSize;

        g2.drawImage(gp.player.currentShield.down1, tailX - (gp.tileSize-10), textY-20, null);
    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public int getXforCenteredText (String text) {

        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;

        return x;
    }

    public int getXForAlignToRightText (String text, int tailX) {

        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;

        return x;
    }
}
