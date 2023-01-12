package com.example.opentalk.service;

import com.example.opentalk.dto.OpentalkDTO;

public interface OpentalkService {
    OpentalkDTO get(OpentalkDTO criteria);

    OpentalkDTO findByID(Long id);

    Boolean save(OpentalkDTO opentalk);

    Boolean delete(Long id);
}
