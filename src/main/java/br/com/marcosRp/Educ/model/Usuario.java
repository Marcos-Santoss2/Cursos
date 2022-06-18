package br.com.marcosRp.Educ.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(	name = "usuariosCurso")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Size(max = 20)
	private String username;
	@NotBlank
	@Size(max = 120)
	private String password;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_curso")
	private Set<Cursos> curso = new HashSet<>();


	public Set<Cursos> getCurso() {
		return curso;
	}

	public void setCurso(Set<Cursos> curso) {
		this.curso = curso;
	}


}
