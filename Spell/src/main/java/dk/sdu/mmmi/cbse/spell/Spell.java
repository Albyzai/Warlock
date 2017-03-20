/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.spell;

import com.badlogic.gdx.graphics.g2d.Animation;
import data.Entity;
import data.SpellType;

public class Spell {

    private SpellType spellType;
    private float damage;
    private Animation animation;

    public Spell(SpellType spelltype, float damage, Animation animation) {
        this.spellType = spellType;
        this.damage = damage;
        this.animation = animation;
    }

    public float getDamage() {
        return damage;
    }

    public Animation getAnimation() {
        return animation;
    }

}
