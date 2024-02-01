package pruebatecnico.service;

import pruebatecnico.model.Post;
import pruebatecnico.model.Usuario;
import pruebatecnico.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private PostService postService = new PostServiceImpl();

    private Usuario usuario;
    private List<Post> posts;

    @BeforeEach
    void setUp() {
        usuario = new Usuario(1L, "123456789", "John", "Doe", "password");
        posts = new ArrayList<>();
        posts.add(new Post(1L, "Primer post", usuario));
        posts.add(new Post(2L, "Segundo post", usuario));
    }

    @Test
    public void testListarPostsPorUsuario() {
        when(usuarioService.obtenerUsuarioPorId(1L)).thenReturn(usuario);
        when(postRepository.findByUsuario(usuario)).thenReturn(posts);
        List<Post> postsObtenidos = postService.listarPostsPorUsuario(1L);
        assertEquals(2, postsObtenidos.size());
    }

    // Agrega más pruebas según sea necesario
}
