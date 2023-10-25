/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygame;

import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.shape.Quad;
import com.jme3.system.AppSettings;

/**
 *
 * @author io
 */
public class GodrayLightController extends AbstractControl {

    private float tpfCounter;
    private Node guiNode;
    private Geometry quad;
    private AppSettings settings;
    private Camera cam;
    private Material lavaMaterial;
    private Material godrayMatarial;

    GodrayLightController(Node guiNode, AppSettings appsettings, Camera cam, Material godrayMatarial, Material lavaMaterial) {
        this.guiNode = guiNode;
        this.settings = appsettings;
        this.cam = cam;
        this.godrayMatarial = godrayMatarial;
        this.lavaMaterial = lavaMaterial;

        quad = new Geometry("box", new Quad(settings.getWidth(), settings.getHeight()));
        quad.setMaterial(godrayMatarial);
        quad.setLocalTranslation(new Vector3f(0, 0, -1000));
    }

    @Override
    protected void controlUpdate(float tpf) {
        Vector2f pos = new Vector2f();
        pos.x = cam.getScreenCoordinates(this.spatial.getLocalTranslation()).x;
        pos.y = cam.getScreenCoordinates(this.spatial.getLocalTranslation()).y;
        pos.x = ((pos.x + 1) / settings.getWidth());
        pos.y = ((pos.y + 1) / settings.getHeight());
        godrayMatarial.setVector2("lightPositionOnScreen", pos);
        tpfCounter += tpf;
        lavaMaterial.setFloat("time", tpfCounter);
        if (pos.x < 0 || pos.x > 1 || pos.y < 0 || pos.x > 1) {//außerhalb der view (spart renderzeit)
            quad.removeFromParent();
        } else {
            guiNode.attachChild(quad);
        }
        this.spatial.rotate(tpf, tpf, tpf);
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
}
