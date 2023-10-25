/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygame;

import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;
import com.mygame.common.Log;

/**
 *
 * @author db-141205
 */
//
public class SpaceStationController extends AbstractControl {

    private float spawnCooldownCounter = 0;
    private float spawnCooldown = 5;
                SpaceShip ship = new SpaceShip();

    @Override
    protected void controlUpdate(float tpf) {
        this.spatial.rotate(tpf, tpf, tpf);
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
}
