package src.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import src.main.models.Movie;
import src.main.models.Store;

/**
 * Test Cases
 * - Adding a movie
 * 1. Test if the store contains the movie that was just added.
 * - Selling a movie
 * 2. Test if the movie that was sold was removed from the store.
 * 3. Test for an IllegalStateException for selling a rented movie.
 * - Renting a movie
 * 4. Test if the movie becomes rented.
 * - Returning a movie
 * 5. Test if the movie becomes available.
 */
public class StoreTest {

    Store store;
    Movie movie01 = new Movie("Shawshank", "Blue-Ray", 9.2);
    Movie movie02 = new Movie("The Godfather", "Blue-Ray", 4.0);

    @Before
    public void setup() {
        store = new Store();
        store.addMovie(new Movie(movie01));
        store.addMovie(new Movie(movie02));

    }

    /**
     * Add a movie
     * User is able to add a movie to the movies collection
     */
    @Test
    public void movieAddedTest() {
        assertTrue(store.contains(new Movie(movie01)));
    }

    /**
     * Selling a movie
     */
    @Test
    public void sellMovieTest() {
        store.sellMovie(movie02.getName());
        assertFalse(store.contains(new Movie(movie02)));
    }

    /**
     * Test for an IllegalStateException for selling a rented movie.
     */
    @Test
    public void rentMovieTest() {

        store.rentMovie(movie02.getName());
        assertFalse(store.getMovie(1).isAvailable());
    }

    /**
     * Test for an IllegalStateException for selling a rented movie.
     */
    @Test(expected = IllegalStateException.class)
    public void sellUnavailableMovieTest() {
        store.rentMovie(movie01.getName());
        store.sellMovie(movie01.getName());
    }
}
