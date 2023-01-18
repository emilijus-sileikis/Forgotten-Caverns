package main;

import entity.Entity;
import object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font joystix;
    BufferedImage heart_full, heart_half, heart_empty;
    public boolean messageOn = false;
    public String message = "";
    int messageCOunter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;

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
    }

    public void showMessage (String text) {

        message = text;
        messageOn = true;
    }

    public void draw (Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(joystix);
        g2.setColor(Color.WHITE);

        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        if (gp.gameState == gp.playState) {
            //TODO play stuff
            drawPlayerHealth();
        }

        if (gp.gameState == gp.pauseState) {
            drawPlayerHealth();
            drawPauseScreen();
        }

        if (gp.gameState == gp.dialogueState) {
            drawPlayerHealth();
            drawDialogueScreen();
        }
    }

    public void drawPlayerHealth () {

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        // Empty hearts
        while (i < gp.player.maxHealth/2) {
            g2.drawImage(heart_empty, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // Reset
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        // Paint current hearts
        while (i < gp.player.health) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.health) { g2.drawImage(heart_full, x, y, null); }
            i++;
            x += gp.tileSize;
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
}
