package br.com.pedido.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.pedido.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.userName = :userName")
	List<User> listByUserName(String userName);

	List<User> findBystatus(Boolean status);

	//@Query("SELECT u FROM User u WHERE u.userName = :userName")
	boolean existsByUserName(String userName);

	@EntityGraph(attributePaths = {"roles"})
	User findByUserName(String userName);

	Optional<User> findByUserNameAndPassword(String userName, String password);
	
	

}
