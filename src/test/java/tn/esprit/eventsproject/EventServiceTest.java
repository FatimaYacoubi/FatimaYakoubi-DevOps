package tn.esprit.eventsproject;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.eventsproject.entities.*;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;
import tn.esprit.eventsproject.services.EventServicesImpl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class EventServicesImplTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private LogisticsRepository logisticsRepository;

    @InjectMocks
    private EventServicesImpl eventServices;

    @Test
    void testAddParticipant() {
        Participant participant = new Participant();
        when(participantRepository.save(any(Participant.class))).thenReturn(participant);

        Participant savedParticipant = eventServices.addParticipant(participant);

        assertNotNull(savedParticipant);
        assertEquals(participant, savedParticipant);
    }

  

    // Similar tests for other methods...

    @Test
    void testCalculCout() {
        Event event = new Event();
        event.setDescription("Test Event");
        event.setCout(0f);

        Set<Logistics> logisticsSet = new HashSet<>();
        Logistics logistics1 = new Logistics();
        logistics1.setReserve(true);
        logistics1.setPrixUnit(10.0f);
        logistics1.setQuantite(5);
        logisticsSet.add(logistics1);

        Logistics logistics2 = new Logistics();
        logistics2.setReserve(false);
        logistics2.setPrixUnit(15.0f);
        logistics2.setQuantite(3);
        logisticsSet.add(logistics2);

        event.setLogistics(logisticsSet);

        when(eventRepository.findByParticipants_NomAndParticipants_PrenomAndParticipants_Tache("Tounsi", "Ahmed", Tache.ORGANISATEUR)).thenReturn(List.of(event));

        eventServices.calculCout();

        assertEquals(50.0f, event.getCout(), 0.01);
    }
}
