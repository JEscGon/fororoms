package com.fororoms.foros.repository;

import com.fororoms.foros.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
