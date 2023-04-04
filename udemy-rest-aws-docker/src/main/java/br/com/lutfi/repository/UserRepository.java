package br.com.lutfi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.lutfi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
//	@Query("SELECT u FROM User u WHERE u.userName = : userName")//nao havia necessidade
//	User findByUserName(@Param("userName") String userName);
	User findByUserName(String userName);
}