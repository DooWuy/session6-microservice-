package patient.patientservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PatientRequest {

    private Long id;
    private String fullName;

    private String address;
    private String medicalHistory;


}
