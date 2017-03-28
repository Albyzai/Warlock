/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.spell;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import data.Entity;
import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IEntityProcessingService;
import services.IGamePluginService;
import data.EntityType;
import data.SpellType;

@ServiceProviders(value = {
    @ServiceProvider(service = IGamePluginService.class),
    @ServiceProvider(service = IEntityProcessingService.class)
})

public class SpellPlugin implements IGamePluginService, IEntityProcessingService {

    private SpellBook spellBook;
    private SpellArchive archive;

    @Override
    public void start(GameData gameData, World world) {
        for (Entity entity : world.getEntities(EntityType.PLAYER, EntityType.ENEMY)) {
            spellBook = new SpellBook(entity);
        }
        archive = new SpellArchive(world);

    }

    @Override
    public void process(GameData gameData, World world) {
        for(Entity entity : world.getEntities(EntityType.PLAYER)){
            
        }
        for (Entity spell : world.getEntities(EntityType.SPELL)) {
            float dt = gameData.getDelta();
            spell.reduceExpiration(dt);
            if (spell.getExpiration() <= 0) {
                world.removeEntity(spell);
                archive.getAnimator().getBatch().end();
            }
        }

    }

    public void unlockSpell(SpellType spellType) {
        spellBook.addToSpellBook(spellType);
    }

    public void useSpell(SpellType spellType, int x, int y) {
        archive.getAnimator().getBatch().draw((TextureRegion) spellBook.getSpell(spellType).getAnimation().getKeyFrame(archive.getAnimator().getStateTime()), x, y);
    }

    @Override
    public void stop(GameData gameData, World world) {
    }

}
