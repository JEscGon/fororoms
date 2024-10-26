package com.fororoms.usuarios.repository;

import com.fororoms.usuarios.entity.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetails, Long> {
    UserDetails findByEmail(String email);


}
