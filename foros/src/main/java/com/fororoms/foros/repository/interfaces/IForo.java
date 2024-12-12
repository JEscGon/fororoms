package com.fororoms.foros.repository.interfaces;

import com.fororoms.foros.service.domain.ForoDomain;

import java.util.List;

public interface IForo {

    ForoDomain findForoById(Long id);
    void deleteForoById(Long id);

    ForoDomain save(String userId, Long id, ForoDomain foroDomain);

    List<ForoDomain> findAllForos();

}
