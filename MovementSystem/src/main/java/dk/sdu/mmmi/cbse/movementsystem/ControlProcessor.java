/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.movementsystem;

import States.CharacterState;
import data.Entity;
import static data.EntityType.SPELL;
import data.GameData;
import static data.GameKeys.*;
import data.SpellType;
import data.World;
import java.util.Collection;
import org.openide.util.lookup.ServiceProvider;
import services.IEntityProcessingService;

@ServiceProvider(service = IEntityProcessingService.class)
/**
 *
 * @author jonaspedersen
 */
public class ControlProcessor implements IEntityProcessingService {

    private boolean moving;
    float startX, startY, endX, endY;
    float directionX;
    float directionY;
    float elapsed;
    float distance;
    float speed = 200;
    float dt;

    @Override
    public void process(GameData gameData, World world) {
        Collection<Entity> entities = world.getEntities();

        for (Entity entity : entities) {

            handleMoveClick(entity, gameData);
            handleTargetClick(entity, gameData);

            if (entity.getType() != SPELL) {
                handleShoot(entity, gameData);
            }
        }
    }

    private void handleMoveClick(Entity e, GameData gameData) {
        if (gameData.getKeys().isPressed(RIGHT_MOUSE)) {

            startX = e.getX();
            startY = e.getY();
            endX = gameData.getScreenX();
            endY = gameData.getDisplayHeight() - gameData.getScreenY();
            elapsed = 0.01f;

            distance = (float) Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));

            directionX = (endX - startX) / distance;
            directionY = (endY - startY) / distance;
            e.setX(startX);
            e.setY(startY);
            moving = true;
        }

        if (distance > 0) {
            if (moving) {
                e.setX(e.getX() + directionX * speed * gameData.getDelta());
                e.setY(e.getY() + directionY * speed * gameData.getDelta());
                if (Math.sqrt(Math.pow(e.getX() - startX, 2) + Math.pow(e.getY() - startY, 2)) >= distance) {
                    e.setX(endX);
                    e.setY(endY);
                    moving = false;
                }

            }
        }
        if (gameData.getKeys().isPressed(ESCAPE)) {
            //leGameMenu.plsShowUp();

        }
        if (gameData.getKeys().isPressed(Q)) {
            //Potions?
        }
    }

    private void handleShoot(Entity e, GameData gameData) {
        if (gameData.getKeys().isDown(LEFT_MOUSE)) {
            System.out.println("shoot at target location");
            e.setCharState(CharacterState.CASTING);

            if (e.getChosenSpell() == null) {
                System.out.println("No spell chosen");
            } else {

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
        if (gameData.getKeys().isPressed(NUM_2)) {
            e.setChosenSpell(SpellType.SPELL2);

        }
        if (gameData.getKeys().isPressed(NUM_3)) {
            //Spell 3

        }
        if (gameData.getKeys().isPressed(NUM_4)) {
            //Spell 4

        }

    }

}
