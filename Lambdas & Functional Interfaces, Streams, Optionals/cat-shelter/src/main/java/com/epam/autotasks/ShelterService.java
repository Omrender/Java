package com.epam.autotasks;

import java.time.LocalDate;
import java.util.*;

public class ShelterService {

    // Assigns a staff attendant to each cat in each room
    public void assignAttendants(List<ShelterRoom> rooms) {
        if (rooms == null || rooms.isEmpty()) {
            return;
        }

        // List of attendants from the Cat.Staff enum in a specific order
        Cat.Staff[] attendants = Cat.Staff.values();
        int attendantIndex = 0;

        // Loop through each room
        for (ShelterRoom room : rooms) {
            if (room.getCats() != null) {
                // Loop through each cat in the room
                for (Cat cat : room.getCats()) {
                    if (cat != null) {
                        // Assign the attendant in a round-robin manner
                        Cat.Staff assignedAttendant = attendants[attendantIndex];
                        cat.setAttendant(assignedAttendant);

                        // Move to the next attendant (and wrap around if necessary)
                        attendantIndex = (attendantIndex + 1) % attendants.length;
                    }
                }
            }
        }
    }

    // Returns a list of cats that need a check-up before the given date
    public List<Cat> getCheckUpList(List<ShelterRoom> rooms, LocalDate date) {
        List<Cat> checkUpList = new ArrayList<>();
        if (rooms == null || date == null || rooms.isEmpty()) {
            return checkUpList;
        }

        for (ShelterRoom room : rooms) {
            if (room.getCats() != null) {
                for (Cat cat : room.getCats()) {
                    if (cat != null && cat.getLastCheckUpDate() != null && cat.getLastCheckUpDate().isBefore(date)) {
                        checkUpList.add(cat);
                    }
                }
            }
        }
        return checkUpList;
    }

    // Returns a list of cats of a specific breed from all rooms
    public List<Cat> getCatsByBreed(List<ShelterRoom> rooms, Cat.Breed breed) {
        List<Cat> catsByBreed = new ArrayList<>();
        if (rooms == null || breed == null || rooms.isEmpty()) {
            return catsByBreed;
        }

        for (ShelterRoom room : rooms) {
            if (room.getCats() != null) {
                for (Cat cat : room.getCats()) {
                    if (cat != null && breed.equals(cat.getBreed())) {
                        catsByBreed.add(cat);
                    }
                }
            }
        }
        return catsByBreed;
    }
}
