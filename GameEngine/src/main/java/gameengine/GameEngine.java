/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameengine;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import data.Entity;
import static data.EntityType.PLAYER;
import data.GameData;
import static data.GameKeys.*;
import data.World;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import managers.AssetsJarFileResolver;
import managers.GameInputProcessor;
import org.openide.util.Lookup;
import services.IEntityProcessingService;
import services.IGamePluginService;
import services.MapSPI;

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
    private Lookup.Result<MapSPI> mapResult;
    private List<MapSPI> maps;
    private TiledMap map;
    private IsometricTiledMapRenderer renderer;
    public  DotaCamera camera;
    private AssetManager assetManager;
    private MapLayers mapLayers, groundLayers;
    private float shrinkTimer, shrinkTime;
    private int layerCount;
    private ShapeRenderer sr;

    @Override
    public void create() {
        world = new World();
        gameData = new GameData();
        maps = new CopyOnWriteArrayList<>();
        shrinkTime = 15;
        AssetsJarFileResolver jfhr = new AssetsJarFileResolver();
        assetManager = new AssetManager(jfhr);
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        
        pluginResult = lookup.lookupResult(IGamePluginService.class);
        processorResult = lookup.lookupResult(IEntityProcessingService.class);
        mapResult = lookup.lookupResult(MapSPI.class);
        sr = new ShapeRenderer();
        

        assetManager.load("assets/shrinkingmap.tmx", TiledMap.class);
        assetManager.finishLoading();
        map = assetManager.get("assets/shrinkingmap.tmx");

        Gdx.input.setInputProcessor(new GameInputProcessor(gameData));
        renderer = new IsometricTiledMapRenderer(map);
        camera = new DotaCamera();
        processors = new CopyOnWriteArrayList<>();
        entityPlugins = new CopyOnWriteArrayList<>();
        
        mapLayers = map.getLayers();
        groundLayers = new MapLayers();
        
        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth/2 + 900, camera.viewportHeight/2, 0);

        for (int i = 2; i < mapLayers.getCount(); i++) {
            mapLayers.get(i).setVisible(false);
            groundLayers.add(mapLayers.get(i));
        }

        for (IGamePluginService plugin : pluginResult.allInstances()) {
            plugin.start(gameData, world);
            entityPlugins.add(plugin);
        }

        for (IEntityProcessingService processor : processorResult.allInstances()) {
            processors.add(processor);
        }
        
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void render() {
        gameData.setDelta(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
        renderer.setView(camera);
        renderer.render();
        camera.updateAndMove();
        update();
        draw();
    }

    private void draw() {

        for (Entity entity : world.getEntities()) {
            float[] shapex = entity.getShapeX();
            float[] shapey = entity.getShapeY();
            if (shapex != null && shapey != null) {

                sr.setColor(1, 1, 1, 1);

                sr.begin(ShapeRenderer.ShapeType.Line);

                for (int i = 0, j = shapex.length - 1;
                        i < shapex.length;
                        j = i++) {

                    sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
                }
                sr.end();
            }
        }

    }

    private void mapShrink(int layerCount)
    {

        mapLayers.get(1).setVisible(false);
        for (int i = 0; i < groundLayers.getCount(); i++) {
            if (layerCount == i + 1 && i != 4) {
                groundLayers.get(i).setVisible(true);
            } else {
                groundLayers.get(i).setVisible(false);
            }

        }
    }
    private void update() {

        
        shrinkTimer += gameData.getDelta();
        if (shrinkTimer >= shrinkTime) {
            layerCount ++;
            if(layerCount >= groundLayers.getCount()){
                layerCount--;
            }
            mapShrink(layerCount);
            shrinkTimer = 0;
        }
//        for (MapSPI map : lookup.lookupAll(MapSPI.class)) {
//            map.processMap(world, gameData);
//        }
        for (IEntityProcessingService processor : processors) {
            processor.process(gameData, world);
        }
        
        for(Entity e : world.getEntities()){
            if(e.getType() == PLAYER){
//                camera.position.x = e.getX();
//                camera.position.y = e.getY();
//                camera.update();
//                System.out.println(sr.getProjectionMatrix());
            }
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
    }

    
    

}

