package pruebatecnico.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cellphone;
    private String name;
    private String lastName;
    private String password;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Post> posts;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    // Getters y setters
}
