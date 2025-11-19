package app.adapter.in.rest.controller;

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
import app.adapter.in.rest.request.SpecialtyRequest;
import app.application.usecases.peopleUseCases.UserUseCase;
import app.application.usecases.peopleUseCases.SpecialtyUseCase;
import app.domain.model.people.Specialty;
import app.domain.model.people.User;
import app.domain.model.enums.Role;

import java.util.List;

@RestController
@RequestMapping("/hr")
public class HumanResourcesController {

	@Autowired
	private UserUseCase userUseCase;

	@Autowired
	private SpecialtyUseCase specialtyUseCase;

	@PostMapping("/staff")
	public ResponseEntity<String> createStaff(@RequestBody UserRequest request,
			@RequestParam(name = "creatorDocument") String creatorDocument) {
		try {
			User user = toDomain(request);
			User creator = new User();
			creator.setDocument(creatorDocument);
			userUseCase.createUser(user, creator);
			return ResponseEntity.status(HttpStatus.CREATED).body("Personal creado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/staff/{document}")
	public ResponseEntity<User> getStaffByDocument(@PathVariable String document) {
		try {
			User found = userUseCase.findUserByDocument(document);
			return ResponseEntity.ok(found);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PutMapping("/staff/{document}/role")
	public ResponseEntity<String> assignRole(@PathVariable String document, @RequestBody SpecialtyRequest body) {
		try {
			// reuse SpecialtyRequest.role field as a simple wrapper for role string
			String roleStr = body.getName();
			Role role = Role.valueOf(roleStr);
			User user = new User();
			user.setDocument(document);
			userUseCase.assignUserRole(user, role);
			return ResponseEntity.ok("Rol asignado");
		} catch (IllegalArgumentException iae) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Rol inválido");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	// Specialties
	@PostMapping("/specialties")
	public ResponseEntity<String> createSpecialty(@RequestBody SpecialtyRequest req) {
		try {
			Specialty s = new Specialty();
			if (req.getId() != null && !req.getId().isBlank()) {
				try {
					s.setId(Integer.valueOf(req.getId()));
				} catch (NumberFormatException nfe) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id de especialidad inválido");
				}
			}
			s.setName(req.getName());
			specialtyUseCase.createSpecialty(s);
			return ResponseEntity.status(HttpStatus.CREATED).body("Especialidad creada");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/specialties/{id}")
	public ResponseEntity<Specialty> getSpecialty(@PathVariable String id) {
		try {
			Integer key;
			try {
				key = Integer.valueOf(id);
			} catch (NumberFormatException nfe) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			Specialty s = specialtyUseCase.findSpecialtyById(key);
			return ResponseEntity.ok(s);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/specialties")
	public ResponseEntity<List<Specialty>> listSpecialties() {
		try {
			List<Specialty> list = specialtyUseCase.listAllSpecialties();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/specialties/{id}")
	public ResponseEntity<String> deleteSpecialty(@PathVariable String id) {
		try {
			Specialty s = new Specialty();
			try {
				s.setId(Integer.valueOf(id));
			} catch (NumberFormatException nfe) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id de especialidad inválido");
			}
			specialtyUseCase.deleteSpecialty(s);
			return ResponseEntity.ok("Especialidad eliminada");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	// Helper conversion
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
				// leave null; use case/service will validate
			}
		}
		if (req.getBirthDate() != null && !req.getBirthDate().isBlank()) {
			try {
				u.setBirthDate(java.time.LocalDate.parse(req.getBirthDate()));
			} catch (Exception e) {
				// ignore
			}
		}
		return u;
	}
}
