package br.com.fiap.dimdim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.dimdim.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {

}
