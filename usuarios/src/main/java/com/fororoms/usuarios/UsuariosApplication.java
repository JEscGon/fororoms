package com.fororoms.usuarios;

import com.fororoms.usuarios.entity.*;
import com.fororoms.usuarios.repository.UserDetailsRepository;
import com.fororoms.usuarios.repository.UsuarioRepository;
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

@EnableDiscoveryClient // Activar Eureka CLIENT@SpringBootApplication(scanBasePackages = "com.fororoms.usuarios")
@SpringBootApplication(scanBasePackages = "com.fororoms.usuarios")
public class UsuariosApplication {
	public static void main(String[] args) {SpringApplication.run(UsuariosApplication.class, args);}
	@Autowired
	PasswordEncoder passwordEncoder;
	@Bean
	CommandLineRunner init(UsuarioRepository userRepository, UserDetailsRepository userDetailsRepository) {
		return args -> {
			// Crea permisos */
			Permission createPermission = Permission.builder()
					.name("CREATE")
					.build();
			Permission readPermission = Permission.builder()
					.name("READ")
					.build();
			Permission updatePermission = Permission.builder()
					.name("UPDATE")
					.build();
			Permission deletePermission = Permission.builder()
					.name("DELETE")
					.build();
			Permission refactorPermission = Permission.builder()
					.name("REFACTOR")
					.build();

			// Crea roles */
			Rol adminRole = Rol.builder()
					.roleEnum(RolEnum.ADMIN)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			Rol userRole = Rol.builder()
					.roleEnum(RolEnum.USER)
					.permissionList(Set.of(createPermission, readPermission))
					.build();

			Rol invitedRole = Rol.builder()
					.roleEnum(RolEnum.INVITED)
					.permissionList(Set.of(readPermission))
					.build();

			Rol developerRole = Rol.builder()
					.roleEnum(RolEnum.DEVELOPER)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
					.build();

			// Crea USERS */
			Usuario user = Usuario.builder()
					.username("user")
					.password(passwordEncoder.encode("user"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.role(Set.of(userRole))
					.build();

			Usuario admin = Usuario.builder()
					.username("admin")
					.password(passwordEncoder.encode("admin"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.role(Set.of(adminRole))
					.build();

			Usuario invited = Usuario.builder()
					.username("invited")
					.password(passwordEncoder.encode("invited"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.role(Set.of(invitedRole))
					.build();

			Usuario developer = Usuario.builder()
					.username("developer")
					.password(passwordEncoder.encode("developer"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.role(Set.of(developerRole))
					.build();

			Usuario userAll = Usuario.builder()
					.username("userAll")
					.password(passwordEncoder.encode("userAll"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.role(Set.of(userRole, adminRole, invitedRole, developerRole))
					.build();

			userRepository.saveAll(List.of(user, admin, invited, developer, userAll));

		        // Crea UserDetails
        UserDetails userDetails1 = UserDetails.builder()
            .nombre("John")
            .apellidos("Doe")
            .email("john.doe@example.com")
            .fechaNacimiento("1990-01-01")
            .dni("12345678A")
            .telefono("123456789")
            .direccion("123 Main St")
            .pais("USA")
            .codigoPostal("12345")
            .ciudad("New York")
            .iban("US12345678901234567890")
            .userEntity(user)
            .build();

        UserDetails userDetails2 = UserDetails.builder()
            .nombre("Jane")
            .apellidos("Doe")
            .email("jane.doe@example.com")
            .fechaNacimiento("1992-02-02")
            .dni("87654321B")
            .telefono("987654321")
            .direccion("456 Elm St")
            .pais("USA")
            .codigoPostal("54321")
            .ciudad("Los Angeles")
            .iban("US09876543210987654321")
            .userEntity(admin)
            .build();

        userDetailsRepository.saveAll(List.of(userDetails1, userDetails2));


		//ver contraseña encriptada
		System.out.println("");
		System.out.println("USER DEFAULTS");
		System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Username : admin " + " Password : " + new BCryptPasswordEncoder().encode("admin"));
		System.out.println("Username : user " + " Password : " + new BCryptPasswordEncoder().encode("user"));
		System.out.println("Username : invited " + " Password : " + new BCryptPasswordEncoder().encode("invited"));
		System.out.println("Username : developer " + " Password : " + new BCryptPasswordEncoder().encode("developer"));
		System.out.println("Username : userAll " + " Password : " + new BCryptPasswordEncoder().encode("userAll"));
		System.out.println("----------------------------------------------------------------------------------------------------");


		};
	}
}