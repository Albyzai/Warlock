package gameengine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import data.Entity;
import data.EntityType;
import data.GameData;
import data.World;
import data.componentdata.Currency;

public class HUD {

    private float health;
    private Integer gold;
    private float roundTimer;
    private Integer roundNumb;
    private Integer exp;
    private Integer level;

    private Label goldLabel;
    private Label roundTimerLabel;
    private Label roundNumbLabel;
    private Label expLabel;
    private Label levelLabel;
    private Table table;

    public HUD(Stage stage, FitViewport viewPort, GameData gameData, World world) {
        roundTimer = gameData.getRoundTime();
        roundNumb = gameData.getRoundNumber();

        for (Entity player : world.getEntities(EntityType.PLAYER)) {
            gold = player.get(Currency.class).getGold();
            health = player.getHealth();
            exp = player.getExpPoints();
            level = player.getLevel();
        }

        table = new Table();
        table.top();
        table.setFillParent(true);

        goldLabel = new Label("GOLD", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        //roundTimerLabel = new Label(String.format("%04d", roundTimer), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        roundTimerLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        roundNumbLabel = new Label("ROUND", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        levelLabel = new Label("LEVEL", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        expLabel = new Label("EXP", new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        table.add(levelLabel).expandX().padTop(10);
        table.add(expLabel).expandX().padTop(10);
        table.add(goldLabel).expandX().padTop(10);
        table.add(roundNumbLabel).expandX().padTop(10);
        table.add(roundTimerLabel).expandX().padTop(10);

        stage.addActor(table);
    }


}
