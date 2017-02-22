package com.company;

import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * Created by Lambros on 21/02/2017.
 */
public class Robot {

    private Coordinate coordinates;
    private boolean isAwake;

    public Robot(Coordinate coordinates, boolean state) {
        this.coordinates = coordinates;
        this.isAwake = state;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isAwake() {
        return isAwake;
    }

    public void setAwake(boolean awake) {
        isAwake = awake;
    }

}
