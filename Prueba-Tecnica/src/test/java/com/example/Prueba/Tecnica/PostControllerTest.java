package pruebatecnico.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import pruebatecnico.model.Post;
import pruebatecnico.service.PostService;
import pruebatecnico.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PostController.class)
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @MockBean
    private UsuarioService usuarioService;

    @InjectMocks
    private PostController postController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testListarPostsPorUsuario() throws Exception {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1L, "Primer post", null));
        when(postService.listarPostsPorUsuario(1L)).thenReturn(posts);
        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].text").value("Primer post"));
    }

    // Se agrega mas pruebas segun sea necesario
}
