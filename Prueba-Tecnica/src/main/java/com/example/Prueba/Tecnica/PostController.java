package pruebatecnico.controller;

import pruebatecnico.model.Post;
import pruebatecnico.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/{usuarioId}")
    public List<Post> listarPostsPorUsuario(@PathVariable Long usuarioId) {
        return postService.listarPostsPorUsuario(usuarioId);
    }

    @PostMapping("/{usuarioId}")
    public Post crearPost(@PathVariable Long usuarioId, @RequestBody Post post) {
        return postService.crearPost(usuarioId, post);
    }
}
