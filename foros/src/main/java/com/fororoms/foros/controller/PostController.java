package com.fororoms.foros.controller;

import com.fororoms.foros.entity.Post;
import com.fororoms.foros.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foros/{foroId}/posts")
public class PostController {

    @Autowired
    private IPostService postService;

    // Crear un nuevo post en un foro
    @PostMapping
    public ResponseEntity<Post> crearPost(@PathVariable Long foroId, @RequestBody Post post) {
        Post nuevoPost = postService.crearPost(foroId, post);
        return ResponseEntity.ok(nuevoPost);
    }

     // Obtener un post por ID
    @GetMapping("/{postId}")
    public ResponseEntity<Post> obtenerPostPorId(@PathVariable Long postId) {
        Post post = postService.obtenerPostPorId(postId);
        return ResponseEntity.ok(post);
    }

    // Listar todos los posts de un foro
    @GetMapping
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

}
