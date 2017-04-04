/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

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
import States.MovementState;
import data.Entity;

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

    private TextureRegion chStandingRight, chStandingLeft, chStandingUp, chStandingDown;

    private Animation chRunningRight, chRunningLeft, chRunningUp, chRunningDown;
    

    @Override
    public void create(){
        batch = new SpriteBatch();

        assets = new AssetManager();
        assets.load("assets/Characters.pack", TextureAtlas.class);
        assets.finishLoading();
        atlas = assets.get("assets/Characters.pack", TextureAtlas.class);

        stateTime = 0;
        Texture texture = atlas.findRegion("FireCharacter").getTexture();

        chStandingRight = new TextureRegion(texture, 0, 0, 16, 16);
        chStandingLeft = new TextureRegion(texture, 0, 16, 16, 16);
        chStandingUp = new TextureRegion(texture, 0, 32, 16, 16);
        chStandingDown = new TextureRegion(texture, 0, 48, 16, 16);

        Array<TextureRegion> frames = new Array<>();

        //run right
        for (int i = 1; i < 7; i++) {
            frames.add(new TextureRegion(texture, i * 16, 0, 16, 16));
        }
        chRunningRight = new Animation(0.1f, frames);
        frames.clear();

        //run left
        for (int i = 1; i < 7; i++) {
            frames.add(new TextureRegion(texture, i * 16, 0, 16, 16));
        }
        chRunningLeft = new Animation(0.1f, frames);
        frames.clear();

        //run up
        for (int i = 1; i < 5; i++) {
            frames.add(new TextureRegion(texture, i * 16, 0, 16, 16));
        }
        chRunningUp = new Animation(0.1f, frames);
        frames.clear();

        //run down
        for (int i = 1; i < 5; i++) {
            frames.add(new TextureRegion(texture, i * 16, 0, 16, 16));
        }
        chRunningDown = new Animation(0.1f, frames);
        frames.clear();
    }

    @Override
    public void render() {
        stateTime += Gdx.graphics.getDeltaTime();
//        sprite.setPosition(1700, 100);
//        sprite.setTexture(getFrame().getTexture());

        batch.begin();
        //sprite.draw(batch);
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

    public TextureRegion getFrame(Entity entity) {
        TextureRegion region = null;
        switch (entity.getMoveState()) {
            case RUNNINGRIGHT:
                region = (TextureRegion) chRunningRight.getKeyFrame(stateTime, true);
                break;
            case RUNNINGLEFT:
                region = (TextureRegion) chRunningLeft.getKeyFrame(stateTime, true);
                break;
            case RUNNINGUP:
                region = (TextureRegion) chRunningUp.getKeyFrame(stateTime, true);
                break;
            case RUNNINGDOWN:
                region = (TextureRegion) chRunningDown.getKeyFrame(stateTime, true);
                break;
            case STANDINGRIGHT:
                region = chStandingRight;
                break;
            case STANDINGLEFT:
                region = chStandingLeft;
                break;
            case STANDINGUP:
                region = chStandingUp;
                break;
            case STANDINGDOWN:
                region = chStandingDown;
                break;
        }
        return region;
    }

    public float getStateTime() {
        return stateTime;
    }
    

}
