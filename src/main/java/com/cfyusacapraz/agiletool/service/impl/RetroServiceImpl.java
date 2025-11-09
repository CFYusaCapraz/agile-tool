package com.cfyusacapraz.agiletool.service.impl;

import com.cfyusacapraz.agiletool.repository.RetroRepository;
import com.cfyusacapraz.agiletool.service.RetroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RetroServiceImpl implements RetroService {

    private final RetroRepository retroRepository;
}
