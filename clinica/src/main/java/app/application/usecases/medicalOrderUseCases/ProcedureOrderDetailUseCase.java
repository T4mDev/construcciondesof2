package app.application.usecases.medicalOrderUseCases;

import app.domain.model.medicalOrders.ProcedureOrderDetail;
import app.domain.model.people.Specialty;
import app.domain.services.medicalOrdersServices.ProcedureOrderDetailService;

import org.springframework.stereotype.Component;

@Component
public class ProcedureOrderDetailUseCase {

    private final ProcedureOrderDetailService procedureOrderDetailService;

    public ProcedureOrderDetailUseCase(ProcedureOrderDetailService procedureOrderDetailService) {
        this.procedureOrderDetailService = procedureOrderDetailService;
    }

    public void createProcedureOrderDetail(ProcedureOrderDetail detail) throws Exception {
        procedureOrderDetailService.create(detail);
    }

    public void validateSpecialistRequirement(ProcedureOrderDetail detail) throws Exception {
        procedureOrderDetailService.validateSpecialistRequirement(detail);
    }

    public void deleteProcedureOrderDetail(ProcedureOrderDetail detail) throws Exception {
        procedureOrderDetailService.delete(detail);
    }

    public void assignSpecialistToProcedure(ProcedureOrderDetail detail, Specialty specialty) throws Exception {
        procedureOrderDetailService.assignSpecialist(detail, specialty);
    }
}
