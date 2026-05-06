package appointment.appointmentservice.repository;

import appointment.appointmentservice.entity.Appointment;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Configuration
    public class RestTemplateConfig {

        @Bean
        @LoadBalanced
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }
}
