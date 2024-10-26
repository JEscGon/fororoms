package com.fororoms.usuarios.repository;

import com.fororoms.usuarios.entity.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends CrudRepository<Rol, Long> {
    List<Rol> findRoleEntitiesByRoleEnumIn(List<String> roleNames);
}
