/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.movementsystem;

import States.CharacterState;
import com.badlogic.gdx.math.Vector3;
import States.MovementState;
import data.Entity;
import data.EntityType;
import static data.EntityType.SPELL;
import data.GameData;
import static data.GameKeys.*;
import data.SpellType;
import data.World;
import java.util.Collection;
import data.SpellInfo;
import org.openide.util.lookup.ServiceProvider;
import services.IEntityProcessingService;

@ServiceProvider(service = IEntityProcessingService.class)
/**
 *
 * @author jonaspedersen
 */
public class ControlProcessor implements IEntityProcessingService {

    float startX, startY, endX, endY;
    float sStartX, sStartY, sEndX, sEndY;
    float directionX;
    float directionY;
    float sDirectionX;
    float sDirectionY;
    float distance;
    float sDistance;
    float speed = 200;
    float dt;
    float sAngle;
    float angle;
    boolean spellIsMoving;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity entity : world.getEntities(EntityType.PLAYER)) {
            handleMoveClick(entity, gameData);
            handleTargetClick(entity, gameData);
            handleShoot(entity, gameData);

            for (Entity spell : world.getEntities(EntityType.SPELL)) {
                 
                sStartX = spell.getX();
                sStartY = spell.getY();
                sEndX = gameData.getMousePositionX();
                sEndY = gameData.getMousePositionY();
                sAngle = (float) Math.toDegrees(Math.atan2(sEndY - sStartY, sEndX - sStartX));
                sDistance = (float) Math.sqrt(Math.pow(sEndX - sStartX, 2) + Math.pow(sEndY - sStartY, 2));

                sDirectionX = (sEndX - sStartX) / sDistance;
                sDirectionY = (sEndY - sStartY) / sDistance;
                spell.setX(sStartX);
                spell.setY(sStartY);
                spellIsMoving = true;

                if (spellIsMoving) {
                    spell.setX(spell.getX() + sDirectionX * spell.getMaxSpeed() * gameData.getDelta());
                    spell.setY(spell.getY() + sDirectionY * spell.getMaxSpeed() * gameData.getDelta());
                    if ((float) Math.sqrt(Math.pow(spell.getX() - sStartX, 2) + Math.pow(spell.getY() - sStartY, 2)) >= sDistance) {
                        spell.setX(sEndX);
                        spell.setY(sEndY);
                        spellIsMoving = false;
                    }
                }
            }

        }
    }

    private void handleMoveClick(Entity e, GameData gameData) {
        if (gameData.getKeys().isPressed(RIGHT_MOUSE)) {
            startX = e.getX();
            startY = e.getY();
            endX = gameData.getMousePositionX();
            endY = gameData.getMousePositionY();
            angle = (float) Math.toDegrees(Math.atan2(endY - startY, endX - startX));

            distance = (float) Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));

            directionX = (endX - startX) / distance;
            directionY = (endY - startY) / distance;
            e.setX(startX);
            e.setY(startY);
            e.setCharState(CharacterState.MOVING);

            if (angle > -45 && angle < 45) {
                e.setMoveState(MovementState.RUNNINGRIGHT);
            }
            else if (angle < 135 && angle > 45) {
                e.setMoveState(MovementState.RUNNINGUP);
            }
            else if (angle > -135 && angle < -45) {
                e.setMoveState(MovementState.RUNNINGDOWN);
            }
            else {
                e.setMoveState(MovementState.RUNNINGLEFT);
            }
        }
        if (e.getCharState().equals(CharacterState.MOVING)) {
            e.setX(e.getX() + directionX * speed * gameData.getDelta());
            e.setY(e.getY() + directionY * speed * gameData.getDelta());
            if ((float) Math.sqrt(Math.pow(e.getX() - startX, 2) + Math.pow(e.getY() - startY, 2)) >= distance) {
                e.setX(endX);
                e.setY(endY);
                e.setCharState(CharacterState.IDLE);
            }
        }
        if (gameData.getKeys().isPressed(ESCAPE)) {
            //leGameMenu.plsShowUp();

        }
        if (gameData.getKeys().isPressed(Q)) {
            //lePotion.plsDrink();
        }
    }

    private void handleShoot(Entity e, GameData gameData) {
        if (gameData.getKeys().isDown(LEFT_MOUSE)) {
            if (e.getChosenSpell() == null) {
                System.out.println("No spell chosen");
            }
            else {
                if (angle > -45 && angle < 45) {
                    e.setMoveState(MovementState.STANDINGRIGHT);
                }
                else if (angle < 135 && angle > 45) {
                    e.setMoveState(MovementState.STANDINGUP);
                }
                else if (angle > -135 && angle < -45) {
                    e.setMoveState(MovementState.STANDINGDOWN);
                }
                else {
                    e.setMoveState(MovementState.STANDINGLEFT);
                }
                System.out.println("shoot at target location");
                e.setCharState(CharacterState.CASTING);
                System.out.println("Shooting: + " + e.getChosenSpell());
            }

        }
    }

    private void handleTargetClick(Entity e, GameData gameData) {
        if (gameData.getKeys().isPressed(NUM_1)) {
            e.setChosenSpell(SpellType.FIREBALL);

        }
        else {
            return;
        }
        if (gameData.getKeys().isPressed(NUM_2)) {
            //e.setChosenSpell(SpellType.SPELL2);

        }
        if (gameData.getKeys().isPressed(NUM_3)) {
            //Spell 3

        }
        if (gameData.getKeys().isPressed(NUM_4)) {
            //Spell 4

        }

    }

}
