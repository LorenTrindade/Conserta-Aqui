package spring.consertaaqui.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import spring.consertaaqui.model.Solicitacao;

@Repository
@Transactional
public interface SolicitacaoRepository extends CrudRepository<Solicitacao, Long>{

	@Query("select s from Solicitacao s where s.usuario.id like %?1%")
	public List<Solicitacao> getSolicitacoesCliente(String id);	
	
	@Query("select s from Solicitacao s where s.servico.titulo like %?1%")
	public List<Solicitacao> buscarSolicitacao(String nome);
	
	@Query("select s from Solicitacao s where s.servico.usuario.id like %?1% and s.estado like 'Análise' and s.servico.usuario.tipoUsuario like 'prestador'")
	public List<Solicitacao> getSolicitacoesAnaliseP(String idc);		
	
	@Query("delete from Solicitacao c where c.servico.usuario.id like %?1%")
	public List<Solicitacao> deleteAllById(String cpf);
	
	@Query("select count(*) from Solicitacao s where s.servico.usuario.id like %?1%")
	public int countSolicitacaoById(String cpf);
}
