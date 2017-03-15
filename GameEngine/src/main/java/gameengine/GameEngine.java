/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameengine;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import data.GameData;
import static data.GameKeys.*;
import data.World;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import managers.GameInputProcessor;
import org.openide.util.Lookup;
import services.IEntityProcessingService;
import services.IGamePluginService;

/**
 *
 * @author jonaspedersen
 */
public class GameEngine implements ApplicationListener {

    private GameData gameData;
    private World world;
    private final Lookup lookup = Lookup.getDefault();
    private Lookup.Result<IGamePluginService> pluginResult;
    private List<IGamePluginService> entityPlugins;
    private Lookup.Result<IEntityProcessingService> processorResult;
    private List<IEntityProcessingService> processors;
    private TiledMap map;
    private IsometricTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    @Override
    public void create()
    {
        world = new World();
        gameData = new GameData();
        map = new TmxMapLoader().load("assets/warlockmap.tmx");
        Gdx.input.setInputProcessor(new GameInputProcessor(gameData));
        renderer = new IsometricTiledMapRenderer(map, 2f);
        camera = new OrthographicCamera();
        camera.translate(1875, 50);
        processors = new CopyOnWriteArrayList<>();
        entityPlugins = new CopyOnWriteArrayList<>();
        batch = new SpriteBatch();
        
        pluginResult = lookup.lookupResult(IGamePluginService.class);
        processorResult = lookup.lookupResult(IEntityProcessingService.class);

        for (IGamePluginService plugin : pluginResult.allInstances()) {
            plugin.start(gameData, world);
            entityPlugins.add(plugin);
            System.out.println(plugin.getClass().getName());
        }
        
        for(IEntityProcessingService processor : processorResult.allInstances()){
            processors.add(processor);
            System.out.println(processor.getClass().getName());
        }
        
    }

    @Override
    public void resize(int width, int height)
    {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(camera);
        renderer.render();
        update();
        draw();
    }
    
    private void draw()
    {
       
        
    }
    
    private void update(){
        for(IEntityProcessingService processor : processors){
            processor.process(gameData, world);
        }
        
        if(gameData.getKeys().isDown(LEFT)){
            camera.translate(10, 0);
        } 
        if (gameData.getKeys().isDown(RIGHT)){
            camera.translate(-10, 0);
        }
        
        if (gameData.getKeys().isDown(UP)){
            camera.translate(0, -10);
        }
        
        if(gameData.getKeys().isDown(DOWN)){
            camera.translate(0, 10);
        }
    }

    @Override
    public void pause()
    {
        
    }

    @Override
    public void resume()
    {
        
    }

    @Override
    public void dispose()
    {
        map.dispose();
        renderer.dispose();
    }

    

}
