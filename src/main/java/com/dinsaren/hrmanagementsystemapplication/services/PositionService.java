package com.dinsaren.hrmanagementsystemapplication.services;

import com.dinsaren.hrmanagementsystemapplication.models.Position;

import java.util.List;

public interface PositionService {
    List<Position> getAll();

    List<Position> getAllStatusActive();

    Position getById(Integer id);

    void update(Position req);

    void create(Position req);
}
