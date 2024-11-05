package com.fororoms.foros.repository.interfaces;

import com.fororoms.foros.service.domain.ForoDomain;

import java.util.List;


public interface IForo {

    ForoDomain findForoById(Long id);

    void deleteForoById(Long id);

    ForoDomain updateForoById(Long id,ForoDomain foroDomain);

    ForoDomain save(ForoDomain foroDomain);

    List<ForoDomain> findAllForos();

}
