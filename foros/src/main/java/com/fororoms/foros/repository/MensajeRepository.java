package com.fororoms.foros.repository;

import com.fororoms.foros.entity.Mensaje;
import com.fororoms.foros.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

    List<Mensaje> findByPost(Post post);

    void deleteAllByPostId(Long id);
}
