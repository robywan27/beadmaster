package project.beadmaster.model;

import java.util.Arrays;

import project.beadmaster.utils.Utility;


/**
 * This class represents a bar entity composed of nine slots, which are either filled or holed.
 */
public class Bar {
    /**
     * The position of the bar with respect to the grid, which can be one among inner, central and outer.
     */
    private BarPosition position;
    /**
     * An array of booleans, since each slot can have one of two mutually exclusive properties: being filled or holed.
     * The convention adopted here is that of assigning the value true for a filled slot, and false for a holed one.
     * It's a final attribute, since a given bar maintains its configuration of slots forever; therefore, it must be constant.
     */
    private final boolean[] slots;


    /** Constructor */
    public Bar(boolean[] slots) {
        this.slots = slots;
    }


    /** Getter and setter methods */
    public BarPosition getPosition() {
        return position;
    }
    public void setPosition(BarPosition position) {
        this.position = position;
    }
    public boolean[] getSlots() {
        return slots;
    }
    /**
     * This getter method returns only the seven slots positioned in the grid. It is necessary to check the position
     * of the bar with respect to the grid, so as to return the correct slots accordingly.
     * @return an array of booleans with the seven slots positioned in the grid.
     */
    public boolean[] getBarInGrid() {
        if (position == BarPosition.INNER)
            return Arrays.copyOfRange(slots, 0, 7);
        else if (position == BarPosition.CENTRAL)
            return Arrays.copyOfRange(slots, 1, 8);
        else //if (position == BarPosition.OUTER)
            return Arrays.copyOfRange(slots, 2, 9);
    }

    /**
     * This method provides a string representation of the state of a bar.
     */
    public String toString() {
        String barRepresentation = "BAR:\nPosition of bar: " + position;
        barRepresentation += "\nSlots in the grid: ";
        boolean[] barInGrid = this.getBarInGrid();
        for (int i = 0; i < Utility.GRID_SIDE; i++)
            barRepresentation += barInGrid[i] + " ";
        barRepresentation += "\n";
        return barRepresentation;
    }
}
