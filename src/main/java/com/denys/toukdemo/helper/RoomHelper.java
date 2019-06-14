package com.denys.toukdemo.helper;

import com.denys.toukdemo.dto.ReservationUnit;
import com.denys.toukdemo.dto.Room;

import java.util.*;

public class RoomHelper {
    private Room room;

    public RoomHelper(Room room) {
        this.room = room;
    }

    public boolean hasSinglePlaceBetweenTwoAlreadyReserved(List<ReservationUnit> units){
        Map<Integer, List<Integer>> uniqueRows = createMapUniqueRows(units);
        for(Map.Entry<Integer, List<Integer>> pair : uniqueRows.entrySet()){
            if(singleRowNotContainsSinglePlace(pair.getKey(), pair.getValue()))
                return false;
        }
        return true;
    }

    private Map<Integer, List<Integer>> createMapUniqueRows(List<ReservationUnit> units){
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (ReservationUnit unit : units) {
            Integer row = unit.getRow();
            Integer place = unit.getPlace();
            List<Integer> set = map.get(row);
            if(set == null) {
                set = new ArrayList<>();
                map.put(row, set);
            }
            set.add(place);
        }
        return map;
    }

    private boolean singleRowNotContainsSinglePlace(Integer row, List<Integer> places){
        Collections.sort(places);
        boolean[] copyRow = room.getReserved()[row].clone();
        for (Integer place : places) {
            if(copyRow[place] == true)
                return false;
            copyRow[place] = true;
        }
        for (int i = 2; i < copyRow.length; i++) {
            if(copyRow[i] && !copyRow[i - 1] && copyRow[i - 2])
                return false;
        }
        return true;
    }
}
