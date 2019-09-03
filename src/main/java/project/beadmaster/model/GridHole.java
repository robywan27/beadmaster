package project.beadmaster.model;


/**
 * This enumeration represents the state of one slot in the grid.
 * If the vertical bar underneath the slot is filled, this slot will be marked with the value VERTICAL.
 * If the vertical bar contains a holed slot, but the horizontal bar beneath it has a filled slot, then the slot is marked as HORIZONTAL.
 * Finally, if neither of the two conditions above hold, the slot is marked as HOLE.
 */
public enum GridHole {
    VERTICAL, HORIZONTAL, HOLE
}
