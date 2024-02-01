
package pruebatecnico.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPublicacion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    // Getters y setters
}
