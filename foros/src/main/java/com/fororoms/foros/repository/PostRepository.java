package com.fororoms.foros.repository;

import com.fororoms.foros.repository.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByForoId(Long foroId);
}
