package br.com.marcosRp.Educ.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;


@AllArgsConstructor
@Data
@Entity
@Table(	name = "Cursos")
public class Cursos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Nome;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Set<Usuario> usuarios;
}
