package app.adapter.out.persistence.medicalOrderAdapters;

import app.domain.model.medicalOrders.MedicalOrder;
import app.domain.ports.medicalOrdersPorts.MedicalOrderPort;
import app.infrastructure.persistence.repository.MedicalOrderRepository;
import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MedicalOrderAdapter implements MedicalOrderPort {

    private final MedicalOrderRepository repository;

    public MedicalOrderAdapter(MedicalOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<MedicalOrder> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<MedicalOrder> findAll() {
        return repository.findAll();
    }

    @Override
    public MedicalOrder save(MedicalOrder order) {
        return repository.save(order);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
