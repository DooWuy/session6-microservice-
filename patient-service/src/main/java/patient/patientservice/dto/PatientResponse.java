package patient.patientservice.dto;

import lombok.Data;

@Data
public class PatientResponse {

    private Long id;
    private String fullName;
    private String address;
    private String medicalHistory;
}
