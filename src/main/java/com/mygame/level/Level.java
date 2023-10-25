/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygame.level;

import com.jme3.math.Vector3f;
import java.util.ArrayList;

/**
 *
 * @author io
 */
public abstract class Level {

    //non occupied stars
    protected ArrayList<Vector3f> stars;
    //npc bases
    protected ArrayList<Vector3f> npcs;
    //player bases (with star)
    protected ArrayList<Vector3f> player;

    Level() {
        stars = new ArrayList<Vector3f>();
        npcs = new ArrayList<Vector3f>();
        player = new ArrayList<Vector3f>();
    }

    /**
     * @return the stars
     */
    public ArrayList<Vector3f> getStars() {
        return stars;
    }

    /**
     * @param stars the stars to set
     */
    public void setStars(ArrayList<Vector3f> stars) {
        this.stars = stars;
    }

    /**
     * @return the npcs
     */
    public ArrayList<Vector3f> getNpcs() {
        return npcs;
    }

    /**
     * @param npcs the npcs to set
     */
    public void setNpcs(ArrayList<Vector3f> npcs) {
        this.npcs = npcs;
    }

    /**
     * @return the player
     */
    public ArrayList<Vector3f> getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(ArrayList<Vector3f> player) {
        this.player = player;
    }
}
