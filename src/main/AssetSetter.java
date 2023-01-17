package main;

import entity.NPC_Girl;
import monster.MON_GreenSlime;
import object.OBJ_Chest;
import object.OBJ_Gate;
import object.OBJ_Key;
import object.OBJ_Speed;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter (GamePanel gp) {
        this.gp = gp;
    }

    public void setObject () {
//        gp.obj[0] = new OBJ_Key(gp);
//        gp.obj[0].worldX = 23 * gp.tileSize;
//        gp.obj[0].worldY = 7 * gp.tileSize;
//
//        gp.obj[1] = new OBJ_Key(gp);
//        gp.obj[1].worldX = 23 * gp.tileSize;
//        gp.obj[1].worldY = 40 * gp.tileSize;
//
//        gp.obj[2] = new OBJ_Key(gp);
//        gp.obj[2].worldX = 38 * gp.tileSize;
//        gp.obj[2].worldY = 8 * gp.tileSize;
//
//        gp.obj[3] = new OBJ_Gate(gp);
//        gp.obj[3].worldX = 10 * gp.tileSize;
//        gp.obj[3].worldY = 12 * gp.tileSize;
//
//        gp.obj[4] = new OBJ_Gate(gp);
//        gp.obj[4].worldX = 8 * gp.tileSize;
//        gp.obj[4].worldY = 28 * gp.tileSize;
//
//        gp.obj[5] = new OBJ_Gate(gp);
//        gp.obj[5].worldX = 12 * gp.tileSize;
//        gp.obj[5].worldY = 23 * gp.tileSize;
//
//        gp.obj[6] = new OBJ_Chest(gp);
//        gp.obj[6].worldX = 10 * gp.tileSize;
//        gp.obj[6].worldY = 8 * gp.tileSize;
//
//        gp.obj[7] = new OBJ_Speed(gp);
//        gp.obj[7].worldX = 37 * gp.tileSize;
//        gp.obj[7].worldY = 42 * gp.tileSize;
    }

    public void setNPC () {

        gp.npc[0] = new NPC_Girl(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;
    }

    public void setMonster() {

        gp.monster[0] = new MON_GreenSlime(gp);
        gp.monster[0].worldX = gp.tileSize * 23;
        gp.monster[0].worldY = gp.tileSize * 36;

        gp.monster[1] = new MON_GreenSlime(gp);
        gp.monster[1].worldX = gp.tileSize * 23;
        gp.monster[1].worldY = gp.tileSize * 37;
    }
}
