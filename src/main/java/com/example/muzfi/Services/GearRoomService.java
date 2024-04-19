package com.example.muzfi.Services;

import com.example.muzfi.Model.Gear;
import com.example.muzfi.Model.GearRoom;

import java.util.List;
import java.util.Optional;

public interface GearRoomService {
    Optional<List<GearRoom>> getAllGearRooms();

    Optional<GearRoom> getGearRoomById(String gearRoomId);

    Optional<GearRoom> getGearRoomsByUserId(String userId);

    GearRoom createGearRoom(GearRoom gearRoom);

    GearRoom updateGearRoom(String gearRoomId, GearRoom gearRoom);

    void deleteGearRoom(String gearRoomId);

    List<GearRoom> getLatestGearRooms(); // This fetches the latest 10 gear rooms

    void addGear(Gear gearName);

    void removeGear(String gearName, String userId);
}
