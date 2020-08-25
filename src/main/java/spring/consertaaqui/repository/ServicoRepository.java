package spring.consertaaqui.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.consertaaqui.model.Servico;

@Repository
@Transactional
public interface ServicoRepository extends CrudRepository<Servico, Long>{

	
	@Query("select s from Servico s where s.usuario.id like %?1%")
	public List<Servico> getServicos(String idp);

	@Query("select s from Servico s where s.titulo like %?1%")
	public List<Servico> buscarServicos(String nomepesquisa);

	@Query("select s from Servico s where s.categoria like 'alvenaria'")
	public List<Servico> getAlvenaria();

	@Query("select s from Servico s where s.categoria like 'eletrica'")
	public List<Servico> getEletrica();

	@Query("select s from Servico s where s.categoria like 'hidraulica' ")
	public List<Servico> getHidraulica();

	@Query("select s from Servico s where s.categoria like 'pintura'")
	public List<Servico> getPintura();

	@Query("select s from Servico s where s.categoria like 'reparos'")
	public List<Servico> getReparos();

	@Query("select s from Servico s where s.categoria like 'refrigeracao'")
	public List<Servico> getRefrigeracao();
	
	@Query("delete from Servico s where s.usuario.id like %?1%")
	public List<Servico> deleteAllById(String cpf);
	
	@Query("select count (*) from Servico s where s.usuario.id like %?1% and s.data like '____%01%__' ")
	public int countServicosByIdJan(String cpf);
	
	@Query("select count (*) from Servico s where s.usuario.id like %?1% and s.data like '____%02%__' ")
	public int countServicosByIdFev(String cpf);
	
	@Query("select count (*) from Servico s where s.usuario.id like %?1% and s.data like '____%03%__' ")
	public int countServicosByIdMar(String cpf);
	
	@Query("select count (*) from Servico s where s.usuario.id like %?1% and s.data like '____%04%__' ")
	public int countServicosByIdAbr(String cpf);
	
	@Query("select count (*) from Servico s where s.usuario.id like %?1% and s.data like '____%05%__' ")
	public int countServicosByIdMai(String cpf);
	
	@Query("select count (*) from Servico s where s.usuario.id like %?1% and s.data like '____%06%__' ")
	public int countServicosByIdJun(String cpf);
	
	@Query("select count (*) from Servico s where s.usuario.id like %?1% and s.data like '____%07%__' ")
	public int countServicosByIdJul(String cpf);
	
	@Query("select count (*) from Servico s where s.usuario.id like %?1% and s.data like '____%08%__' ")
	public int countServicosByIdAgo(String cpf);
	
	@Query("select count (*) from Servico s where s.usuario.id like %?1% and s.data like '____%09%__' ")
	public int countServicosByIdSet(String cpf);
	
	@Query("select count (*) from Servico s where s.usuario.id like %?1% and s.data like '____%10%__' ")
	public int countServicosByIdOut(String cpf);
	
	@Query("select count (*) from Servico s where s.usuario.id like %?1% and s.data like '____%11%__' ")
	public int countServicosByIdNov(String cpf);
	
	@Query("select count (*) from Servico s where s.usuario.id like %?1% and s.data like '____%12%__' ")
	public int countServicosByIdDez(String cpf);
	
	@Query("select count(*) from Servico s where s.usuario.id like %?1%")
	public int countServicoById(String cpf);
}
