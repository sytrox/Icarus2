/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygame;

import com.jme3.asset.AssetManager;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.math.Vector4f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Quad;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;
import com.jme3.texture.FrameBuffer;
import com.jme3.texture.Image;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.mygame.common.Log;

/**
 *
 * @author io
 */
public class GodrayLight extends Node {

    private Node rootNode;
    private Node guiNode;
    private ViewPort viewPort;
    private Texture offTex;
    private Sphere sphere;
    private Geometry geometry;
    private Material lavaMaterial;
    private Material godrayMatarial;
    private float tpfCounter;
    private AppSettings settings;
    private Camera cam;
    private AssetManager assetManager;
    private Geometry quad;
    private GodrayLightController godrayLightController;

    public GodrayLight(AppSettings settings, Camera cam, AssetManager assetManager, Node rootNode, Node guiNode) {

        this.settings = settings;
        this.cam = cam;
        this.assetManager = assetManager;
        this.rootNode = rootNode;
        this.guiNode = guiNode;
        sphere = new Sphere(20, 20, 1);
        geometry = new Geometry("Sphere", sphere);
        offTex = setupOffscreenView();



        lavaMaterial = new Material(assetManager, "MatDefs/lava.j3md");
        Texture tex1 = assetManager.loadTexture("Textures/cloud.png");
        tex1.setWrap(Texture.WrapMode.Repeat);
        lavaMaterial.setTexture("texture1", tex1);
        Texture tex2 = assetManager.loadTexture("Textures/lavatile.jpg");
        tex2.setWrap(Texture.WrapMode.Repeat);
        lavaMaterial.setTexture("texture2", tex2);
        lavaMaterial.setVector2("uvScale", new Vector2f(1.0f, 1.0f));
        lavaMaterial.setVector2("resolution", new Vector2f(settings.getWidth(), settings.getHeight()));
        lavaMaterial.setFloat("time", 1.0f);
        lavaMaterial.setFloat("fogDensity", 0.0f);
        lavaMaterial.setVector3("fogColor", new Vector3f(0, 0, 0));
        lavaMaterial.setVector4("test", new Vector4f(1, 0, 0, 1));
        //mat2.getAdditionalRenderState().setFaceCullMode(RenderState.FaceCullMode.Off); // turn culling off
        //mat2.preload(renderManager);
        geometry.setMaterial(lavaMaterial);
        this.attachChild(geometry);

        godrayMatarial = new Material(assetManager, "MatDefs/godray.j3md");
        godrayMatarial.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Additive);
        godrayMatarial.setTexture("firstPass", offTex);
        godrayMatarial.setFloat("exposure", 0.0094f);
        godrayMatarial.setFloat("decay", 0.95f);
        godrayMatarial.setFloat("density", 1.00f);
        godrayMatarial.setFloat("weight", 15.00f);

        godrayLightController = new GodrayLightController(guiNode, settings, cam, godrayMatarial, lavaMaterial);
        this.addControl(godrayLightController);
    }

    private Texture setupOffscreenView() {

        viewPort = new ViewPort("godray viewport", cam);
        //offView = getRenderManager().createPostView("Offscreen View", cam);
        getViewPort().setClearFlags(true, true, true);
        getViewPort().setBackgroundColor(ColorRGBA.Black);
        // create offscreen framebuffer
        FrameBuffer offBuffer = new FrameBuffer(settings.getWidth(), settings.getHeight(), 1);
        offBuffer.setDepthBuffer(Image.Format.Depth16);
        // setup framebuffer's texture
        Texture2D offTex = new Texture2D(settings.getWidth(), settings.getHeight(), Image.Format.RGBA16F);

        offBuffer.setColorTexture(offTex);
        // set viewport to render to offscreen framebuffer
        getViewPort().setOutputFrameBuffer(offBuffer);
        getViewPort().attachScene(rootNode);

        return offTex;
    }

    /**
     * @return the viewPort
     */
    public ViewPort getViewPort() {
        return viewPort;
    }

    /**
     * @return the geometry
     */
    public Geometry getGeometry() {
        return geometry;
    }
}
