package kodlamaio.northwind.api.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.northwind.business.abstracts.UserService;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.ErrorDataResult;


@RestController
@RequestMapping(value="/api/users")
public class UsersContoller {

	private UserService userService;

	@Autowired
	public UsersContoller(UserService userService) {
		super();
		this.userService = userService;
	}

	
	@PostMapping(value="/add")
	//kullnaıcı data veriyor
	public ResponseEntity<?> add (@Valid@RequestBody User user) {
	//buradan oku demek requestbody
		return ResponseEntity.ok(this.userService.add(user)) ;
	//? (unknown) ben ne verirsem onu döndürür
	}
	
	
	//AOP-bizimm bütün metotlarımızı herhangi bir hata olursa diye bütün operasyonların önüne global exception handle yazılır
	//bütün operasyonlara try catch yazmak yerine bir tane yazarız
	
	
	//javada tipi sonuna .class diyerek veriyoruz
	
	
	
	
	
	//bu sistemde bir hata olursa bu metotu devreye sok.
	
	//bütün metotlar buradan geçecek
	//bütün classların türü objecttir
	//bu obje valid hataları olabiir sistemde oluşan hatalar olabilir vs.
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object>handleValidationException
	(MethodArgumentNotValidException exceptions){
		Map<String,String>validationErrors=new HashMap<String, String>();
		
		for(FieldError fieldError:exceptions.getBindingResult().getFieldErrors()) {
		
		validationErrors.put(fieldError.getField() , fieldError.getDefaultMessage());	
	
		}	
		
		ErrorDataResult<Object>errors=new ErrorDataResult<Object>(validationErrors,"Doğrulama hataları");
	    return errors;
	}

}

