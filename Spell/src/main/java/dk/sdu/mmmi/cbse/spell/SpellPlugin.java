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
import States.CharacterState;

@ServiceProviders(value = {
    @ServiceProvider(service = IGamePluginService.class),
    @ServiceProvider(service = IEntityProcessingService.class)
})

public class SpellPlugin implements IGamePluginService, IEntityProcessingService {

    private SpellBook spellBook;
    private SpellArchive archive;
    private World world;
    
    @Override
    public void start(GameData gameData, World world) {
        for (Entity entity : world.getEntities(EntityType.PLAYER)) {
            spellBook = new SpellBook(entity);
        }
        archive = new SpellArchive(world);

    }

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(EntityType.PLAYER, EntityType.ENEMY)) {
            if (entity.getCharState() == CharacterState.CASTING) {
                useSpell(world, entity.getChosenSpell(), entity.getX(), entity.getY(), entity);
                entity.setCharState(CharacterState.IDLE);
            }
        }
        for (Entity spell : world.getEntities(EntityType.SPELL)) {
            float dt = gameData.getDelta();
            spell.reduceExpiration(dt);
            if (spell.getExpiration() <= 0) {
                world.removeEntity(spell);
            }
        }
    }

    public void unlockSpell(World world, Entity owner, SpellType spellType) {
        spellBook.addToSpellBook(world, owner, spellType);
    }

    public void useSpell(World world, SpellType spellType, float x, float y, Entity caster) {
        for (Spell spell : spellBook.getSpellBook(world, caster)) {
            if (spell.getSpellType().equals(spellType)) {
                //archive.getAnimator().getBatch().draw((TextureRegion) spellBook.getSpell(spellType).getAnimation().getKeyFrame(archive.getAnimator().getStateTime()), x, y);
                spell.getSpellEntity().setType(EntityType.SPELL);
                spell.getSpellEntity().setPosition(caster.getX(), caster.getY());
                spell.getSpellEntity().setRadians(caster.getRadians());
                spell.getSpellEntity().setMaxSpeed(spellBook.getSpell(spellType).getSpeed());
                spell.getSpellEntity().setAcceleration(spellBook.getSpell(spellType).getAcceleration());
                return;
            }
        }
    }

    @Override
    public void stop() {
        for (Entity spell : world.getEntities(EntityType.SPELL)) {
            world.removeEntity(spell);
        }
    }

}
