package com.yasmim.project.service;

import com.yasmim.project.dto.RegisterServiceData;
import com.yasmim.project.entity.ServiceData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceService {
    public List<ServiceData> getPaginatedServices(String query, Integer index, Integer size);
    public ServiceData registerService(RegisterServiceData serviceData);
}
