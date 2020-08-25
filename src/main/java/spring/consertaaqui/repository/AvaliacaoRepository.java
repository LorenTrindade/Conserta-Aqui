package spring.consertaaqui.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import spring.consertaaqui.model.Avaliacao;

public interface AvaliacaoRepository extends CrudRepository<Avaliacao, Long>{

	@Query("select count (valor) from Avaliacao a where a.servico.usuario.id like %?1% and a.valor = 5")
	public int countEstrelaById5(String cpf);
	
	@Query("select count (valor) from Avaliacao a where a.servico.usuario.id like %?1% and a.valor = 4")
	public int countEstrelaById4(String cpf);
	
	@Query("select count (valor) from Avaliacao a where a.servico.usuario.id like %?1% and a.valor = 3")
	public int countEstrelaById3(String cpf);
	
	@Query("select count (valor) from Avaliacao a where a.servico.usuario.id like %?1% and a.valor = 2")
	public int countEstrelaById2(String cpf);
	
	@Query("select count (valor) from Avaliacao a where a.servico.usuario.id like %?1% and a.valor = 1")
	public int countEstrelaById1(String cpf);
	
	@Query("select a from Avaliacao a where a.usuario.id like %?1%")
	public List<Avaliacao> getAvaliacaoC(String idp);
	
	@Query("select a from Avaliacao a where a.servico.usuario.id like %?1%")
	public List<Avaliacao> getAvaliacaoP(String idp);
}
