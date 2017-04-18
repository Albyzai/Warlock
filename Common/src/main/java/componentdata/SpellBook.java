/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentdata;

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

}
