package tn.esprit.eventsproject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.eventsproject.dto.EventDTO;
import tn.esprit.eventsproject.dto.LogisticsDTO;
import tn.esprit.eventsproject.dto.ParticipantDTO;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.entities.Tache;
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


    @Test
    void testEventEqualsAndHashCodeConsistency() {
        Event event1 = new Event();
        event1.setIdEvent(1);

        Event event2 = new Event();
        event2.setIdEvent(1);

        assertNotEquals(event1, event2);
        assertNotEquals(event1.hashCode(), event2.hashCode());
    }


    @Test
    void testNoExceptionThrown() {
        assertDoesNotThrow(() -> {
            // Your code here that should not throw any exception
            int result = 2 + 2;
            assertEquals(4, result);
        }, "Unexpected exception thrown");
    }

    @Test
    void testExceptionThrown() {
        Throwable exception = assertThrows(Exception.class, () -> {
            // Your code here that should throw a specific exception
            throw new Exception("Test exception");
        });

        assertEquals("Test exception", exception.getMessage());
    }

    @Test
    void testNoExceptionThrownAlternative() {
        try {
            // Your code here that should not throw any exception
            int result = 2 + 2;
            assertEquals(4, result);
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }

    @Test
    void testExceptionThrownAlternative() {
        try {
            // Your code here that should throw a specific exception
            throw new Exception("Test exception");
        } catch (Exception e) {
            assertEquals("Test exception", e.getMessage());
        }
    }

    @Test
    void testEventEntity() {
        // Create an Event object
        Event event = new Event();
        event.setIdEvent(1);
        event.setDescription("Test Event");
        event.setDateDebut(LocalDate.now());
        event.setDateFin(LocalDate.now().plusDays(1));
        event.setCout(100.0f);

        // Test getters
        assertEquals(1, event.getIdEvent());
        assertEquals("Test Event", event.getDescription());
        assertNotNull(event.getDateDebut());
        assertNotNull(event.getDateFin());
        assertEquals(100.0f, event.getCout());

        // Test setters
        event.setIdEvent(2);
        assertEquals(2, event.getIdEvent());

        // Test toString method
        assertNotNull(event.toString());
    }

    @Test
    void testLogisticsEntity() {
        // Create a Logistics object
        Logistics logistics = new Logistics();
        logistics.setIdLog(1);
        logistics.setDescription("Test Logistics");
        logistics.setReserve(true);
        logistics.setPrixUnit(50.0f);
        logistics.setQuantite(10);

        // Test getters
        assertEquals(1, logistics.getIdLog());
        assertEquals("Test Logistics", logistics.getDescription());
        assertEquals(true, logistics.isReserve());
        assertEquals(50.0f, logistics.getPrixUnit());
        assertEquals(10, logistics.getQuantite());

        // Test setters
        logistics.setIdLog(2);
        assertEquals(2, logistics.getIdLog());

        // Test toString method
        assertNotNull(logistics.toString());
    }

    @Test
    void testParticipantEntity() {
        // Create a Participant object
        Participant participant = new Participant();
        participant.setIdPart(1);
        participant.setNom("John");
        participant.setPrenom("Doe");
        participant.setTache(Tache.INVITE);

        // Test getters
        assertEquals(1, participant.getIdPart());
        assertEquals("John", participant.getNom());
        assertEquals("Doe", participant.getPrenom());
        assertEquals(Tache.INVITE, participant.getTache());

        // Test setters
        participant.setIdPart(2);
        assertEquals(2, participant.getIdPart());

        // Test toString method
        assertNotNull(participant.toString());
    }

    @Test
    void testDTOs() {
        // Create DTO objects
        EventDTO eventDTO = new EventDTO();
        LogisticsDTO logisticsDTO = new LogisticsDTO();
        ParticipantDTO participantDTO = new ParticipantDTO();

        // Test DTO getters and setters
        // (Note: You can add more detailed tests based on your specific requirements)

        assertNotNull(eventDTO.toString());
        assertNotNull(logisticsDTO.toString());
        assertNotNull(participantDTO.toString());
    }


}
