package app.domain.ports.peoplePorts;

import java.util.List;

import app.domain.model.people.Specialty;

public interface SpecialtyPort {
    Specialty findById(Integer id) throws Exception;

    Specialty findByName(String name) throws Exception;

    void save(Specialty specialty) throws Exception;

    void delete(Specialty specialty) throws Exception;

    List<Specialty> findAll() throws Exception;
}
