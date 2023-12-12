package tn.esprit.eventsproject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.repositories.EventRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;
    @Test
    void testEventCreation() {
        Event event = new Event();
        assertNotNull(event);
    }

    @Test
    void testEventIdGetterSetter() {
        Event event = new Event();
        event.setIdEvent(1);
        assertEquals(1, event.getIdEvent());
    }

    @Test
    void testEventDescriptionGetterSetter() {
        Event event = new Event();
        event.setDescription("Test Description");
        assertEquals("Test Description", event.getDescription());
    }

    @Test
    void testEventDateDebutGetterSetter() {
        Event event = new Event();
        LocalDate date = LocalDate.now();
        event.setDateDebut(date);
        assertEquals(date, event.getDateDebut());
    }

    @Test
    void testEventDateFinGetterSetter() {
        Event event = new Event();
        LocalDate date = LocalDate.now().plusDays(1);
        event.setDateFin(date);
        assertEquals(date, event.getDateFin());
    }

    @Test
    void testEventCoutGetterSetter() {
        Event event = new Event();
        event.setCout(100.0f);
        assertEquals(100.0f, event.getCout(), 0.01);
    }

    @Test
    void testEventEquality() {
        Event event1 = new Event();
        event1.setIdEvent(1);

        Event event2 = new Event();
        event2.setIdEvent(1);

        assertNotEquals(event1, event2);
    }

    @Test
    void testEventNotEqual() {
        Event event1 = new Event();
        event1.setIdEvent(1);

        Event event2 = new Event();
        event2.setIdEvent(2);

        assertNotEquals(event1, event2);
    }

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
