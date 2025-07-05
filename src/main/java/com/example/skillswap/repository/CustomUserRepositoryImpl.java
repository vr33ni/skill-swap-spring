package com.example.skillswap.repository;

import com.example.skillswap.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findNearbyUsers(Point point, double radiusKm) {
        String hql = """
            SELECT u FROM User u
            WHERE ST_DWithin(
                u.location, :point, :radius
            ) = true
        """;

        // PostGIS wants meters, radius_km is in km:
        double radiusMeters = radiusKm * 1000;

        return em.createQuery(hql, User.class)
                .setParameter("point", point)
                .setParameter("radius", radiusMeters)
                .getResultList();
    }
}
