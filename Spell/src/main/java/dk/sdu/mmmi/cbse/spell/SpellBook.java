
package dk.sdu.mmmi.cbse.spell;

import com.badlogic.gdx.graphics.g2d.Animation;
import java.util.ArrayList;
import java.util.List;
import data.Entity;
import data.SpellType;

/**
 *
 * @author mads1
 */
public class SpellBook {

    private List spellBook;
    private final Entity owner; 
    private SpellArchive archieve = new SpellArchive();

    public SpellBook(Entity owner) {
        spellBook = new ArrayList();
        this.owner = owner;
        addDefaultSpells();
    }

    public void addToSpellBook(SpellType spellType) {
        spellBook.add(archieve.getSpellArchive().get(spellType));
    }

    public List<Spell> getSpellBook() {
        return spellBook;
    }
    
    
    private void addDefaultSpells(){
        spellBook.add(archieve.getSpellArchive().get(SpellType.SPELL1));
    }

}
