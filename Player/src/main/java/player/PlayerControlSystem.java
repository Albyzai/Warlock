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

            // set shape
            setShape(x, y, player.getRadians());
            player.setShapeX(shapex);
            player.setShapeY(shapey);

        }

    }

    private void setShape(float x, float y, float radians) {
        shapex[0] = (float) (x + Math.cos(radians) * 32);
        shapey[0] = (float) (y + Math.sin(radians) * 32);

        shapex[1] = (float) (x + Math.cos(radians - 16 * 3.1415f / 20) * 32);
        shapey[1] = (float) (y + Math.sin(radians - 16 * 3.1145f / 20) * 32);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 20);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 20);

        shapex[3] = (float) (x + Math.cos(radians + 16 * 3.1415f / 20) * 32);
        shapey[3] = (float) (y + Math.sin(radians + 16 * 3.1415f / 20) * 32);

    }

}
