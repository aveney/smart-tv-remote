package com.TelevisionRemote.SmartTelevisionRemote.service;

import com.TelevisionRemote.SmartTelevisionRemote.repository.AppsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AppsService {

    private AppsRepository appsRepository;
}
