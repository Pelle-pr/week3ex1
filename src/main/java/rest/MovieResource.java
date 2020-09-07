package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.DTOmovie;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movie")
public class MovieResource {
    
    

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final MovieFacade FACADE =  MovieFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
            String[] actors1 = {"John", "Benny", "Carl"};
     String[] actors2 = {"Lonny", "Hanne", "Jørgen"};
  
     EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
     
     EntityManager em = EMF.createEntityManager();
     
     em.getTransaction().begin();
     
     em.persist(new Movie(2010, "John alene i verden",actors1));
     em.persist(new Movie(1999, "Lenny er sej", actors2));
     em.persist(new Movie(1945, "Rose", actors2));
     em.persist(new Movie(1955, "Johnny", actors2));
     em.persist(new Movie(2011, "Skraldemanden", actors2));
     
     em.getTransaction().commit();
     em.close();
    
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieMeCount() {
        long count = FACADE.getMovieCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    @Path("allmovies")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMovies (){
        
        List<DTOmovie> dtolist = new ArrayList<>();
        List<Movie> movielist = FACADE.getAllMovies();
        
        for (Movie movie : movielist) {
            dtolist.add(new DTOmovie(movie));
            
        }
        
        return GSON.toJson(dtolist);
    }
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMovieByID(@PathParam("id") Long id ){
        
      
        DTOmovie m = new DTOmovie(FACADE.getMovieById(id));
        
        return GSON.toJson(m);
        
    }
    
}
