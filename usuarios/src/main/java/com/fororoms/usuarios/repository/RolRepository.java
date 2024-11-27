package com.fororoms.usuarios.repository;

import com.fororoms.usuarios.repository.entity.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository extends CrudRepository<Rol, Long> {
    List<Rol> findByRoleEnumIn(List<String> roleNames);
}
