package dk.sdu.mmmi.cbse.roundsystem;

import com.badlogic.gdx.Gdx;
import data.Entity;
import data.EntityType;
import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import services.IEntityProcessingService;
import services.IGamePluginService;
import States.GameState;


@ServiceProviders(value = {
    @ServiceProvider(service = IGamePluginService.class),
    @ServiceProvider(service = IEntityProcessingService.class)
})

public class RoundPlugin implements IGamePluginService, IEntityProcessingService {

    private float currentTime;
    
    @Override
    public void start(GameData gameData, World world) {
        gameData.setRoundNumber(1);
        gameData.setCurrentTime(0);
        gameData.setRoundTime(3);
    }

    @Override
    public void process(GameData gameData, World world) {
        //float dt = Gdx.graphics.getDeltaTime();
        
        //float timer;
//        timer = gameData.getRoundNumber()
//        gameData.getCurrentTime();
//        currentTime = roundTime -= dt;
//        int minutes = ((int)roundTime) / 60;
//        int seconds = ((int)roundTime) % 60;
//        if(currentTime <= 0){
//            GameState.ROUNDEND;
//            roundNumber++; 
//        }
        
    }



    @Override
    public void stop(GameData gameData, World world) {
    }

}
