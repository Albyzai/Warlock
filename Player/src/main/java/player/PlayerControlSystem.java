package player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import data.Entity;
import static data.EntityType.PLAYER;
import data.GameData;
import static data.GameKeys.*;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IEntityProcessingService;
import services.IGamePluginService;

@ServiceProviders(value = {
    @ServiceProvider(service = IEntityProcessingService.class),})
/**
 *
 * @author jcs
 */
public class PlayerControlSystem implements IEntityProcessingService {

    private float[] shapex = new float[4];
    private float[] shapey = new float[4];
    float directionY;
    float directionX;

    @Override
    public void process(GameData gameData, World world) {
        // TODO: Implement entity processor

        for (Entity player : world.getEntities(PLAYER)) {
            float x = player.getX();
            float y = player.getY();
            float dt = gameData.getDelta();
            float dx = player.getDx();
            float dy = player.getDy();
            float acceleration = player.getAcceleration();
            float deceleration = player.getDeacceleration();
            float rotationSpeed = player.getRotationSpeed();
            float radians = player.getRadians();
            float maxSpeed = player.getMaxSpeed();

//            if (gameData.getKeys().isDown(LEFT_MOUSE)) {
//                player.setRadians(radians += rotationSpeed * dt);
//            } else if (gameData.getKeys().isDown(RIGHT_MOUSE)) {
//                player.setRadians(radians -= rotationSpeed * dt);
//            }
            //Walking with right mouse
            if (gameData.getKeys().isDown(RIGHT_MOUSE)) {
                player.setDeacceleration(4);
                float pathX = gameData.getScreenX() - player.getX();
                float pathY = (gameData.getDisplayHeight() - gameData.getScreenY()) - player.getY();

                float distance = (float) Math.sqrt(pathX * pathX + pathY * pathY);
                directionX = pathX / distance;
                directionY = pathY / distance;
                                player.setX(x += directionX * acceleration);
                player.setY(y += directionY * acceleration);
            } 
            int diffx = Math.abs((int)player.getX() - gameData.getScreenX());
            int diffy = Math.abs((int)player.getY() - gameData.getScreenY());
            if (diffx < 50 && diffx > -50 && diffy < 50 && diffy > -50) {
                player.setSpeed(0);
                System.out.println("player is at position");
            }
            //fix this.. Så den ikke kører loop medmindre der er trykket
            if ((int)player.getX() != gameData.getScreenX() && (int)player.getY() != gameData.getScreenY()) {
                player.setX(x += directionX * acceleration);
                player.setY(y += directionY * acceleration);
            }
            
            //Shooting left mouse
            if (gameData.getKeys().isDown(LEFT_MOUSE)) {
                System.out.println("left mouse down");
            }

            //til her
            // deceleration
            float vec = (float) Math.sqrt(dx * dx + dy * dy);
            if (vec > 0) {
                player.setDx(player.getDx() - (dx / vec) * deceleration * dt);
                player.setDy(player.getDy() - (dy / vec) * deceleration * dt);
                //dx -= (dx / vec) * deceleration * dt;
                //dy -= (dy / vec) * deceleration * dt;
            }
            if (vec > maxSpeed) {
                player.setDx((dx / vec) * maxSpeed);
                player.setDy((dy / vec) * maxSpeed);
                //dx = (dx / vec) * maxSpeed;
                //dy = (dy / vec) * maxSpeed;
            }

            //fire
            if (gameData.getKeys().isPressed(LEFT_MOUSE)) {
                //todo
            }

            // set position
            player.setX(player.getX() + dx * dt);
            player.setY(player.getY() + dy * dt);
            //x += dx * dt;
            //y += dy * dt;

            // set shape
            setShape(x, y, radians);
            player.setShapeX(shapex);
            player.setShapeY(shapey);

            // screen wrap
            wrap(x, y, gameData, player);
            
        }

    }

    private void setShape(float x, float y, float radians) {
        shapex[0] = (float) (x + Math.cos(radians) * 8f);
        shapey[0] = (float) (y + Math.sin(radians) * 8);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 5);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 5);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8);

    }

    private void wrap(float x, float y, GameData gameData, Entity player) {
        if (x < 0) {
            player.setX(gameData.getDisplayWidth());
            //x = gameData.getDisplayWidth();
        }
        if (x > gameData.getDisplayWidth()) {
            player.setX(0);
            //x = 0;
        }
        if (y < 0) {
            player.setY(gameData.getDisplayHeight());
            //y = gameData.getDisplayHeight();
        }
        if (y > gameData.getDisplayHeight()) {
            player.setY(0);
            //y = 0;
        }
    }
}
