package tn.esprit.eventsproject;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.entities.Tache;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ParticipantTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private Participant participant;

    @Test
    void testParticipantCreation() {
        assertNotNull(participant);
    }

    @Test
    void testParticipantIdGetterSetter() {
        participant.setIdPart(1);
        assertEquals(1, participant.getIdPart());
    }

    @Test
    void testParticipantNomGetterSetter() {
        participant.setNom("John");
        assertEquals("John", participant.getNom());
    }

    @Test
    void testParticipantPrenomGetterSetter() {
        participant.setPrenom("Doe");
        assertEquals("Doe", participant.getPrenom());
    }

    @Test
    void testParticipantTacheGetterSetter() {
        participant.setTache(Tache.ORGANISATEUR);
        assertEquals(Tache.ORGANISATEUR, participant.getTache());
    }

    @Test
    void testParticipantEventsGetterSetter() {
        Set<Event> events = new HashSet<>();
        Event event = new Event();
        events.add(event);
        participant.setEvents(events);

        assertEquals(events, participant.getEvents());
    }



    @Test
    void testFindParticipantById() {
        when(participantRepository.findById(1)).thenReturn(Optional.of(participant));

        Optional<Participant> foundParticipant = participantRepository.findById(1);

        assertTrue(foundParticipant.isPresent());
        assertEquals(participant, foundParticipant.get());
    }

    // Add more tests as needed...
}

