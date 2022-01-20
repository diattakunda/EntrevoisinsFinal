package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {


    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }
// four methods added : getNeighbourByPosition, getNeighbourFavorites,addNeighboursFavorites,deleteNeighboursFavorites

    @Test
    public void getNeighbourbyPosition() {
        int position = 0;
        Neighbour expectedNeighbour = service.getPositionNeighbour(position);
        Neighbour neighbour = service.getNeighbours().get(position);
        assertEquals(expectedNeighbour.getName(), neighbour.getName());
    }

    @Test
    public void getNeighbourFavoritesWithSuccess() {
        List<Neighbour> expectedFavoritesNeighbour = service.getFavoritesNeighbours();
        assertTrue(expectedFavoritesNeighbour.isEmpty());
    }

    @Test
    public void addNeighboursFavoritesWithSucess() {
        int position = 10;
        Neighbour neighbour = service.getNeighbours().get(position);
        service.createFavoritesNeighbours(neighbour);
        assertFalse(service.getFavoritesNeighbours().isEmpty());
    }

    @Test

    public void deleteNeighbourFavorites() {
        int position = 9;
        Neighbour neighbour = service.getNeighbours().get(position);
        service.createFavoritesNeighbours(neighbour);
        service.deleteFavoritesNeighbours(neighbour);
        assertTrue(service.getFavoritesNeighbours().isEmpty());
    }

}