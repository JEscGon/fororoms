package com.fororoms.foros.repository.jpaimpl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fororoms.foros.repository.PostRepository;
import com.fororoms.foros.repository.entity.Post;
import com.fororoms.foros.repository.interfaces.IPost;
import com.fororoms.foros.service.domain.PostDomain;
import com.fororoms.foros.utils.JwtUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

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
    @Autowired
    private JwtUtils jwtUtils;

 //TODO : validacion userID con el token si no es el mismo ID o admin(preauthorice controller) no puede editar.
    @Override
    public PostDomain save(@RequestHeader("Authorization") String token, Long foroId, Long postId, PostDomain postDomain) {
        DecodedJWT decodedJWT = jwtUtils.validateToken(token.replace("Bearer ", ""));
        String userId = jwtUtils.extractUserId(decodedJWT);
        Post post;
        if (postId != null){
            Optional<Post> postEntity = postRepository.findById(postId);
            if(postEntity.isPresent()) {
                post = postEntity.get();
                if (post.getUsuarioId().equals(Long.valueOf(userId)) || decodedJWT.getClaim("roles").asList(String.class).contains("ADMIN")) {
                    throw new RuntimeException("Usuario no autorizado para editar este post");
                }
                LocalDateTime aux = post.getFechaCreacion();
                modelMapper.map(postDomain, post);
                post.setFechaEdicion(LocalDateTime.now());
                post.setFechaCreacion(aux);
                post.setUsuarioId(postEntity.get().getUsuarioId());
            }else{
                throw new RuntimeException("Post no encontrado");
            }
        } else {
            post = modelMapper.map(postDomain, Post.class);
            post.setFechaCreacion(LocalDateTime.now());
            post.setUsuarioId(Long.valueOf(userId));
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
        //TODO : eliminar mensajes relacionados con el post?
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
