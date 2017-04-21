package hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sun.prism.image.ViewPort;
import data.Entity;
import data.EntityType;
import data.GameData;
import data.World;
import services.IEntityProcessingService;
import services.IGamePluginService;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;


@ServiceProviders(value = {
    @ServiceProvider(service = IGamePluginService.class),
    @ServiceProvider(service = IEntityProcessingService.class)
})
public class HUD implements IGamePluginService, IEntityProcessingService {
    private Stage stage;
    private FitViewport viewPort;
    
    private float health;
    private int gold;
    private float roundTimer;
    private int roundNumb;
    private int exp;
    
    private Label goldLabel;
    private Label roundTimerLabel;
    private Label roundNumbLabel;

    private SpriteBatch sb;
    private Table table;

    @Override
    public void start(GameData gameData, World world) {
        sb = new SpriteBatch();
        roundTimer = gameData.getRoundTime();
        roundNumb = gameData.getRoundNumber();
        
        for(Entity player : world.getEntities(EntityType.PLAYER)){
            gold = player.getGold();
            health = player.getHealth();
            exp = player.getExpPoints();
        }
        
        viewPort = new FitViewport(gameData.getScreenX(), gameData.getScreenY(), new OrthographicCamera());
        stage = new Stage(viewPort, sb);
        
        table = new Table();
        table.top();
        table.setFillParent(true);
        
        goldLabel = new Label("GOLD", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        roundTimerLabel = new Label(String.format("%04d", roundTimer), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        roundNumbLabel = new Label("ROUND", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        
        table.add(roundNumbLabel).expandX().padTop(10);
        table.add(roundTimerLabel).expandX().padTop(10);
        table.add(goldLabel).expandX().padTop(10);
        
        stage.addActor(table);
    }

    @Override
    public void stop() {
        table.clear();
        sb.dispose();
    }

    @Override
    public void process(GameData gameData, World world) {
        sb.setProjectionMatrix(stage.getCamera().combined);
        sb.begin();
        stage.draw();
        sb.end();
    }
}
