package app.application.usecases.medicalOrderUseCases;

import app.domain.model.medicalOrders.Prescription;
import app.domain.services.medicalOrdersServices.PrescriptionService;

import org.springframework.stereotype.Component;

@Component
public class PrescriptionUseCase {

    private final PrescriptionService prescriptionService;

    public PrescriptionUseCase(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    public void createPrescription(Prescription prescription) throws Exception {
        prescriptionService.create(prescription);
    }

    public void deletePrescription(Prescription prescription) throws Exception {
        prescriptionService.delete(prescription);
    }

    public void updatePrescriptionDuration(Prescription prescription, int newDays) throws Exception {
        prescriptionService.updateDuration(prescription, newDays);
    }

    public void validatePrescriptionDosage(Prescription prescription) throws Exception {
        prescriptionService.validateDosage(prescription);
    }
}
