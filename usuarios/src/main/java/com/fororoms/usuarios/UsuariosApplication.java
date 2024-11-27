package com.fororoms.usuarios;

import com.fororoms.usuarios.repository.PermissionRepository;
import com.fororoms.usuarios.repository.RolRepository;
import com.fororoms.usuarios.repository.UserDetailsRepository;
import com.fororoms.usuarios.repository.UsuarioRepository;
import com.fororoms.usuarios.repository.entity.*;
import com.fororoms.usuarios.service.domain.RolEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@EnableDiscoveryClient // Activar Eureka CLIENT
@SpringBootApplication(scanBasePackages = "com.fororoms.usuarios")
public class UsuariosApplication {
	public static void main(String[] args) {SpringApplication.run(UsuariosApplication.class, args);}

////SOLO SE INICIA UNA VEZ PARA CREAR LOS ROLES Y PERMISOS
//	@Autowired
//	PasswordEncoder passwordEncoder;
//
//	@Bean
//	CommandLineRunner init(RolRepository repository, PermissionRepository permissionRepository) {
//		return args -> {
//// Crea permisos */
//			Permission createPermission = Permission.builder()
//					.name("CREATE")
//					.build();
//			Permission readPermission = Permission.builder()
//					.name("READ")
//					.build();
//			Permission updatePermission = Permission.builder()
//					.name("UPDATE")
//					.build();
//			Permission deletePermission = Permission.builder()
//					.name("DELETE")
//					.build();
//			permissionRepository.saveAll(List.of(createPermission, readPermission, updatePermission, deletePermission));
//
//// Crea roles */
//			Rol adminRole = Rol.builder()
//					.roleEnum(RolEnum.ROLE_ADMIN)
//					.build();
//
//			Rol userRole = Rol.builder()
//					.roleEnum(RolEnum.ROLE_USER)
//					.build();
//
//			Rol invitedRole = Rol.builder()
//					.roleEnum(RolEnum.ROLE_INVITED)
//					.build();
//
//		repository.save(adminRole);
//		repository.save(userRole);
//		repository.save(invitedRole);
//		};
//	}
}