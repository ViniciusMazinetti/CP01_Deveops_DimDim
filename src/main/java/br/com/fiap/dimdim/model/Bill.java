package br.com.fiap.dimdim.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "T_Bill")
@SequenceGenerator(name = "bill", sequenceName = "SQ_T_BILL", allocationSize = 1)
public class Bill {
	
	@Id
	@GeneratedValue(generator = "bill", strategy = GenerationType.IDENTITY)
	@Column(name = "id_bill")
	private Long id;
	
	@Column(name = "nr_value")
	private float value;
	
	
	@ManyToOne
	@JoinColumn(name = "id_client")
	private Client client;

}
