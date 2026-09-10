package br.com.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pedido.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
