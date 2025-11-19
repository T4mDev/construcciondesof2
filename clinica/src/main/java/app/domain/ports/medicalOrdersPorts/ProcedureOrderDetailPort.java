package app.domain.ports.medicalOrdersPorts;

import app.domain.model.medicalOrders.ProcedureOrderDetail;

public interface ProcedureOrderDetailPort {
    ProcedureOrderDetail findById(String id) throws Exception;

    void save(ProcedureOrderDetail detail) throws Exception;

    void delete(ProcedureOrderDetail detail) throws Exception;
}
