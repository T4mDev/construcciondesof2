package app.application.usecases.peopleUseCases;

import app.domain.model.people.Specialty;
import app.domain.services.peopleServices.SpecialtyService;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpecialtyUseCase {

    private final SpecialtyService specialtyService;

    public SpecialtyUseCase(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    public void createSpecialty(Specialty specialty) throws Exception {
        specialtyService.create(specialty);
    }

    public Specialty findSpecialtyById(Integer id) throws Exception {
        return specialtyService.findById(id);
    }

    public List<Specialty> listAllSpecialties() throws Exception {
        return specialtyService.listAllSpecialties();
    }

    public void deleteSpecialty(Specialty specialty) throws Exception {
        specialtyService.delete(specialty);
    }
}
