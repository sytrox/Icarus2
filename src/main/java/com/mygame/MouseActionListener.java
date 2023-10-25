/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygame;

import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.InputManager;
import com.jme3.input.controls.ActionListener;
import com.jme3.material.RenderState;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;
import java.util.ArrayList;
import java.util.HashSet;
import com.mygame.common.Log;

/**
 *
 * @author io
 */
public class MouseActionListener extends AbstractControl implements ActionListener {

    private Node rootNode;
    private Node guiNode;
    private Camera cam;
    private Node clickAbles;
    private InputManager inputManager;
    private HashSet<SpaceShipController> shipController;
    private boolean select;
    private AssetManager assetManager;
    private final AppSettings settings;
    private Picture pic;
    private Vector3f click3d;
    private Vector3f dir;

    public MouseActionListener(Node rootNode, Node guiNode, Camera cam, Node clickAbles, InputManager inputManager, AssetManager assetManager, AppSettings settings) {
        this.rootNode = rootNode;
        this.guiNode = guiNode;
        this.cam = cam;
        this.clickAbles = clickAbles;
        this.inputManager = inputManager;
        this.assetManager = assetManager;
        this.settings = settings;
        //pic = new Picture("HUD Picture");
        //pic.setImage(assetManager, "Textures/selectBox.png", true);
//        pic.getMaterial().getAdditionalRenderState().setFaceCullMode(RenderState.FaceCullMode.Off);
        shipController = new HashSet<>();
    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if (name.equals("LeftClick") && isPressed) {
            select = true;
            pic.setPosition(inputManager.getCursorPosition().x, inputManager.getCursorPosition().y);
            guiNode.attachChild(pic);
            shipController.clear();
            Log.debug("click");
        } else if (name.equals("LeftClick") && !isPressed) {
            select = false;
            pic.removeFromParent();
        }
        if (name.equals("RightClick") && !isPressed) {
            CollisionResults results = new CollisionResults();
            Vector2f click2d = inputManager.getCursorPosition();
            click3d = cam.getWorldCoordinates(
                    new Vector2f(click2d.x, click2d.y), 0f).clone();
            dir = cam.getWorldCoordinates(
                    new Vector2f(click2d.x, click2d.y), 1f).subtractLocal(click3d).normalizeLocal();
            Ray ray = new Ray(click3d, dir);
            clickAbles.collideWith(ray, results);
            if (results.size() > 0) {
                Geometry geometry = results.getCollision(0).getGeometry();
                if (geometry.getName().equals("Sphere")) {
                    dir = dir.mult(37.0f / dir.z);
                    dir = dir.mult(-1);
                    dir = dir.add(cam.getLocation());
                    dir.z = 3.0f;
                    for (SpaceShipController controller : shipController) {
                        Log.debug(geometry.getWorldTranslation());
                        controller.setNewPosition(geometry.getWorldTranslation());
                    }
                }
            }
            Log.debug("RightClick" + dir);
        }
    }

    @Override
    protected void controlUpdate(float tpf) {
        if (select) {
            pic.setWidth(inputManager.getCursorPosition().x - pic.getLocalTranslation().x);
            pic.setHeight(inputManager.getCursorPosition().y - pic.getLocalTranslation().y);

            // 1. Reset results list.          
            CollisionResults results = new CollisionResults();


            if (pic.getLocalTranslation().x == inputManager.getCursorPosition().x && pic.getLocalTranslation().y == inputManager.getCursorPosition().y) {
                Vector2f click2d = inputManager.getCursorPosition();
                click3d = cam.getWorldCoordinates(
                        new Vector2f(click2d.x, click2d.y), 0f).clone();
                dir = cam.getWorldCoordinates(
                        new Vector2f(click2d.x, click2d.y), 1f).subtractLocal(click3d).normalizeLocal();
                Ray ray = new Ray(click3d, dir);
                clickAbles.collideWith(ray, results);
                if (results.size() > 0) {
                    Geometry geometry = results.getCollision(0).getGeometry();
                    if (geometry.getName().equals("Sphere")) {
                        Log.debug("helau!:D");
                    }
                }
            } else {
                if (pic.getLocalTranslation().x < inputManager.getCursorPosition().x) {
                    for (float x = pic.getLocalTranslation().x; x <= inputManager.getCursorPosition().x; x += 4) {
                        if (pic.getLocalTranslation().y <= inputManager.getCursorPosition().y) {
                            for (float y = pic.getLocalTranslation().y; y < inputManager.getCursorPosition().y; y += 4) {
                                click3d = cam.getWorldCoordinates(
                                        new Vector2f(x, y), 0f).clone();
                                dir = cam.getWorldCoordinates(
                                        new Vector2f(x, y), 1f).subtractLocal(click3d).normalizeLocal();
                                Ray ray = new Ray(click3d, dir);
                                clickAbles.collideWith(ray, results);
                            }
                        } else {
                            for (float y = inputManager.getCursorPosition().y; y < pic.getLocalTranslation().y; y += 4) {
                                click3d = cam.getWorldCoordinates(
                                        new Vector2f(x, y), 0f).clone();
                                dir = cam.getWorldCoordinates(
                                        new Vector2f(x, y), 1f).subtractLocal(click3d).normalizeLocal();
                                Ray ray = new Ray(click3d, dir);
                                clickAbles.collideWith(ray, results);
                            }
                        }

                    }
                } else {
                    for (float x = inputManager.getCursorPosition().x; x < pic.getLocalTranslation().x; x += 4) {
                        if (pic.getLocalTranslation().y <= inputManager.getCursorPosition().y) {
                            for (float y = pic.getLocalTranslation().y; y <= inputManager.getCursorPosition().y; y += 4) {
                                click3d = cam.getWorldCoordinates(
                                        new Vector2f(x, y), 0f).clone();
                                dir = cam.getWorldCoordinates(
                                        new Vector2f(x, y), 1f).subtractLocal(click3d).normalizeLocal();
                                Ray ray = new Ray(click3d, dir);
                                clickAbles.collideWith(ray, results);
                            }
                        } else {
                            for (float y = inputManager.getCursorPosition().y; y < pic.getLocalTranslation().y; y += 4) {
                                click3d = cam.getWorldCoordinates(
                                        new Vector2f(x, y), 0f).clone();
                                dir = cam.getWorldCoordinates(
                                        new Vector2f(x, y), 1f).subtractLocal(click3d).normalizeLocal();
                                Ray ray = new Ray(click3d, dir);
                                clickAbles.collideWith(ray, results);

                            }
                        }
                    }
                }
            }
            for (CollisionResult collisionResult : results) {
                SpaceShipController control = collisionResult.getGeometry().getControl(SpaceShipController.class);
                if (control != null) {
                    shipController.add(control);
                }
            }
        }

    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
}
