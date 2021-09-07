package br.com.fiap.dimdim.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "T_Client")
@SequenceGenerator(name = "client", sequenceName = "SQ_T_CLIENT", allocationSize = 1)
public class Client {
	
	@Id
	@GeneratedValue(generator = "client", strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String cpf;
	
	@NotBlank
	@Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "E-mail não válido. Favor digitar um e-mail válido.")
	private String email;

}
