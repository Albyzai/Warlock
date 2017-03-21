/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.spell;

import com.badlogic.gdx.graphics.g2d.Animation;

public class Spell {

    private final float damage;
    private final Animation animation;
    

    public Spell(float damage, Animation animation) {
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
