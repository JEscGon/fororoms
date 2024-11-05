package com.fororoms.foros.repository.jpaimpl;

import com.fororoms.foros.repository.PostRepository;
import com.fororoms.foros.repository.entity.Post;
import com.fororoms.foros.repository.interfaces.IPost;
import com.fororoms.foros.service.domain.PostDomain;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PostJpaImpl implements IPost {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDomain save(Long foroId,Long postId, PostDomain postDomain) {
        Post post;
        if (postId != null){
            Optional<Post> postEntity = postRepository.findById(postId);
            if(postEntity.isPresent()) {
                post = postEntity.get();
                modelMapper.map(postDomain, post);
                post.setFechaEdicion(LocalDateTime.now());
                post.setId(postId);
            }else{
                throw new RuntimeException("Post no encontrado");
            }
        } else {
            post = modelMapper.map(postDomain, Post.class);
            post.setFechaCreacion(LocalDateTime.now());
        }
        postRepository.save(post);
        return modelMapper.map(post, PostDomain.class);
    }

    @Override
    public PostDomain obtenerPostPorId(Long id) {
        Post postEntity = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post no encontrado"));
        return modelMapper.map(postEntity, PostDomain.class);
    }

    @Override
    public List<PostDomain> listarPostsPorForo(Long foroId) {
        List<Post> postEntityList = postRepository.findAllByForoId(foroId);
        return postEntityList.stream()
          .map(post -> modelMapper.map(post, PostDomain.class))
          .collect(Collectors.toList());
    }

    @Override
    public void eliminarPost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<PostDomain> listarPosts() {
        List<Post> postEntityList = postRepository.findAll();
        return postEntityList.stream()
                .map(post -> modelMapper.map(post, PostDomain.class))
                .collect(Collectors.toList());
    }
}
