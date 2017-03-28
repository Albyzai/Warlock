/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.levelsystem;

import data.Entity;
import data.EntityType;
import data.World;

/**
 *
 * @author mads1
 */
public class LevelSystem {

    private final int LEVEL2 = 50;
    private final int LEVEL3 = 120;
    private final int LEVEL4 = 250;
    private final int LEVEL5 = 400;
    private final int LEVEL6 = 600;
    private final int LEVEL7 = 860;
    private final int LEVEL8 = 1040;
    private final int LEVEL9 = 1400;
    private final int LEVEL10 = 2000;
    

    public LevelSystem(World world) {
        for (Entity entity : world.getEntities(EntityType.PLAYER, EntityType.ENEMY)) {
            entity.setLevel(1);
            entity.setExpPoints(0);
        }
    }

    public void gainLevel(Entity e) {
        e.setLevel(e.getLevel() + 1);
    }

    public void gainExp(Entity e, int exp) {
        e.setExpPoints(e.getExpPoints() + exp);
        
        if (e.getExpPoints() >= LEVEL2 && e.getExpPoints() < LEVEL3 && e.getLevel() != 2) {
            gainLevel(e);
        } else if (e.getExpPoints() >= LEVEL3 && e.getExpPoints() < LEVEL4 && e.getLevel() != 3) {
            gainLevel(e);
        } else if (e.getExpPoints() >= LEVEL4 && e.getExpPoints() < LEVEL5 && e.getLevel() != 4) {
            gainLevel(e);
        } else if (e.getExpPoints() >= LEVEL5 && e.getExpPoints() < LEVEL6 && e.getLevel() != 5) {
            gainLevel(e);
        } else if (e.getExpPoints() >= LEVEL6 && e.getExpPoints() < LEVEL7 && e.getLevel() != 6) {
            gainLevel(e);
        } else if (e.getExpPoints() >= LEVEL7 && e.getExpPoints() < LEVEL8 && e.getLevel() != 7) {
            gainLevel(e);
        } else if (e.getExpPoints() >= LEVEL8 && e.getExpPoints() < LEVEL9 && e.getLevel() != 8) {
            gainLevel(e);
        } else if (e.getExpPoints() >= LEVEL9 && e.getExpPoints() < LEVEL10 && e.getLevel() != 9) {
            gainLevel(e);
        }
    }

}
