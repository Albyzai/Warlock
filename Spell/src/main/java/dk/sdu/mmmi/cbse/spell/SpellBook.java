package dk.sdu.mmmi.cbse.spell;

import java.util.ArrayList;
import java.util.List;
import data.Entity;
import data.SpellType;

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

    public void addToSpellBook(SpellType spellType) {
        spellBook.add((Spell) SpellArchive.getSpellArchive().get(spellType));
    }

    public List<Spell> getSpellBook() {
        return spellBook;
    }

    public Spell getSpell(SpellType spellType) {
        for (Spell spell : spellBook) {
            if (spellType.equals(spell.getSpelltype())) {
                return spell;
            }
        }
        return null;
    }

    private void addDefaultSpells() {
        spellBook.add((Spell) SpellArchive.getSpellArchive().get(SpellType.FIREBALL));
    }
}
