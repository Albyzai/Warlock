/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.spell;

import com.badlogic.gdx.graphics.g2d.Animation;
import data.Entity;

public class Spell {

    private float damage;
    private Animation animation;
    private Entity caster;

    public Spell(float damage, Animation animation, Entity caster) {
        this.damage = damage;
        this.animation = animation;
        this.caster = caster;
    }

    public float getDamage() {
        return damage;
    }

    public Entity getCaster() {
        return caster;
    }

    public Animation getAnimation() {
        return animation;
    }

}
