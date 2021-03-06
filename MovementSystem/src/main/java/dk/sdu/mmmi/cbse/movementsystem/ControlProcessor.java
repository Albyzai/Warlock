/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.movementsystem;

import States.CharacterState;
import States.MovementState;
import data.Entity;
import data.EntityType;
import data.GameData;
import static data.GameKeys.*;
import data.SpellType;
import data.World;
import data.componentdata.Body;
import data.componentdata.Position;
import data.componentdata.SpellInfos;
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
    float speed;
    float dt;
    float sAngle;
    float angle;
    boolean spellIsMoving = false;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity entity : world.getEntities(EntityType.PLAYER)) {
            handleMoveClick(entity, gameData);
            handleTargetClick(entity, gameData);
            handleShoot(entity, gameData);

            for (Entity spell : world.getEntities(EntityType.SPELL)) {
<<<<<<< HEAD

                sStartX = spell.getX();
                sStartY = spell.getY();
                sEndX = gameData.getMousePositionX();
                sEndY = gameData.getMousePositionY();
                float dirX = sEndX - sStartX;
                float dirY = sEndY - sStartY;

                float dirLength = (float) Math.sqrt(dirX * dirX + dirY * dirY);
                dirX = dirX / dirLength;
                dirY = dirY / dirLength;
                //sAngle = (float) Math.toDegrees(Math.atan2(sEndY - sStartY, sEndX - sStartX));
//                sDistance = (float) Math.sqrt(Math.pow(sEndX - sStartX, 2) + Math.pow(sEndY - sStartY, 2));
//
//                sDirectionX = (sEndX - sStartX) / sDistance;
//                sDirectionY = (sEndY - sStartY) / sDistance;
//                spell.setX(sStartX);
//                spell.setY(sStartY);

//                    spell.setX(spell.getX() + sDirectionX * spell.getMaxSpeed() * gameData.getDelta());
//                    spell.setY(spell.getY() + sDirectionY * spell.getMaxSpeed() * gameData.getDelta());
                spell.setX(spell.getX() + dirX * spell.getMaxSpeed() * gameData.getDelta());
                spell.setY(spell.getY() + dirY * spell.getMaxSpeed() * gameData.getDelta());
                System.out.println("X: " + spell.getX() + "    Y: " + spell.getY());
//                    if ((float) Math.sqrt(Math.pow(spell.getX() - sStartX, 2) + Math.pow(spell.getY() - sStartY, 2)) >= sDistance) {
//                        spell.setX(sEndX);
//                        spell.setY(sEndY);
//                        spellIsMoving = false;
//                    }

=======
                if (gameData.getKeys().isPressed(RIGHT_MOUSE)) {
                    Position sp = spell.get(Position.class);
                    sStartX = sp.getX();
                    sStartY = sp.getY();
                    sEndX = gameData.getMousePositionX();
                    sEndY = gameData.getMousePositionY();
                    sAngle = (float) Math.toDegrees(Math.atan2(sEndY - sStartY, sEndX - sStartX));
                    sDistance = (float) Math.sqrt(Math.pow(sEndX - sStartX, 2) + Math.pow(sEndY - sStartY, 2));

                    sDirectionX = (sEndX - sStartX) / sDistance;
                    sDirectionY = (sEndY - sStartY) / sDistance;
                    sp.setX(sStartX);
                    sp.setY(sStartY);
                    spellIsMoving = true;

                    if (spellIsMoving) {
                        sp.setX(sp.getX() + sDirectionX * spell.getMaxSpeed() * gameData.getDelta());
                        sp.setY(sp.getY() + sDirectionY * spell.getMaxSpeed() * gameData.getDelta());
                        if ((float) Math.sqrt(Math.pow(sp.getX() - sStartX, 2) + Math.pow(sp.getY() - sStartY, 2)) >= sDistance) {
                            sp.setX(sEndX);
                            sp.setY(sEndY);
                            spellIsMoving = false;
                        }
                    }
                }
>>>>>>> b205658b22705f5daf1e969a07d82e0b5eca6520
            }
        }
    }

    private void handleMoveClick(Entity e, GameData gameData) {
        Position p = e.get(Position.class);
        Body b = e.get(Body.class);
        if (gameData.getKeys().isPressed(RIGHT_MOUSE)) {
<<<<<<< HEAD
            startX = e.getX();
            startY = e.getY();

//            endX = gameData.getScreenX();
//            endY = gameData.getDisplayHeight() - gameData.getScreenY();
            endX = gameData.getMousePositionX() - (e.getWidth() / 2);
            endY = gameData.getMousePositionY() - (e.getHeight() / 2);
            if (startX == endX && startY == endY) {
                e.setCharState(CharacterState.IDLE);
            } else {
                angle = (float) Math.toDegrees(Math.atan2(endY - startY, endX - startX));

                distance = (float) Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));

                directionX = (endX - startX) / distance;
                directionY = (endY - startY) / distance;
                e.setX(startX);
                e.setY(startY);
                e.setCharState(CharacterState.MOVING);

                if (angle > -45 && angle < 45) {
                    e.setMoveState(MovementState.RUNNINGRIGHT);
                } else if (angle < 135 && angle > 45) {
                    e.setMoveState(MovementState.RUNNINGUP);
                } else if (angle > -135 && angle < -45) {
                    e.setMoveState(MovementState.RUNNINGDOWN);
                } else {
                    e.setMoveState(MovementState.RUNNINGLEFT);
                }
=======

            startX = p.getX();
            startY = p.getY();
            endX = gameData.getMousePositionX() - b.getWidth() / 2;
            endY = gameData.getMousePositionY() - b.getHeight() / 2;
            angle = (float) Math.toDegrees(Math.atan2(endY - startY, endX - startX));
            speed = 200;
            distance = (float) Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));

            directionX = (endX - startX) / distance;
            directionY = (endY - startY) / distance;
            p.setX(startX);
            p.setY(startY);
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
>>>>>>> b205658b22705f5daf1e969a07d82e0b5eca6520
            }
        }
        if (e.getCharState().equals(CharacterState.MOVING)) {
            p.setX(p.getX() + directionX * speed * gameData.getDelta());
            p.setY(p.getY() + directionY * speed * gameData.getDelta());
            if ((float) Math.sqrt(Math.pow(p.getX() - startX, 2) + Math.pow(p.getY() - startY, 2)) >= distance) {
                speed = 0;
                //e.setCharState(CharacterState.IDLE);
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
            SpellInfos spell = e.get(SpellInfos.class);
//            if (spell.getChosenSpell() == null) {
//                System.out.println("No spell chosen");
//            }
//            else {
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
            //System.out.println("Shooting: + " + spell.getChosenSpell());
            //}

        }
    }

    private void handleTargetClick(Entity e, GameData gameData) {
        if (gameData.getKeys().isPressed(NUM_1)) {
            e.get(SpellInfos.class).setChosenSpell(SpellType.FIREBALL);

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
