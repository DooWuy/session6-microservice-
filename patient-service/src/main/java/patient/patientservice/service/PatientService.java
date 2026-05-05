package patient.patientservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patient.patientservice.dto.PatientRequest;
import patient.patientservice.dto.PatientResponse;
import patient.patientservice.entity.Patient;
import patient.patientservice.repository.PatientRepository;

@Service
@RequiredArgsConstructor
public class PatientService {


    private final PatientRepository  patientRepository ;

    public PatientResponse createPatient(PatientRequest request){

        Patient patient = new Patient() ;
        patient.setId(request.getId());
        patient.setFullName(request.getFullName());
        patient.setAddress(request.getAddress());
        patient.setMedicalHistory(request.getMedicalHistory());

        Patient savedPatient = patientRepository.save(patient);

        PatientResponse response = new PatientResponse();
        patient.setId(request.getId());
        patient.setFullName(request.getFullName());
        patient.setAddress(request.getAddress());
        patient.setMedicalHistory(request.getMedicalHistory());

        return response ;


    }
}
