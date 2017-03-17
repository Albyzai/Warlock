/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.healthsystem;

import data.Entity;
import static data.EntityType.*;
import data.GameData;
import data.World;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import services.IGamePluginService;

@ServiceProvider(service = IGamePluginService.class)

public class HealthEntity implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {
        for (Entity e : world.getEntities(PLAYER)) {
            e.setHealth(100);
        }
        for (Entity e : world.getEntities(ENEMY)) {
            e.setHealth(100);
        }
    }

    @Override
    public void stop(GameData gameData, World world
    ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
