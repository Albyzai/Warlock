/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.spell;

import com.badlogic.gdx.graphics.g2d.Animation;
import data.SpellType;
import data.Entity;
import data.World;

public class Spell {

    private SpellType spelltype;
    private final float damage;
    private final Animation animation;
    private boolean isStatic;

    public Spell(World world, SpellType spellType, float damage, Animation animation, boolean isStatic, float expiration) {
        Entity spell = new Entity();
        world.addEntity(spell);
        spell.setExpiration(expiration);
        this.spelltype = spelltype;
        this.damage = damage;
        this.animation = animation;
        this.isStatic = isStatic;
    }

    public SpellType getSpelltype() {
        return spelltype;
    }

    public float getDamage() {
        return damage;
    }

    public Animation getAnimation() {
        return animation;
    }

    public boolean isIsStatic() {
        return isStatic;
    }

}
