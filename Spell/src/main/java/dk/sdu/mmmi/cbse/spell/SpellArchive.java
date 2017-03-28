package dk.sdu.mmmi.cbse.spell;

import data.SpellType;
import data.World;
import java.util.TreeMap;

/**
 *
 * @author mads1
 */
public class SpellArchive {

    private static final TreeMap spellArchive = new TreeMap<SpellType, Spell>();
    private Animator animator = new Animator();

    public SpellArchive(World world) {
        addSpellsToArchive(world);
    }

    private void addSpellsToArchive(World world) {
        spellArchive.put(SpellType.FIREBALL, new Spell(world, SpellType.FIREBALL, 10, animator.getSpell1(), false, 5f));
        spellArchive.put(SpellType.SPELL2, new Spell(world, SpellType.SPELL2, 10, animator.getSpell2(), false, 5f));
        spellArchive.put(SpellType.SPELL3, new Spell(world, SpellType.SPELL3, 10, animator.getSpell3(), false, 5f));
        spellArchive.put(SpellType.SPELL4, new Spell(world, SpellType.SPELL4, 10, animator.getSpell4(), false, 5f));
    }

    public static TreeMap getSpellArchive() {
        return spellArchive;
    }

    public Animator getAnimator() {
        return animator;
    }

}
