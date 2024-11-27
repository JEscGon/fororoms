package com.fororoms.usuarios.repository;

import com.fororoms.usuarios.repository.entity.Permission;
import org.springframework.data.repository.CrudRepository;

public interface PermissionRepository extends CrudRepository<Permission, Long> {

}
