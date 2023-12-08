package com.tienda.mathiloweb;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tienda.mathiloweb.entity.Usuario;
import com.tienda.mathiloweb.repository.IUsuarioRepository;

@SpringBootTest
class MathilowebApplicationTests {
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Test
	void crearUsuarioTest() {
		
		Usuario u = new Usuario();
		u.setId((long) 1);
		u.setNombre("User2");
		u.setApellido("Castillo");
		u.setEmail("user2@gmail.com");
		u.setPassword(encoder.encode("123"));
		
		Usuario resultado = usuarioRepository.save(u);
		
		assertTrue(resultado.getPassword().equalsIgnoreCase(u.getPassword()));
	}

}
