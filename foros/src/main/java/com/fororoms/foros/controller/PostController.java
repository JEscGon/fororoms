package com.fororoms.foros.controller;

import com.fororoms.foros.controller.dto.PostDTO;
import com.fororoms.foros.service.domain.ForoDomain;
import com.fororoms.foros.service.domain.PostDomain;
import com.fororoms.foros.service.ForoService;
import com.fororoms.foros.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//Obj Domain --> Obj DTO
@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private ForoService foroService;
    @Autowired
    private ModelMapper modelMapper;

 //   @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/new/{foroId}")
    public ResponseEntity<PostDTO> crearPost(@RequestHeader String authorization, @PathVariable Long foroId, @RequestBody PostDTO postDTO) {
        ForoDomain foro = foroService.obtenerForoPorId(foroId);
        if(foro == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        PostDomain postDomain = modelMapper.map(postDTO, PostDomain.class);
        postDomain.setForo(foro);
        postService.crearPost(authorization,foroId,postDomain);
        PostDTO postResponse = modelMapper.map(postDomain, PostDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponse);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> obtenerPostPorId(@PathVariable Long postId) {
        PostDomain postDomain = postService.obtenerPostPorId(postId);
        PostDTO postDTO = modelMapper.map(postDomain, PostDTO.class);
        return ResponseEntity.ok(postDTO);
    }

    @GetMapping("/{foroId}/all")
    public ResponseEntity<List<PostDTO>> listarPostsPorForo(@PathVariable Long foroId) {
        List<PostDomain> posts = postService.listarPostsPorForo(foroId);
        List<PostDTO> postResponse = posts.stream()
                .map(postDomain -> modelMapper.map(postDomain, PostDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(postResponse);
    }
    @PreAuthorize("@securityService.isUserIdMatching(#userId) or hasRole('ADMIN')")
    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> actualizarPost(@RequestHeader String authorization,@PathVariable Long postId, @RequestBody PostDTO postDTO) {
        PostDomain postDomain = modelMapper.map(postDTO, PostDomain.class);
        postDomain.setForo(postService.obtenerPostPorId(postId).getForo());
        postService.actualizarPost(authorization, postId, postDomain);
        PostDTO response = modelMapper.map(postDomain, PostDTO.class);
        return ResponseEntity.ok(response);
    }
    //@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> eliminarPost(@PathVariable Long postId) {
        postService.eliminarPost(postId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> listarPosts(){
        List<PostDomain> posts = postService.listarPosts();
        List<PostDTO> postResponse = posts.stream()
                .map(postDomain -> modelMapper.map(postDomain, PostDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(postResponse);
    }
}
