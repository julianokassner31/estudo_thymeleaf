package br.com.jkassner.ce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jkassner.ce.model.Role;
import br.com.jkassner.ce.model.TipoRole;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	List<Role> findByTipoNot(TipoRole tipo);

}
