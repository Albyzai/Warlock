/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.spell;

import com.badlogic.gdx.math.Vector2;
import data.Entity;
import data.GameData;
import data.World;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IEntityProcessingService;
import services.IGamePluginService;
import data.EntityType;

@ServiceProviders(value = {
    @ServiceProvider(service = IGamePluginService.class)
    ,
    @ServiceProvider(service = IEntityProcessingService.class)
})

public class SpellPlugin implements IGamePluginService, IEntityProcessingService {

    Entity spell;
    SpellBook spellBook;
    

    @Override
    public void start(GameData gameData, World world) {
        for(Entity entity : world.getEntities(EntityType.PLAYER, EntityType.ENEMY)){
            spellBook = new SpellBook(entity);         
        }

    }

    @Override
    public void process(GameData gameData, World world) {
        for(Entity entity : world.getEntities(EntityType.PLAYER, EntityType.ENEMY)){
            float dt = gameData.getDelta();
        }
            
        
    }
      

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(spell);
    }

}
