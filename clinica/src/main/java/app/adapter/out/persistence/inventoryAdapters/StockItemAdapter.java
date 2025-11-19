package app.adapter.out.persistence.inventoryAdapters;

import app.domain.model.inventory.StockItem;
import app.domain.ports.inventoryPorts.StockItemPort;
import app.infrastructure.persistence.entities.StockItemEntity;
import app.infrastructure.persistence.mapper.StockItemMapper;
import app.infrastructure.persistence.repository.StockItemRepository;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class StockItemAdapter implements StockItemPort {

    private final StockItemRepository repository;

    public StockItemAdapter(StockItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<StockItem> findById(Long id) {
        return repository.findById(id)
                .map(StockItemMapper::toDomain);
    }

    @Override
    public Optional<StockItem> findByItemName(String name) {
        return repository.findByItemName(name)
                .map(StockItemMapper::toDomain);
    }

    @Override
    public List<StockItem> findAll() {
        return repository.findAll()
                .stream()
                .map(StockItemMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public StockItem save(StockItem item) {
        StockItemEntity entity = StockItemMapper.toEntity(item);
        StockItemEntity saved = repository.save(entity);
        return StockItemMapper.toDomain(saved);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean isEmpty() {
        return repository.count() == 0;
    }

    @Override
    public List<StockItem> findByType(String type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByType'");
    }

}
