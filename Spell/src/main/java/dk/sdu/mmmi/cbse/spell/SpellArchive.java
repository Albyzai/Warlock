package dk.sdu.mmmi.cbse.spell;

import data.SpellType;
import data.World;
import java.util.TreeMap;
import data.SpellList;

/**
 *
 * @author mads1
 */
public class SpellArchive {

    private static final TreeMap spellArchive = new TreeMap<SpellType, Spell>();

    public SpellArchive(World world) {
        spellArchive.put(SpellType.FIREBALL, new Spell(world, SpellType.FIREBALL, SpellList.FIREBALL_DMG, "assets/0029.png", SpellList.FIREBALL_STATIC, SpellList.FIREBALL_EXPIRATION, SpellList.FIREBALL_SPEED, SpellList.FIREBALL_ACCELERATION, SpellList.FIREBALL_COOLDOWN, SpellList.FIREBALL_BOUNCE));
    }

    public static TreeMap getSpellArchive() {
        return spellArchive;
    }


}
