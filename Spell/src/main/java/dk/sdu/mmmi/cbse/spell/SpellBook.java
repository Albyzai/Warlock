/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.spell;

import com.badlogic.gdx.graphics.g2d.Animation;
import java.util.ArrayList;
import java.util.List;
import data.Entity;

/**
 *
 * @author mads1
 */
public class SpellBook {

    private List<Spell> spellBook;
    private Entity owner; 

    public SpellBook(Entity owner) {
        spellBook = new ArrayList();
        this.owner = owner;
    }

    public void addToSpellBook(int damage, Animation animation, Entity caster) {
        spellBook.add(new Spell(damage, animation, caster));
    }

    public List<Spell> getSpellBook() {
        return spellBook;
    }

}
