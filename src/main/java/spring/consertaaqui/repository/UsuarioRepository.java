package spring.consertaaqui.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.consertaaqui.model.Usuario;

@Repository
@Transactional
public interface UsuarioRepository extends CrudRepository<Usuario, String>{

	
	@Query("select u from Usuario u where u.tipoUsuario like 'prestador'")
	public List<Usuario> getUsuarioP();
	
	
}

