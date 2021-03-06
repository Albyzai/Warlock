/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.componentdata;

import data.SpellType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aleksander
 */
public class SpellBook {

    private float globalCooldownTime;
    private float cooldownTimeLeft;
    private List<SpellType> spells = new ArrayList<>();
    private Owner owner;

    public float getGlobalCooldownTime() {
        return globalCooldownTime;
    }

    public void setGlobalCooldownTime(float globalCooldownTime) {
        this.globalCooldownTime = globalCooldownTime;
    }

    public float getCooldownTimeLeft() {
        return cooldownTimeLeft;
    }

    public void setCooldownTimeLeft(float cooldownTimeLeft) {
        this.cooldownTimeLeft = cooldownTimeLeft;
    }

    public List<SpellType> getSpells() {
        return spells;
    }

    public void setSpells(List<SpellType> spells) {
        this.spells = spells;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    
    
    
    
}
