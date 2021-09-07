package br.com.fiap.dimdim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.dimdim.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
