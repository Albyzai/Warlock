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
import data.SpellType;

/**
 *
 * @author mads1
 */
public class SpellBook {

    private List<Spell> spellBook;
    private Entity owner; 
    private Animator animator;

    public SpellBook(Entity owner) {
        spellBook = new ArrayList();
        this.owner = owner;
        addDefaultSpells();
    }

    public void addToSpellBook(SpellType spellType, int damage, Animation animation) {
        spellBook.add(new Spell(spellType, damage, animation));
    }

    public List<Spell> getSpellBook() {
        return spellBook;
    }
    
    private void addDefaultSpells(){
        spellBook.add(new Spell(SpellType.SPELL1, 10, animator.getSpell1()));
        spellBook.add(new Spell(SpellType.SPELL2, 15, animator.getSpell2()));
        spellBook.add(new Spell(SpellType.SPELL3, 20, animator.getSpell3()));
        spellBook.add(new Spell(SpellType.SPELL4, 25, animator.getSpell4()));
    }

}
