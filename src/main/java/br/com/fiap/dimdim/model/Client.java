package br.com.fiap.dimdim.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "T_Client")
public class Client {
	
	@Id
	@GeneratedValue(generator = "client", strategy = GenerationType.IDENTITY)
	@Column(name = "id_client")
	private Long id;
	
	@Column(name = "nm_client")
	private String name;
	
	@Column(name = "nr_cpf")
	private String cpf;
	
	@Column(name = "ds_email")
	private String email;

}
