package appointment.appointmentservice.service;

import appointment.appointmentservice.dto.AppointmentRequest;
import appointment.appointmentservice.dto.AppointmentResponse;
import appointment.appointmentservice.entity.Appointment;
import appointment.appointmentservice.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final RestTemplate restTemplate;

    public AppointmentResponse createAppointment(AppointmentRequest request) {

        validatePatientExists(request.getPatientId());


        validateDoctorExists(request.getDoctorId());


        Appointment appointment = new Appointment();
        appointment.setPatientId(request.getPatientId());
        appointment.setDoctorId(request.getDoctorId());
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setReason(request.getReason());
        appointment.setStatus("PENDING");

        Appointment savedAppointment = appointmentRepository.save(appointment);

        return mapToResponse(savedAppointment);
    }

    private void validatePatientExists(Long patientId) {
        try {
            String url = "http://patient-service/api/v1/patients/" + patientId;
            restTemplate.getForObject(url, Object.class);
        } catch (RestClientException e) {
            throw new IllegalArgumentException("Patient ID " + patientId + " not found");
        }
    }

    private void validateDoctorExists(Long doctorId) {
        try {
            String url = "http://doctor-service/api/v1/doctors/" + doctorId;
            restTemplate.getForObject(url, Object.class);
        } catch (RestClientException e) {
            throw new IllegalArgumentException("Doctor ID " + doctorId + " not found");
        }
    }

    private AppointmentResponse mapToResponse(Appointment appointment) {
        return new AppointmentResponse(
                appointment.getId(),
                appointment.getPatientId(),
                appointment.getDoctorId(),
                appointment.getAppointmentDate(),
                appointment.getReason(),
                appointment.getStatus()
        );
    }


}
