/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import com.jme3.util.TangentBinormalGenerator;

/**
 *
 * @author db-141205
 */
public class SpaceShip extends Node {

    private SpaceShipController spaceshipcontroller;
    private Spatial spatial;
    private AssetManager assetManager;
    private Node clickAbles;

    SpaceShip() {
        this.clickAbles = Main.getMain().getClickAbles();
        this.assetManager = Main.assetManagerStatic;
        spaceshipcontroller = new SpaceShipController();

        spatial = assetManager.loadModel("Models/ship4/scene.obj");

        /*Material cube1Mat = new Material(assetManager,
         "Common/MatDefs/Misc/Unshaded.j3md");
         Texture cube1Tex = assetManager.loadTexture(
         "Models/Fighter/SciFi_FighterMK_diffuse.jpg");
         cube1Mat.setTexture("ColorMap", cube1Tex);
         spatial.setMaterial(cube1Mat);*/
        spatial.setLocalScale(0.001f);

        spatial.setUserData("ID", "fuckyou");
        spatial.addControl(spaceshipcontroller);
        this.attachChild(spatial);
        clickAbles.attachChild(this);
    }

    public SpaceShipController getSpaceShipController() {
        return spaceshipcontroller;
    }

    public Spatial getSpatial() {
        return spatial;
    }
}
