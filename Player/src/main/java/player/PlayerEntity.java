package player;

import data.Entity;
import static data.EntityType.PLAYER;
import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IGamePluginService;

@ServiceProviders(value = {
    @ServiceProvider(service = IGamePluginService.class)
    
})


public class PlayerEntity implements IGamePluginService {

    private Entity player;

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        player = createPlayerShip(gameData);
        world.addEntity(player);
    }

    private Entity createPlayerShip(GameData gameData) {

        Entity playerShip = new Entity();
        playerShip.setType(PLAYER);

        playerShip.setPosition(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);

        playerShip.setMaxSpeed(2);
        playerShip.setAcceleration(2);
        playerShip.setDeacceleration(1);

        playerShip.setRadians(3.1415f / 2);
        playerShip.setRotationSpeed(3);

        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }

}
