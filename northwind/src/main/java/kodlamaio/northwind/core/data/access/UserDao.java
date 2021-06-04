package kodlamaio.northwind.core.data.access;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.northwind.core.entities.User;

public interface UserDao extends  JpaRepository<User, Integer> {
//kullanıcı ekleme hazır jpa repostory sayesinde
	 
	
	User findByEmail(String email);
	//emaile göre kullanıcı aratıyorum
	
	
	
	
	
}
