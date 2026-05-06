package doctor.doctorservice.repository;

import doctor.doctorservice.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  DoctorRepository extends JpaRepository<Doctor, Long > {




}
