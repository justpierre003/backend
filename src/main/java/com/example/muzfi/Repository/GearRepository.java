package com.example.muzfi.Repository;

import com.example.muzfi.Model.Gear;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GearRepository extends MongoRepository<Gear, String> {
    // You can define custom queries here if needed
    List<Gear> findByNameContaining(String name);

    List<Gear> findByNameContainingAndPostCategory(String name, String category);

    List<Gear> findByNameContainingIgnoreCase(String query);

    Optional<Gear> findByName(String name);

    Optional<Gear> findById(String id);

    List<Gear> findByPostCategoryContainingIgnoreCase(String query);

    List<Gear> findTopByOrderByCreatedDateTimeDesc(int limit);
}
