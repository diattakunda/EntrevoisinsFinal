package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();
    List<Neighbour> getFavoritesNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);
    void deleteFavoritesNeighbours(Neighbour neighbour);

    /**
     * Get position of a Neighbour.
     * @param position
     */
    Neighbour getPositionNeighbour(int position);



    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);
    void createFavoritesNeighbours(Neighbour favoriteNeighbour);
}
