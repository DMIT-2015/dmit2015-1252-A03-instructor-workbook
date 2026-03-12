package dmit2015.repository;

import dmit2015.entity.Movie;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.security.enterprise.SecurityContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MovieRepository {

    @Inject
    private SecurityContext securityContext;
    
    @PersistenceContext (unitName = "mssql-dmit2015-jpa-pu") // unitName is optional if persistence.xml contains only one persistence-unit
    private EntityManager em;

    @Transactional
    public void add(Movie newMovie) {
        String username = securityContext.getCallerPrincipal().getName();
        if (username.equalsIgnoreCase("anonymous")) {
            throw new RuntimeException("Access denied. You must be authenticated to perform this operation.");
        }
        newMovie.setUsername(username);
        em.persist(newMovie);
    }

    @Transactional
    public void update(Movie updatedMovie) {
        Optional<Movie> maybeMovie = findOptionalById(updatedMovie.getId());
        if (maybeMovie.isPresent()) {
            Movie existingMovie = maybeMovie.orElseThrow();
            existingMovie.setTitle(updatedMovie.getTitle());
            existingMovie.setGenre(updatedMovie.getGenre());
            existingMovie.setPrice(updatedMovie.getPrice());
            existingMovie.setRating(updatedMovie.getRating());
            existingMovie.setReleaseDate(updatedMovie.getReleaseDate());
            em.merge(existingMovie);
        }
    }

    @Transactional
    public void delete(Movie existingMovie) {
        if (!em.contains(existingMovie)) {
            existingMovie = em.merge(existingMovie);
        }
        em.remove(existingMovie);
    }

    @Transactional
    public void deleteById(Long id) {
        Optional<Movie> maybeMovie = findOptionalById(id);
        if (maybeMovie.isPresent()) {
            Movie existingMovie = maybeMovie.orElseThrow();
            em.remove(existingMovie);
        }
    }

    public Movie findById(Long id) {
        return em.find(Movie.class, id);
    }

    public Optional<Movie> findOptionalById(Long id) {
        try {
            Movie querySingleResult = findById(id);
            return Optional.of(querySingleResult);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    public List<Movie> findAll() {
        String username = securityContext.getCallerPrincipal().getName();
        if (username.equalsIgnoreCase("anonymous")) {
            throw new RuntimeException("Access denied. You must be authenticated to perform this operation.");
        }
        boolean hasRequiredRoles = securityContext.isCallerInRole("Sales") || securityContext.isCallerInRole("Shipping");
        if (!hasRequiredRoles) {
            throw new RuntimeException("Access denied. You do have permission to perform this operation.");
        }
        return em.createQuery("SELECT m FROM Movie m WHERE m.username = :usernameValue", Movie.class)
                .setParameter("usernameValue", username)
                .getResultList();
    }

    public List<Movie> findAllOrderByTitle() {
        return em.createQuery("SELECT m FROM Movie m ORDER BY m.title", Movie.class)
                .getResultList();
    }

    public long count() {
        return em.createQuery("SELECT COUNT(m) FROM Movie m", Long.class).getSingleResult().longValue();
    }

    @Transactional
    public void deleteAll() {
        em.createQuery("DELETE FROM Movie").executeUpdate();
    }

}

