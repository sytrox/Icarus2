/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygame.level;

import com.jme3.math.Vector3f;
import java.util.ArrayList;

/**
 *
 * @author db-141205
 */
public class Level1 extends Level {
//1=star 2=human-base, 3=pc-base

    public Level1() {
        super();
        stars.add(new Vector3f(7, 5, 3));
        stars.add(new Vector3f(3, -3, 3));
        stars.add(new Vector3f(-5, -5, 3));
        player.add(new Vector3f(-3, -3, 3));
    }
}
