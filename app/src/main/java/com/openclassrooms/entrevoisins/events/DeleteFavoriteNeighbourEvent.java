package com.openclassrooms.entrevoisins.events;


import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user deletes a Neighbour
 */

public class DeleteFavoriteNeighbourEvent {
    /**
     * Neighbour to delete
     */
    public Neighbour favoriteNeighbour;
    public int fragPosition;

    /**
     * Constructor.
     * @param favoriteNeighbour
     */
    public DeleteFavoriteNeighbourEvent(Neighbour favoriteNeighbour, int fragPosition) {
        this.favoriteNeighbour = favoriteNeighbour;
        this.fragPosition=fragPosition;
    }

}
