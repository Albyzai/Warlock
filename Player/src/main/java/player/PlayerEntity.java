/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import com.badlogic.gdx.math.Vector2;
import data.Entity;
import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IEntityProcessingService;
import services.IGamePluginService;

@ServiceProviders(value = {
    @ServiceProvider(service = IGamePluginService.class)
    ,
    @ServiceProvider(service = IEntityProcessingService.class)
})

public class PlayerEntity implements IGamePluginService, IEntityProcessingService {

    private Vector2 velocity = new Vector2(0, 0);
    private float speed = 2 * 60;
    private Entity player;

    private Animator animator;

    @Override
    public void start(GameData gameData, World world) {
        player = new Entity();
        world.addEntity(player);
        player.setLife(10);
        player.setPosition(0, 0);

    }

    @Override
    public void process(GameData gameData, World world) {
        float dt = gameData.getDelta();

        if (velocity.y > speed) {
            velocity.y = speed;
        }

        if (velocity.x > speed) {
            velocity.x = speed;
        }
//
//        setX(getX() + velocity.x * dt);
//        setY(getY() + velocity.y * dt);
        if (player.getLife() <= 0) {
            world.removeEntity(player);
        }
        //animator.getSprite().setPosition(player.getX(), player.getY());
        animator.render();
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }

}
