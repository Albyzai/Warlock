/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.spell;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import data.SpellType;

/**
 *
 * @author mads1
 */
public class Animator extends ApplicationAdapter{

    private Sprite sprite;
    private Batch batch;
    private TextureAtlas atlas;
    private AssetManager assets;
    private float stateTime;
    
    private SpellType spell;
    private String spellName;

    private Animation<TextureRegion> spell1, spell2, spell3, spell4;
    

    @Override
    public void create(){
        batch = new SpriteBatch();

        assets = new AssetManager();
        assets.load("assets/Spells.pack", TextureAtlas.class);
        assets.finishLoading();
        atlas = assets.get("assets/Spells.pack", TextureAtlas.class);

        stateTime = 0;
        Texture texture = atlas.findRegion(spellName).getTexture();
        sprite = new Sprite();

        Array<TextureRegion> frames = new Array<>();

        //run right
        for (int i = 1; i < 7; i++) {
            frames.add(new TextureRegion(texture, i * 16, 0, 16, 16));
        }
        spell1 = new Animation(0.1f, frames);
        frames.clear();

        //run left
        for (int i = 1; i < 7; i++) {
            frames.add(new TextureRegion(texture, i * 16, 0, 16, 16));
        }
        spell2 = new Animation(0.1f, frames);
        frames.clear();

        //run up
        for (int i = 1; i < 5; i++) {
            frames.add(new TextureRegion(texture, i * 16, 0, 16, 16));
        }
        spell3 = new Animation(0.1f, frames);
        frames.clear();

        //run down
        for (int i = 1; i < 5; i++) {
            frames.add(new TextureRegion(texture, i * 16, 0, 16, 16));
        }
        spell4 = new Animation(0.1f, frames);
        frames.clear();
        
        sprite.setSize(500, 500);
    }

    @Override
    public void render() {
        stateTime += Gdx.graphics.getDeltaTime();
        sprite.setPosition(1700, 100);
        sprite.setTexture(getFrame().getTexture());

        batch.begin();
        batch.draw(getFrame(), 500, 500);
        batch.end();
    }

    @Override
    public void dispose(){
        batch.dispose();
        atlas.dispose();
    }
    public TextureAtlas getAtlas() {
        return atlas;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public TextureRegion getFrame() {
        TextureRegion region = null;
        switch (spell) {
            case SPELL1:
                region = (TextureRegion) spell1.getKeyFrame(stateTime, true);
                break;
            case SPELL2:
                region = (TextureRegion) spell2.getKeyFrame(stateTime, true);
                break;
            case SPELL3:
                region = (TextureRegion) spell3.getKeyFrame(stateTime, true);
                break;
            case SPELL4:
                region = (TextureRegion) spell4.getKeyFrame(stateTime, true);
                break;
        }
        return region;
    }

    public Animation<TextureRegion> getSpell1() {
        return spell1;
    }

    public Animation<TextureRegion> getSpell2() {
        return spell2;
    }

    public Animation<TextureRegion> getSpell3() {
        return spell3;
    }

    public Animation<TextureRegion> getSpell4() {
        return spell4;
    }
    
    

}
