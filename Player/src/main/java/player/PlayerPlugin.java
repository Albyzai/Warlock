package player;

import States.CharacterState;
import data.Entity;
import static data.EntityType.PLAYER;
import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IEntityProcessingService;
import services.IGamePluginService;
import States.MovementState;
import data.ViewManager;
import data.ViewPaths;

@ServiceProviders(value = {
    @ServiceProvider(service = IEntityProcessingService.class),
    @ServiceProvider(service = IGamePluginService.class)
})
/**
 *
 * @author jcs
 */
public class PlayerPlugin implements IEntityProcessingService, IGamePluginService {

    private float[] shapex = new float[4];
    private float[] shapey = new float[4];
    private float directionY;
    private float directionX;
    private Entity player;
    
	
            
    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        ViewPaths.CHARACTER_FINAL_IMAGE_PATH = PlayerPlugin.class.getResource(ViewPaths.CHARACTER_IMAGE_PATH).getPath().replace("file:", "");
        ViewManager.createView(ViewPaths.CHARACTER_FINAL_IMAGE_PATH, false);
        
        Entity player = new Entity();
        player.setType(PLAYER);

	player.setView(ViewManager.getView(ViewPaths.CHARACTER_FINAL_IMAGE_PATH));
        player.setPosition(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);

        player.setMaxSpeed(2);
        player.setAcceleration(2);
        player.setDeacceleration(1);

        player.setRadians(3.1415f / 2);
        player.setRotationSpeed(3);
        world.addEntity(player);
        
        player.setMoveState(MovementState.STANDINGRIGHT);
        player.setCharState(CharacterState.IDLE);
        
        
        
        
    }

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

    

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }

}
