package tn.esprit.eventsproject;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.eventsproject.config.PerformanceAspect;
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


    @Autowired
    private PerformanceAspect performanceAspect;
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
    @Test
    void testGetLogisticsDates() {
        LocalDate dateDebut = LocalDate.now().minusDays(1);
        LocalDate dateFin = LocalDate.now().plusDays(1);

        Event event = new Event();
        Logistics logistics = new Logistics();
        logistics.setReserve(true);
        Set<Logistics> logisticsSet = new HashSet<>();
        logisticsSet.add(logistics);
        event.setLogistics(logisticsSet);

        when(eventRepository.findByDateDebutBetween(dateDebut, dateFin)).thenReturn(List.of(event));

        List<Logistics> logisticsList = eventServices.getLogisticsDates(dateDebut, dateFin);

        assertNotNull(logisticsList);
        assertFalse(logisticsList.isEmpty());
        assertEquals(logistics, logisticsList.get(0));
    }
    @Test
    void testAddAffectLog() {
        Logistics logistics = new Logistics();
        String descriptionEvent = "Test Event";

        Event event = new Event();
        when(eventRepository.findByDescription(descriptionEvent)).thenReturn(event);
        when(logisticsRepository.save(any(Logistics.class))).thenReturn(logistics);

        Logistics savedLogistics = eventServices.addAffectLog(logistics, descriptionEvent);

        assertNotNull(savedLogistics);
        assertTrue(event.getLogistics().contains(savedLogistics));
    }
    @Test
    void testAddAffectEventParticipantBySet() {
        Event event = new Event();
        Set<Participant> participants = new HashSet<>();
        Participant participant = new Participant();
        participants.add(participant);
        event.setParticipants(participants);

        when(participantRepository.findById(participant.getIdPart())).thenReturn(Optional.of(participant));
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        Event savedEvent = eventServices.addAffectEvenParticipant(event);

        assertNotNull(savedEvent);
        assertTrue(savedEvent.getParticipants().contains(participant));
    }


}
