package facades;

import utils.EMF_Creator;
import entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;
    private static String[] actors1 = {"John", "Mogens", "Carl"};
    private static String[] actors2 = {"Henning", "Lola", "Kim"};
    private static Movie movie1 = new Movie(2013, "BraveHeart", actors1);
    private static Movie movie2 = new Movie(1999, "Vil med John", actors2);
    private static Movie movie3 = new Movie(1994, "Danser med Drenge", actors2);
    public MovieFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = MovieFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.persist(movie1);
            em.persist(movie2);
            em.persist(movie3);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testAFacadeMethod() {
        assertEquals(3, facade.getMovieCount(), "Expects 3 rows in the database");
    }
    
    @Test
    public void testGetallmovies(){
        
        List<Movie> movielist = facade.getAllMovies();
        assertEquals(3, movielist.size());
        
    }
    @Test
    public void testgetMovieById (){
        
        long movieID = movie1.getId();
        Movie m = facade.getMovieById(movieID);
        assertEquals(movie1.getYear(), m.getYear());
        
    }
    @Test
    public void testMovieByYear(){
        
        int year = movie1.getYear();
        List<Movie> movielist = facade.getMoviesByYear(year);
        assertEquals(year, movielist.get(0).getYear());
    }
    
    @Test
    public void testAddmovie(){
        
        int year = 1992;
        String title = "Titanic";
        String[] actors = {"Johnny Madsen", "Madonna"};
        
        facade.addMovie(year, title, actors);
        assertEquals(4, facade.getMovieCount());
        
    }
    @Test
    public void testGetMovieByTitle(){
        
        String title = movie1.getTitle();
        
        assertEquals(title, facade.getMovieByTitle(title).getTitle());
        
    }

}
