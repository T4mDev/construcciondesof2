package app.adapter.out.persistence.medicalOrderAdapters;

import app.domain.model.medicalOrders.ProcedureOrderDetail;
import app.domain.ports.medicalOrdersPorts.ProcedureOrderDetailPort;
import org.springframework.stereotype.Repository;

@Repository
public class ProcedureOrderDetailAdapter implements ProcedureOrderDetailPort {

    @Override
    public void save(ProcedureOrderDetail detail) throws Exception {
        // implementación mínima
    }

    @Override
    public void delete(ProcedureOrderDetail detail) throws Exception {
        // implementación mínima
    }

    @Override
    public ProcedureOrderDetail findById(String id) throws Exception {
        return null; // implementación mínima segura
    }
}
