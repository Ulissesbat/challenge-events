package com.events.desafio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.events.desafio.entities.Usuario;
import com.events.desafio.projection.UserDetailsProjection;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	
	@Query(nativeQuery = true, value = """
		SELECT tb_usuario.email AS usuarionome, tb_usuario.senha, tb_role.id AS roleId, tb_role.authority
		FROM tb_usuario
		INNER JOIN tb_usuario_role ON tb_usuario.id = tb_usuario_role.usuario_id
		INNER JOIN tb_role ON tb_role.id = tb_usuario_role.role_id
		WHERE tb_usuario.email = :email
		""")
	List<UserDetailsProjection> searchUserAndRolesByEmail(String email);
	
	Optional<Usuario> findByEmail(String email);
	
}
