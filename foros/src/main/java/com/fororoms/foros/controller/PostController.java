package com.fororoms.foros.controller;

import com.fororoms.foros.repository.entity.Post;
import com.fororoms.foros.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private IPostService postService;



    // Crear un nuevo post en un foro
    @PostMapping("/{foroId}")
    public ResponseEntity<Post> crearPost(@PathVariable Long foroId, @RequestBody Post post) {

    if (foroId == null || foroId <= 0) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    /*Foro aux = foroService.obtenerForoPorId(foroId);
    post.setForo(aux);*/

    Post nuevoPost = postService.crearPost(foroId, post);
    return ResponseEntity.ok(nuevoPost);
    }

     // Obtener un post por ID
    @GetMapping("/{postId}")
    public ResponseEntity<Post> obtenerPostPorId(@PathVariable Long postId) {
    try {
            Post post = postService.obtenerPostPorId(postId);
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Listar todos los posts de un foro
    @GetMapping("/{foroId}/posts")
    public ResponseEntity<List<Post>> listarPostsPorForo(@PathVariable Long foroId) {
        List<Post> posts = postService.listarPostsPorForo(foroId);
        return ResponseEntity.ok(posts);
    }

    // Actualizar un post por ID
    @PutMapping("/{postId}")
    public ResponseEntity<Post> actualizarPost(@PathVariable Long postId, @RequestBody Post post) {
        Post postActualizado = postService.actualizarPost(postId, post);
        return ResponseEntity.ok(postActualizado);
    }

     // Eliminar un post por ID
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> eliminarPost(@PathVariable Long postId) {
        postService.eliminarPost(postId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/all")
    public ResponseEntity<List<Post>> listarPosts(){
        List<Post> posts = postService.listarPosts();
        return ResponseEntity.ok(posts);
    }
}
