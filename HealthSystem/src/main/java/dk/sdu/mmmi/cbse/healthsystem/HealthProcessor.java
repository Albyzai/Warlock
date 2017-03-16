package dk.sdu.mmmi.cbse.healthsystem;

import data.Entity;
import data.GameData;
import data.World;
import java.util.ArrayList;
import org.openide.util.lookup.ServiceProvider;
import services.IEntityProcessingService;



@ServiceProvider(service = IEntityProcessingService.class)
public class HealthProcessor implements IEntityProcessingService {

    @Override
    public void process(GameData data, World world) {
        for (Entity e : world.getEntities()) {
            int health = e.getHealth();
            ArrayList<Integer> damageTaken = e.getDamageTaken();
            e.getDamageTaken().clear();
            if (health > 0) {
                for (int dmg : damageTaken) {
                    e.setHealth(e.getHealth() - dmg);

                }

            }
            if (e.getHealth() <= 0) {
                world.removeEntity(e);
            }
        }
    }

}
