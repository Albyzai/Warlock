/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameengine;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import data.Entity;
import data.GameData;

/**
 *
 * @author mads1
 */
public class Animator{

    private Texture texture;
    private float stateTime;

    private TextureRegion chStandingRight, chStandingLeft, chStandingUp, chStandingDown;

    private Animation chRunningRight, chRunningLeft, chRunningUp, chRunningDown;
    

    public void Animator(){

    }

    public void render(Entity entity, GameData gameData) {
        //stateTime += gameData.getDelta();
        
        //batch.setProjectionMatrix();
//        batch.begin();
//        batch.draw(getFrame(entity), entity.getX(), entity.getY());
//        batch.end();
    }

    public void initializeSprite(Texture imageFile){
    texture = imageFile;


        stateTime = 0;
        chStandingRight = new TextureRegion(texture, 0, 0, 32, 32);
        chStandingLeft = new TextureRegion(texture, 0, 32, 32, 32);
        chStandingUp = new TextureRegion(texture, 0, 64, 32, 32);
        chStandingDown = new TextureRegion(texture, 0, 128, 32, 32);

        Array<TextureRegion> frames = new Array<>();

        //run right
        for (int i = 1; i < 7; i++) {
            frames.add(new TextureRegion(texture, i * 32, 0, 32, 32));
        }
        chRunningRight = new Animation(0.1f, frames);
        frames.clear();

        //run left
        for (int i = 1; i < 7; i++) {
            frames.add(new TextureRegion(texture, i * 32, 0, 32, 32));
        }
        chRunningLeft = new Animation(0.1f, frames);
        frames.clear();

        //run up
        for (int i = 1; i < 5; i++) {
            frames.add(new TextureRegion(texture, i * 32, 0, 32, 32));
        }
        chRunningUp = new Animation(0.1f, frames);
        frames.clear();

        //run down
        for (int i = 1; i < 5; i++) {
            frames.add(new TextureRegion(texture, i * 32, 0, 32, 32));
        }
        chRunningDown = new Animation(0.1f, frames);
        frames.clear();
    }
    
    public Texture getTexture() {
        return texture;
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
    
    public void updateStateTime(float dt){
        stateTime += dt;
    
    }

    
    

}