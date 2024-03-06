package com.lf.common.handler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/03/06
 */
@Service
public class HandlerServiceFactory {
    private final Map<Byte, HandlerService> serviceMap = new HashMap<>();

    @Autowired
    public HandlerServiceFactory(List<HandlerService> serviceList) {
        serviceList.forEach(handlerService -> serviceMap.put(handlerService.getCommand(), handlerService));
    }

    public HandlerService getService(Byte command) {
        return serviceMap.get(command);
    }
}
