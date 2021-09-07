package br.com.fiap.dimdim.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "T_Bill")
@SequenceGenerator(name = "bill", sequenceName = "SQ_T_BILL", allocationSize = 1)
public class Bill {
	
	@Id
	@GeneratedValue(generator = "bill", strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Campo obrigatório")
	private float value;
	
	@NotBlank (message = "Campo obrigatório")
	@ManyToOne
	private Client client;
	
	@Temporal(TemporalType.DATE)
	@CreationTimestamp
	private Date creationDate;
	
	@Temporal(TemporalType.DATE)
	private Date expireDate;

}
