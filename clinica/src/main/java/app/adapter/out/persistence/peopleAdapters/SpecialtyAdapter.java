package app.adapter.out.persistence.peopleAdapters;

import app.domain.model.people.Specialty;
import app.domain.ports.peoplePorts.SpecialtyPort;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.domain.model.people.Specialty;

/**
 * Adapter de salida para la entidad {@link Specialty}.
 *
 * Implementa el puerto {@link SpecialtyPort} simulando una base de datos en
 * memoria
 * mediante un mapa. Permite realizar operaciones CRUD básicas sobre tipos de
 * especialistas.
 */
@Repository
public class SpecialtyAdapter implements SpecialtyPort {

    // Simulación de base de datos en memoria
    private final Map<Integer, Specialty> database = new HashMap<>();

    @Override
    public Specialty findById(Integer id) throws Exception {
        return database.get(id);
    }

    @Override
    public Specialty findByName(String name) throws Exception {
        return database.values().stream()
                .filter(s -> s.getName() != null && s.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Specialty specialty) throws Exception {
        if (specialty == null || specialty.getId() == null) {
            throw new Exception("Especialidad inválida");
        }
        database.put(specialty.getId(), specialty);
    }

    @Override
    public void delete(Specialty specialty) throws Exception {
        if (specialty == null || specialty.getId() == null) {
            throw new Exception("Especialidad inválida");
        }
        database.remove(specialty.getId());
    }

    @Override
    public List<Specialty> findAll() throws Exception {
        return new ArrayList<>(database.values());
    }
}
