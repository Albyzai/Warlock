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
        spellArchive.put(SpellType.FIREBALL, new Spell(world, SpellType.FIREBALL, 10, animator.getSpell1(), false, 5f, 200f, 50f, 2));
    }

    public static TreeMap getSpellArchive() {
        return spellArchive;
    }

    public Animator getAnimator() {
        return animator;
    }

}
