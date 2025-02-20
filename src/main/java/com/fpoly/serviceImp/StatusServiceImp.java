package com.fpoly.serviceImp;

import com.fpoly.entity.Status;
import com.fpoly.repository.StatusRepository;
import com.fpoly.servive.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatusServiceImp implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<Status> findAll() {
        return statusRepository.findAll();
    }
}
