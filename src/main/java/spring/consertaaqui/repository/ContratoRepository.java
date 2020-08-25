package spring.consertaaqui.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.consertaaqui.model.Contrato;

@Repository
@Transactional
public interface ContratoRepository extends CrudRepository<Contrato, Long>{

	@Query("select c from Contrato c where c.estado= 'Em andamento'")
	public List<Contrato> getContratoAndamentoC(String idprestador);
	
	@Query("select c from Contrato c where c.estado= 'Em andamento' and c.servico.usuario.id like %?1%")
	public List<Contrato> getContratoAndamentoP(String idprestador);
	
	@Query("select c from Contrato c where c.estado= 'Finalizado' and c.usuario.id like %?1%")
	public List<Contrato> getContratoFinalizadoC(String idprestadpr);
	
	@Query("select c from Contrato c where c.estado= 'Finalizado' and c.servico.usuario.id like %?1% and c.servico.usuario.tipoUsuario like 'prestador'")
	public List<Contrato> getContratoFinalizadoP(String idprestadpr);
	
	@Query("delete from Contrato c where c.servico.usuario.id like %?1%")
	public List<Contrato> deleteAllById(String cpf);
	
	@Query("select count(*) from Contrato c where c.servico.usuario.id like %?1%")
	public int countContratoById(String cpf);
}
