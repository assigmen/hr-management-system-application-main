package com.dinsaren.hrmanagementsystemapplication.services.impl;

import com.dinsaren.hrmanagementsystemapplication.constants.Constant;
import com.dinsaren.hrmanagementsystemapplication.models.Position;
import com.dinsaren.hrmanagementsystemapplication.repositories.PositionRepository;
import com.dinsaren.hrmanagementsystemapplication.services.PositionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;

    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public List<Position> getAll() {
        return positionRepository.findAll();
    }

    @Override
    public List<Position> getAllStatusActive() {
        return positionRepository.findAllByStatus(Constant.STATUS_ACTIVE);
    }

    @Override
    public Position getById(Integer id) {
        return positionRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Position req) {
        var find = positionRepository.findById(req.getId()).orElse(null);
        if(find != null){
            var code = positionRepository.findByCode(req.getCode());
            if(code != null && find.getCode().equals(code.getCode())){
                positionRepository.save(req);
            }else{
                positionRepository.save(req);
            }

        }
    }

    @Override
    public void create(Position req) {
        var code = positionRepository.findByCode(req.getCode());
        if(code == null){
            positionRepository.save(req);
        }

    }

}
