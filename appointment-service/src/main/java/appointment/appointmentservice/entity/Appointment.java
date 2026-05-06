package appointment.appointmentservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @NotNull
    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @NotNull
    @FutureOrPresent
    @Column(name = "appointment_date", nullable = false)
    private LocalDateTime appointmentDate;

    @NotBlank
    @Size(max = 500)
    @Column(name = "reason", nullable = false)
    private String reason;

    @NotBlank
    @Pattern(regexp = "^(pending|confirm|cancel)$")
    @Column(name = "status", nullable = false)
    private String status;


}
