package com.yasmim.project.service;

import com.yasmim.project.dto.RegisterServiceData;
import com.yasmim.project.entity.ServiceData;

import java.util.List;

public interface ServiceService {
    public List<ServiceData> getPaginatedServices(String query, Integer index, Integer size);
    public String registerService(RegisterServiceData serviceData);
}
