/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygame.gamestates;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector3f;
import com.jme3.ui.Picture;
import java.util.ArrayList;
import com.mygame.GodrayLight;
import com.mygame.SpaceStation;

/**
 *
 * @author io
 */
public class Intro extends GameState {

    private Picture part1;
    private Picture part2;
    private Picture part3;
    private Picture part4;
    private Picture part5;
    private Picture part6;

    public Intro() {
        super();

    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);

        part1 = new Picture("HUD Picture 1");
        part1.setImage(assetManager, "Textures/intro.jpg", true);
        part1.setWidth(200);
        part1.setHeight(20);
        part1.move((settings.getWidth() / 2) - 100, (settings.getHeight() / 2) - 10, 0);

        part2 = new Picture("HUD Picture 2");
        part2.setImage(assetManager, "Textures/intro.jpg", true);
        part2.setWidth(20);
        part2.setHeight(200);
        part2.move((settings.getWidth() / 2) - 10, (settings.getHeight() / 2) - 100, 0);

        part2 = new Picture("HUD Picture 2");
        part2.setImage(assetManager, "Textures/intro.jpg", true);
        part2.setWidth(20);
        part2.setHeight(110);
        part2.move((settings.getWidth() / 2) - 10, (settings.getHeight() / 2) - 100, 0);

    }

    @Override
    public void update(float tpf) {

        guiNode.attachChild(part1);
        guiNode.attachChild(part2);
        guiNode.attachChild(part3);
    }
}
