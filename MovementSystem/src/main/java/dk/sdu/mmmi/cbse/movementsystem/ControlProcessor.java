/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.movementsystem;

import data.Entity;
import static data.EntityType.SPELL;
import data.GameData;
import static data.GameKeys.LEFT_MOUSE;
import static data.GameKeys.RIGHT_MOUSE;
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

    @Override
    public void process(GameData gameData, World world)
    {
        Collection<Entity> entities = world.getEntities();

        for (Entity entity : entities) {

            handleMoveClick(entity, gameData);
            handleTargetClick(entity, gameData);

            if (entity.getType() != SPELL) {
                handleShoot(entity, gameData);
            }
        }
    }

    private void handleMoveClick(Entity e, GameData gameData)
    {
        if (gameData.getKeys().isPressed(RIGHT_MOUSE)) {

            startX = e.getX();
            startY = e.getY();
            endX = gameData.getScreenX();
            endY = gameData.getScreenY();
            elapsed = 0.01f;
            System.out.println("endX = " + endX);
            System.out.println("endY = " + endY);

            e.setDeacceleration(4);

            float pathX = gameData.getScreenX() - e.getX();
            float pathY = (gameData.getDisplayHeight() - gameData.getScreenY()) - e.getY();

            distance = (float) Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));
            //float distance = (float) Math.sqrt(pathX * pathX + pathY * pathY);

            directionX = pathX / distance;
            directionY = pathY / distance;
            e.setX(startX);
            e.setY(startY);
            moving = true;
        }

        if (moving) {
            e.setX( e.getX() + directionX * e.getAcceleration());
            e.setY(e.getY() + directionY * e.getAcceleration());
            if (Math.sqrt(Math.pow(e.getX() - startX, 2) + Math.pow(e.getY() - startY, 2)) >= distance) {
                e.setX(endX);
                e.setY(endY);
                moving = false;
            }

        }

        System.out.println("x = " + e.getX());
        System.out.println("y = " + e.getY());

//        int diffx = Math.abs((int) e.getX() - gameData.getScreenX());
//        int diffy = Math.abs((int) e.getY() - gameData.getScreenY());
//        if (diffx < 50 && diffx > -50 && diffy < 50 && diffy
//                > -50) {
//            e.setSpeed(0);
//        }
//
//        float vec = (float) Math.sqrt(e.getDx() * e.getDx() + e.getDy() * e.getDy());
//        if (vec
//                > 0) {
//            e.setDx(e.getDx() - (e.getDx() / vec) * e.getDeacceleration() * gameData.getDelta());
//            e.setDy(e.getDy() - (e.getDy() / vec) * e.getDeacceleration() * gameData.getDelta());
//        }
//        if (vec
//                > e.getMaxSpeed()) {
//            e.setDx((e.getDx() / vec) * e.getMaxSpeed());
//            e.setDy((e.getDy() / vec) * e.getMaxSpeed());
//        }
    }

    private void handleShoot(Entity e, GameData gameData)
    {

        //Shooting left mouse
        if (gameData.getKeys().isDown(LEFT_MOUSE)) {
            System.out.println("shoot at target location");
        }
    }

    private void handleTargetClick(Entity e, GameData gameData)
    {

    }

}

