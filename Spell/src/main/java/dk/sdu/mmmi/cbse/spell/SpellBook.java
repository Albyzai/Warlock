package dk.sdu.mmmi.cbse.spell;

import java.util.ArrayList;
import java.util.List;
import data.Entity;
import data.EntityType;
import data.SpellType;
import data.World;

/**
 *
 * @author mads1
 */
public class SpellBook {

    private List<Spell> spellBook;
    private final Entity owner;

    public SpellBook(Entity owner) {
        spellBook = new ArrayList();
        this.owner = owner;
        addDefaultSpells();
    }

    public void addToSpellBook(World world, Entity owner, SpellType spellType) {
        getSpellBook(world, owner).add((Spell) SpellArchive.getSpellArchive().get(spellType));
    }

    public List<Spell> getSpellBook(World world, Entity owner) {
        for(Entity entity : world.getEntities(EntityType.PLAYER, EntityType.ENEMY)){
            if(owner.equals(this.owner)){
                return spellBook;
            }
        }
        return null;
    }

    public Spell getSpell(SpellType spellType) {
        for (Spell spell : spellBook) {
            if (spellType.equals(spell.getSpellType())) {
                return spell;
            }
        }
        return null;
    }

    private void addDefaultSpells() {
        spellBook.add((Spell) SpellArchive.getSpellArchive().get(SpellType.FIREBALL));
    }
}
