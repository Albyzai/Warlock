
package dk.sdu.mmmi.cbse.spell;

import data.SpellType;
import java.util.TreeMap;

/**
 *
 * @author mads1
 */
public class SpellArchive {
    
    private final TreeMap spellArchive = new TreeMap<SpellType, Spell>();
    private Animator animator;

    public SpellArchive(){
        addSpellsToArchive();
    }
    
    private void addSpellsToArchive(){
        spellArchive.put(SpellType.SPELL1, new Spell(10, animator.getSpell1()));
        spellArchive.put(SpellType.SPELL2, new Spell(10, animator.getSpell2()));
        spellArchive.put(SpellType.SPELL3, new Spell(10, animator.getSpell3()));
        spellArchive.put(SpellType.SPELL4, new Spell(10, animator.getSpell4()));
    }

    public TreeMap getSpellArchive() {
        return spellArchive;
    }
    
    
    
}
