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

    private SpellType spellType;
    private final float damage;
    private final Animation animation;
    private boolean isStatic;
    private Entity spellEntity;
    private float speed;
    private float acceleration; 
    private int cooldown;

    public Spell(World world, SpellType spellType, float damage, Animation animation, boolean isStatic, float expiration, float speed, float acceleration, int cooldown) {
        spellEntity = new Entity();
        world.addEntity(spellEntity);
        spellEntity.setExpiration(expiration);
        this.spellType = spellType;
        this.damage = damage;
        this.animation = animation;
        this.isStatic = isStatic;
        this.speed = speed;
        this.acceleration = acceleration;
        this.cooldown = cooldown;
    }

    public SpellType getSpellType() {
        return spellType;
    }

    public float getDamage() {
        return damage;
    }

    public Animation getAnimation() {
        return animation;
    }

    public int getCooldown() {
        return cooldown;
    }

    public boolean isIsStatic() {
        return isStatic;
    }

    public Entity getSpellEntity() {
        return spellEntity;
    }


    public float getSpeed() {
        return speed;
    }

    public float getAcceleration() {
        return acceleration;
    }
    
    

}
