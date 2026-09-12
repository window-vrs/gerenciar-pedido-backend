package br.com.pedido.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.pedido.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.username = :userName")
	List<User> listByUsername(String userName);

	List<User> findBystatus(Boolean status);

	//@Query("SELECT u FROM User u WHERE u.username = :userName")
	boolean existsByUsername(String username);

	@EntityGraph(attributePaths = {"roles"})
	User findByUsername(String username);

	Optional<User> findByUsernameAndPassword(String username, String password);
	
	

}
