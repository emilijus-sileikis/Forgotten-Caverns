package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class IT_D_Tree extends InteractiveTile{

    GamePanel gp;

    public IT_D_Tree(GamePanel gp, int col, int row) {
        super(gp,col,row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/res/tiles_interactive/d_tree", gp.tileSize, gp.tileSize);
        destructible = true;
        health = 5;
    }

    public boolean isCorrectItem(Entity entity) {
        boolean isCorrectItem = false;

        if (entity.currentWeapon.type == type_axe) {
            isCorrectItem = true;
        }

        return isCorrectItem;
    }

    public void playSE() {
//        gp.playSE(10);
    }

    public InteractiveTile getDestroyedForm() {
        return new IT_Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
    }
}
