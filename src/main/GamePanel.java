package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;
import tile_interactive.InteractiveTile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {

    // Screen settings
    final int originalTileSize = 16; //16x16
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxSceenCol = 16;
    public final int maxScreenRow = 12;
    public int screenWidth = tileSize * maxSceenCol;
    public int screenHeight = tileSize * maxScreenRow;

    // WORLD
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;

    // System
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    // Entity and objects
    public Player player = new Player(this, keyH);
    public Entity[] obj = new Entity[20];
    public Entity[] npc = new Entity[10];
    public Entity[] monster = new Entity[20];
    public InteractiveTile[] iTile = new InteractiveTile[50];
    public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();


    // Game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame () {

        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();

        gameState = titleState;

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {

                update();
                repaint();
                delta--;
            }
        }

    }

    public void update() {

        if (gameState == playState) {
            player.update();

            // For NPC
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.update();
                }
            }
            // For MONSTER
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    if (monster[i].alive && !monster[i].dying) {
                        monster[i].update();
                    }
                    if (!monster[i].alive) {
                        monster[i].checkDrop();
                        monster[i] = null;
                    }
                }
            }
            // Projectile
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    if (projectileList.get(i).alive) {
                        projectileList.get(i).update();
                    }
                    if (!projectileList.get(i).alive) {
                        projectileList.remove(i);
                    }
                }
            }
            // Particles
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null) {
                    if (particleList.get(i).alive) {
                        particleList.get(i).update();
                    }
                    if (!particleList.get(i).alive) {
                        particleList.remove(i);
                    }
                }
            }
            for (InteractiveTile interactiveTile : iTile) {
                if (interactiveTile != null) {
                    interactiveTile.update();
                }
            }
        }

        if (gameState == pauseState) {
            // nothing
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (gameState == titleState) {
            ui.draw(g2);
        }
        else {

            tileM.draw(g2);

            // InteractivSe tile
            for (InteractiveTile interactiveTile : iTile) {
                if (interactiveTile != null) {
                    interactiveTile.draw(g2);
                }
            }

            // Add player
            entityList.add(player);

            //Add entities
            for (Entity entity : npc) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }

            for (Entity entity : obj) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }

            for (Entity value : monster) {
                if (value != null) {
                    entityList.add(value);
                }
            }

            for (Entity value : projectileList) {
                if (value != null) {
                    entityList.add(value);
                }
            }

            for (Entity value : particleList) {
                if (value != null) {
                    entityList.add(value);
                }
            }

            // Sort
            entityList.sort(new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    return Integer.compare(e1.worldY, e2.worldY);
                }
            });

            // Draw entities
            for (Entity entity : entityList) { entity.draw(g2); }

            // Empty the list
            entityList.clear();

            ui.draw(g2);
        }

        g2.dispose();
    }

    public void playMusic (int i) {

        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic () {

        music.stop();
    }

    public void playSE (int i) {

        se.setFile(i);
        se.play();
    }
}
