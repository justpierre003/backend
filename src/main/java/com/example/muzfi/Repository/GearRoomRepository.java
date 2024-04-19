package com.example.muzfi.Repository;

import com.example.muzfi.Model.GearRoom;
import com.example.muzfi.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GearRoomRepository extends MongoRepository<GearRoom, String> {
    List<GearRoom> findAllByUserId(String userId);

    Optional<GearRoom> findByUserId(String id);

    List<GearRoom> findAllByOrderByCreatedDateDesc();

}
