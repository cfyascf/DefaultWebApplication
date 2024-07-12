package com.yasmim.project.services.impl;

import com.yasmim.project.dto.RegisterServiceData;
import com.yasmim.project.entities.ServiceData;
import com.yasmim.project.repositories.ServiceRepository;
import com.yasmim.project.services.ServiceService;
import com.yasmim.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DefaultServiceService implements ServiceService {

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    UserService userService;

    @Override
    public List<ServiceData> getPaginatedServices(String query, Integer index, Integer size) {
        var services = serviceRepository.findAll();

        return paginate(filterServices(query, services), index, size);
    }

    @Override
    public ServiceData registerService(RegisterServiceData obj) {
        ServiceData service = new ServiceData(
                format(obj.getName()),
                format(obj.getDescription()));

        service.setManager(userService.findUserByFullName(format(obj.getManagerName())));

        return serviceRepository.save(service);
    }

    public String format(String input) {
        return input.toLowerCase(Locale.ROOT);
    }

    public List<ServiceData> filterServices(String query, List<ServiceData> services) {
        if(query == null || query.isEmpty()) return services;

        return services.stream()
                .filter(s -> s.getName().contains(query))
                .toList();
    }

    public List<ServiceData> paginate(List<ServiceData> filteredServices, Integer index, Integer size) {

        if(size > filteredServices.size()) { return filteredServices; }

        if(index == 0) { index = 1; }

        var servicesIndexes = index * size;
        List<ServiceData> paginatedServices = new ArrayList<>();

        for(int i = servicesIndexes - size; i < servicesIndexes; i++) {
            paginatedServices.add(filteredServices.get(i));
        }

        return paginatedServices;
    }
}
