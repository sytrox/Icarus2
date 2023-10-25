/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Torus;
import com.jme3.texture.Texture;

/**
 *
 * @author db-141205
 */
public class SpaceStation extends Node {

    private Geometry geometry;
    private Torus torus;
    private AssetManager assetManager;
    private SpaceStationController spaceStationController;

    public SpaceStation() {
        this.assetManager = Main.assetManagerStatic;
        torus = new Torus(100, 100, 0.2f, 1.5f);
        geometry = new Geometry("A shape", torus); // wrap shape into geometry
        torus.scaleTextureCoordinates(new Vector2f(0.5f, .5f));
        Material mat = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
        Texture tex = assetManager.loadTexture(
                "Textures/steel.jpg");
        tex.setWrap(Texture.WrapMode.Repeat);
        mat.setTexture("ColorMap", tex);
        geometry.setMaterial(mat);                         // assign material to geometry
        this.attachChild(geometry);
        Main.getMain().getRootNode().attachChild(this);
        spaceStationController = new SpaceStationController();
        this.addControl(spaceStationController);
    }

    /**
     * @return the spaceStationController
     */
    public SpaceStationController getSpaceStationController() {
        return spaceStationController;
    }
}
