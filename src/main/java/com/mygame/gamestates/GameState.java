package com.mygame.gamestates;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;



import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import com.jme3.system.AppSettings;
import com.mygame.Main;
import com.mygame.common.Log;


public abstract class GameState extends AbstractAppState {

    protected RenderManager renderManager;
    protected Camera cam;
    protected Main main;
    protected Node rootNode;
    protected Node guiNode;
    protected AssetManager assetManager;
    protected AppStateManager stateManager;
    protected InputManager inputManager;
    protected ViewPort viewPort;
    protected AppSettings settings;
    private List<Spatial> guiNodeSpatials = new ArrayList<>();
    private List<Control> guiNodeControls = new ArrayList<>();

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        main = (Main) app;
        rootNode = main.getRootNode();
        assetManager = main.getAssetManager();
        this.stateManager = main.getStateManager();
        inputManager = main.getInputManager();
        viewPort = main.getViewPort();
        guiNode = main.getGuiNode();

        cam = main.getCamera();
        renderManager = main.getRenderManager();
        settings = main.getSettings();
    }



    public void changeState(GameState newState) {
        Log.debug("Changing state from '%s' to '%s'", this.getClass().getSimpleName(), newState.getClass().getSimpleName());
        stateManager.attach(newState);
        stateManager.detach(this);
    }

    @Override
    public void cleanup() {
        super.cleanup();
        Log.debug("Cleaning scene");

        for (int i = 0; i < guiNodeSpatials.size(); i++) {
            guiNode.detachChild(guiNodeSpatials.get(i));
        }

        for (int i = 0; i < guiNodeControls.size(); i++) {
            guiNode.removeControl(guiNodeControls.get(i));
        }

    }

    public void guiNodeAttachChild(Spatial spatial) {
        guiNode.attachChild(spatial);
        guiNodeSpatials.add(spatial);
    }

    public void guiNodeDetachChild(Spatial spatial) {
        guiNode.detachChild(spatial);
        guiNodeSpatials.remove(spatial);
    }

    public void guiNodeAddControl(Control control) {
        guiNode.addControl(control);
        guiNodeControls.add(control);
    }

    public void guiNodeRemoveControl(Control control) {
        guiNode.removeControl(control);
        guiNodeControls.remove(control);
    }
}
