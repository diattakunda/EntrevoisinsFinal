package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user deletes a Neighbour
 */
public class DeleteNeighbourEvent {

    /**
     * Neighbour to delete
     */
    public Neighbour neighbour;
    public int fragPosition;

    /**
     * Constructor.
     * @param neighbour
     */
    public DeleteNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
        this.fragPosition = fragPosition;
    }

}
