package app.adapter.in.rest.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.adapter.in.rest.request.UserRequest;
import app.domain.model.enums.Role;
import app.domain.model.people.User;
import app.domain.services.peopleServices.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@PostMapping("/users")
	public ResponseEntity<String> createUser(@RequestBody UserRequest request,
			@RequestParam(name = "creatorDocument") String creatorDocument) {
		try {
			User user = toDomain(request);
			User creator = new User();
			creator.setDocument(creatorDocument);
			userService.create(user, creator);
			return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/users/{document}")
	public ResponseEntity<String> deleteUser(@PathVariable String document) {
		try {
			User account = new User();
			account.setDocument(document);
			userService.delete(account);
			return ResponseEntity.ok("Usuario eliminado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/users/{document}")
	public ResponseEntity<User> getUserByDocument(@PathVariable String document) {
		try {
			User found = userService.findByDocument(document);
			return ResponseEntity.ok(found);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	public static class PasswordRequest {
		private String newPassword;

		public String getNewPassword() {
			return newPassword;
		}

		public void setNewPassword(String newPassword) {
			this.newPassword = newPassword;
		}
	}

	@PutMapping("/users/{document}/password")
	public ResponseEntity<String> updatePassword(@PathVariable String document, @RequestBody PasswordRequest body) {
		try {
			User account = new User();
			account.setDocument(document);
			userService.updatePassword(account, body.getNewPassword());
			return ResponseEntity.ok("Contraseña actualizada");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	public static class RoleRequest {
		private String role;

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}
	}

	@PutMapping("/users/{document}/role")
	public ResponseEntity<String> assignRole(@PathVariable String document, @RequestBody RoleRequest body) {
		try {
			User account = new User();
			account.setDocument(document);
			Role role = Role.valueOf(body.getRole());
			userService.assignRole(account, role);
			return ResponseEntity.ok("Rol asignado");
		} catch (IllegalArgumentException iae) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Rol inválido");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	// Helper: convierte UserRequest a dominio
	private User toDomain(UserRequest req) {
		User u = new User();
		if (req.getId() != null) {
			u.setDocument(req.getId());
		}
		u.setUsername(req.getUsername());
		u.setPassword(req.getPassword());
		u.setEmail(req.getEmail());
		if (req.getRole() != null && !req.getRole().isBlank()) {
			try {
				u.setRole(Role.valueOf(req.getRole()));
			} catch (IllegalArgumentException e) {
				// leave null; service will validate
			}
		}
		if (req.getBirthDate() != null && !req.getBirthDate().isBlank()) {
			try {
				u.setBirthDate(LocalDate.parse(req.getBirthDate()));
			} catch (Exception e) {
				// ignore parse error; service will validate
			}
		}
		return u;
	}
}
