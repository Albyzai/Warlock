package dk.sdu.mmmi.cbse.spell;

import data.SpellType;
import data.World;
import java.util.TreeMap;
import data.SpellInfo;

/**
 *
 * @author mads1
 */
public class SpellArchive {

    private static final TreeMap spellArchive = new TreeMap<SpellType, Spell>();

    public SpellArchive(World world) {
        //spellArchive.put(SpellType.FIREBALL, new Spell(world, SpellType.FIREBALL, SpellInfo.FIREBALL_DMG, animator.getSpell1(), SpellInfo.FIREBALL_STATIC, SpellInfo.FIREBALL_EXPIRATION, SpellInfo.FIREBALL_SPEED, SpellInfo.FIREBALL_ACCELERATION, SpellInfo.FIREBALL_COOLDOWN, SpellInfo.FIREBALL_BOUNCE));
    }

    public static TreeMap getSpellArchive() {
        return spellArchive;
    }


}
