package pruebatecnico.service;

import pruebatecnico.model.Usuario;
import pruebatecnico.repository.UsuarioRepository;
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
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService = new UsuarioServiceImpl();

    private List<Usuario> usuarios;

    @BeforeEach
    void setUp() {
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1L, "123456789", "John", "Doe", "password"));
        usuarios.add(new Usuario(2L, "987654321", "Jane", "Smith", "password123"));
    }

    @Test
    public void testListarUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(usuarios);
        List<Usuario> usuariosObtenidos = usuarioService.listarUsuarios();
        assertEquals(2, usuariosObtenidos.size());
    }

    // Agrega más pruebas según sea necesario
}
