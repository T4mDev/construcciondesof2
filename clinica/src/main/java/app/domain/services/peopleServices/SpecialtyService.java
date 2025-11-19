package app.domain.services.peopleServices;

import app.domain.model.people.Specialty;
import app.domain.ports.peoplePorts.SpecialtyPort;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio de dominio para la gestión de especialidades médicas.
 * Encapsula las reglas de negocio relacionadas con la creación, búsqueda,
 * listado y eliminación de especialidades, delegando la persistencia
 * en {@link SpecialtyPort}.
 */
@Service
public class SpecialtyService {

    @Autowired
    private final SpecialtyPort specialtyPort;

    /**
     * Constructor que inyecta el puerto de persistencia de especialidades.
     * 
     * @param specialtyPort instancia de {@link SpecialtyPort}
     */
    public SpecialtyService(SpecialtyPort specialtyPort) {
        this.specialtyPort = specialtyPort;
    }

    /**
     * Crea una nueva especialidad médica.
     * Reglas de negocio:
     * <ul>
     * <li>El ID de la especialidad es obligatorio.</li>
     * <li>El nombre de la especialidad es obligatorio.</li>
     * <li>No puede existir otra especialidad con el mismo ID.</li>
     * </ul>
     * 
     * @param specialty objeto {@link Specialty} a crear
     * @throws Exception si alguna regla de negocio es violada
     */
    public void create(Specialty specialty) throws Exception {
        if (specialty.getName() == null || specialty.getName().isEmpty()) {
            throw new Exception("El nombre de la especialidad es obligatorio");
        }

        Specialty existing = specialtyPort.findByName(specialty.getName());
        if (existing != null) {
            throw new Exception("Ya existe una especialidad con ese ID");
        }

        specialtyPort.save(specialty);
    }

    /**
     * Busca una especialidad médica por su ID.
     * Regla de negocio: la especialidad debe existir en el sistema.
     * 
     * @param id identificador de la especialidad
     * @return especialidad encontrada
     * @throws Exception si no se encuentra la especialidad
     */
    public Specialty findById(Integer id) throws Exception {
        Specialty specialty = specialtyPort.findById(id);
        if (specialty == null) {
            throw new Exception("No se encontró la especialidad con ese ID");
        }
        return specialty;
    }

    /**
     * Lista todas las especialidades médicas.
     * Nota: este método debe implementarse en el adaptador correspondiente.
     * 
     * @return lista de especialidades
     * @throws UnsupportedOperationException siempre, hasta que se implemente en el
     *                                       adaptador
     */
    public List<Specialty> listAllSpecialties() throws Exception {
        throw new UnsupportedOperationException("listAllSpecialties debe implementarse en el adaptador");
    }

    /**
     * Elimina una especialidad médica existente.
     * Regla de negocio: la especialidad debe existir en el sistema.
     * 
     * @param specialty objeto {@link Specialty} a eliminar
     * @throws Exception si la especialidad no existe
     */
    public void delete(Specialty specialty) throws Exception {
        Specialty existing = specialtyPort.findById(specialty.getId());
        if (existing == null) {
            throw new Exception("No se encontró la especialidad para eliminar");
        }

        specialtyPort.delete(specialty);
    }
}
