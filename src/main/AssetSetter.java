package main;

import entity.NPC_Girl;
import monster.MON_GreenSlime;
import object.OBJ_Coin_Gold;
import object.OBJ_Health;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Axe;
import tile_interactive.IT_D_Tree;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter (GamePanel gp) {
        this.gp = gp;
    }

    public void setObject () {

        int i = 0;

        gp.obj[i] = new OBJ_Coin_Gold(gp);
        gp.obj[i].worldX = gp.tileSize*25;
        gp.obj[i].worldY = gp.tileSize*20;
        i++;

        gp.obj[i] = new OBJ_Heart(gp);
        gp.obj[i].worldX = gp.tileSize*21;
        gp.obj[i].worldY = gp.tileSize*22;
        i++;

        gp.obj[i] = new OBJ_ManaCrystal(gp);
        gp.obj[i].worldX = gp.tileSize*23;
        gp.obj[i].worldY = gp.tileSize*25;
        i++;

        gp.obj[i] = new OBJ_Health(gp);
        gp.obj[i].worldX = gp.tileSize*23;
        gp.obj[i].worldY = gp.tileSize*28;
        i++;

        gp.obj[i] = new OBJ_Axe(gp);
        gp.obj[i].worldX = gp.tileSize*23;
        gp.obj[i].worldY = gp.tileSize*30;
    }

    public void setNPC () {

        gp.npc[0] = new NPC_Girl(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;
    }

    public void setMonster() {

        int i = 0;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 23;
        gp.monster[i].worldY = gp.tileSize * 36;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 23;
        gp.monster[i].worldY = gp.tileSize * 37;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 23;
        gp.monster[i].worldY = gp.tileSize * 38;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 23;
        gp.monster[i].worldY = gp.tileSize * 39;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 23;
        gp.monster[i].worldY = gp.tileSize * 40;
    }

    public void setInteractiveTile() {
        int i = 0;
        gp.iTile[i] = new IT_D_Tree(gp,27,12);
        i++;
        gp.iTile[i] = new IT_D_Tree(gp,28,12);
        i++;
        gp.iTile[i] = new IT_D_Tree(gp,29,12);
        i++;
        gp.iTile[i] = new IT_D_Tree(gp,30,12);
        i++;
        gp.iTile[i] = new IT_D_Tree(gp,31,12);
    }
}
