package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, spacePressed, shotPressed;

    public KeyHandler (GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Title state
        if (gp.gameState == gp.titleState) { titleState(code); }

        // Play state
       else if (gp.gameState == gp.playState) { playState(code); }

        // Pause state
        else if (gp.gameState == gp.pauseState) { pauseState(code); }

        // Dialogue state
       else if (gp.gameState == gp.dialogueState) { dialogueState(code); }

        // Character state
        else if (gp.gameState == gp.characterState) { characterState(code); }

        // Option state
        else if (gp.gameState == gp.optionsState) { optionState(code); }
    }

    public void titleState(int code) {

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) { gp.ui.commandNum = 2; }
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 2) { gp.ui.commandNum = 0; }
        }
        if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
            if (gp.ui.commandNum == 0) {gp.gameState = gp.playState; gp.playMusic(0);}
            if (gp.ui.commandNum == 1) {//TODO later
            }
            if (gp.ui.commandNum == 2) {System.exit(0);}
        }
    }

    public void playState(int code) {

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) { upPressed = true; }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) { downPressed = true; }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) { leftPressed = true; }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) { rightPressed = true; }
        if(code == KeyEvent.VK_P) { gp.gameState = gp.pauseState; }
        if(code == KeyEvent.VK_C) { gp.gameState = gp.characterState; }
        if(code == KeyEvent.VK_ENTER) { enterPressed = true; }
        if(code == KeyEvent.VK_SPACE) { spacePressed = true; }
        if(code == KeyEvent.VK_F) { shotPressed = true; }
        if(code == KeyEvent.VK_ESCAPE) { gp.gameState = gp.optionsState; }
    }

    public void pauseState(int code) {

        if (code == KeyEvent.VK_P) { gp.gameState = gp.playState; }
    }

    public void dialogueState(int code) {

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) { gp.gameState = gp.playState; }
    }

    public void characterState(int code) {

        if (code == KeyEvent.VK_C) { gp.gameState = gp.playState; }

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            if (gp.ui.slotRow != 0) {
                gp.ui.slotRow--;
                gp.playSE(8);
            }
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            if (gp.ui.slotCol != 0) {
                gp.ui.slotCol--;
                gp.playSE(8);
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            if (gp.ui.slotRow != 3) {
                gp.ui.slotRow++;
                gp.playSE(8);
            }
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if (gp.ui.slotCol != 4) {
                gp.ui.slotCol++;
                gp.playSE(8);
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            gp.player.selectItem();
        }
    }

    private void optionState(int code) {

        if (code == KeyEvent.VK_ESCAPE) { gp.gameState = gp.playState; }
        if (code == KeyEvent.VK_ENTER) { enterPressed= true; }

        int maxCommandNum = 0;
        switch (gp.ui.subState) {
            case 0: maxCommandNum = 5; break;
            case 3: maxCommandNum = 1; break;
        }

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            gp.playSE(8);
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            gp.playSE(8);
            if (gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(8);
                }
                if (gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
                    gp.se.volumeScale--;
                    gp.playSE(8);
                }
            }
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(8);
                }
                if (gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
                    gp.se.volumeScale++;
                    gp.playSE(8);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) { upPressed = false; }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) { downPressed = false; }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) { leftPressed = false; }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) { rightPressed = false; }
        if(code == KeyEvent.VK_SPACE) { spacePressed = false; }
        if(code == KeyEvent.VK_F) { shotPressed = false; }
    }
}
