package com.example.muzfi.Services;

import com.example.muzfi.Model.Community;
import com.example.muzfi.Model.Gear;
import com.example.muzfi.Model.GearRoom;
import com.example.muzfi.Model.User;
import com.example.muzfi.Repository.GearRoomRepository;
import com.example.muzfi.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GearRoomServiceImpl implements GearRoomService {

    private final GearRoomRepository gearRoomRepository;

    @Autowired
    public GearRoomServiceImpl(GearRoomRepository gearRoomRepository) {
        this.gearRoomRepository = gearRoomRepository;
    }

    @Override
    public Optional<List<GearRoom>> getAllGearRooms() {
        return Optional.of(gearRoomRepository.findAll());
    }

    @Override
    public Optional<GearRoom> getGearRoomById(String gearRoomId) {
        return gearRoomRepository.findById(gearRoomId);
    }

    @Override
    public Optional<GearRoom> getGearRoomsByUserId(String userId) {
        return gearRoomRepository.findByUserId(userId);
    }

    @Override
    public GearRoom createGearRoom(GearRoom gearRoom) {
        return gearRoomRepository.save(gearRoom);
    }

    @Override
    public GearRoom updateGearRoom(String gearRoomId, GearRoom updatedGearRoom) {
        GearRoom existingGearRoom = gearRoomRepository.findById(gearRoomId).orElse(null);
        if (existingGearRoom != null) {
            existingGearRoom.setGearIds(updatedGearRoom.getGearIds());
            // More updates as needed

            return gearRoomRepository.save(existingGearRoom);
        }
        return null;
    }

    @Override
    public void deleteGearRoom(String gearRoomId) {
        gearRoomRepository.deleteById(gearRoomId);
    }


    @Override
    public List<GearRoom> getLatestGearRooms() {
        // Fetching the latest gear rooms ordered by creation date
        return gearRoomRepository.findAllByOrderByCreatedDateDesc();
    }

    @Override
    public void addGear(Gear gearName) {
        Optional<GearRoom> gearRoom = gearRoomRepository.findByUserId(gearName.getAuthorId());
        if (gearRoom.get() == null) {
            throw new NotFoundException("GearRoom not found ");
        }
        List<Gear> gearList;
        if (gearRoom.get().getGears() == null){
            gearList = new ArrayList<>();
        }else{
            gearList = gearRoom.get().getGears();
        }
        gearList.add(gearName);
        gearRoom.get().setGears(gearList);
        gearRoomRepository.save(gearRoom.get());
    }

    @Override
    public void removeGear(String gearName, String userId) {
        Optional<GearRoom> gearRoom = gearRoomRepository.findByUserId(userId);
        if (gearRoom.get() == null) {
            throw new NotFoundException("GearRoom not found");
        }
        List<Gear> gearList = gearRoom.get().getGears();
        gearList.remove(gearName);
        gearRoom.get().setGears(gearList);
        gearRoomRepository.save(gearRoom.get());
    }
}
