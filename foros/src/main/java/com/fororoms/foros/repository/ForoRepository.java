package com.fororoms.foros.repository;

import com.fororoms.foros.repository.entity.Foro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForoRepository extends JpaRepository<Foro, Long> {
}
