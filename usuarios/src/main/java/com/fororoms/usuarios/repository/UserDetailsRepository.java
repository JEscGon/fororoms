package com.fororoms.usuarios.repository;

import com.fororoms.usuarios.repository.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
