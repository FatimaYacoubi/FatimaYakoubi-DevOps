package tn.esprit.eventsproject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.repositories.EventRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;


    @Test
    void testFindByDateDebutBetween() {
        // Given
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.now().plusDays(5);

        Event event1 = new Event();
        event1.setDateDebut(date1);
        eventRepository.save(event1);

        Event event2 = new Event();
        event2.setDateDebut(date2);
        eventRepository.save(event2);

        // When
        List<Event> result = eventRepository.findByDateDebutBetween(date1, date2);

        // Then
        assertEquals(2, result.size());
    }

}
