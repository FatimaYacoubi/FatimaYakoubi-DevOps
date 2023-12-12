package tn.esprit.eventsproject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.eventsproject.controllers.EventRestController;
import tn.esprit.eventsproject.dto.EventDTO;
import tn.esprit.eventsproject.dto.LogisticsDTO;
import tn.esprit.eventsproject.dto.ParticipantDTO;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.services.IEventServices;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventRestControllerTest {

    @Mock
    private IEventServices eventServices;

    @InjectMocks
    private EventRestController eventRestController;

    @Test
    void testAddParticipant() {
        ParticipantDTO participantDTO  = new ParticipantDTO(); // create a participant object

        Participant participant = new Participant();
        participant.setEvents(participantDTO.getEvents());
        participant.setTache(participantDTO.getTache());
        participant.setPrenom(participantDTO.getPrenom());
        participant.setNom(participantDTO.getNom());
        participant.setIdPart(participantDTO.getIdPart());



        when(eventServices.addParticipant(any())).thenReturn(participant);

        Participant result = eventRestController.addParticipant(participantDTO);

        assertEquals(participant, result);
    }

    @Test
    void testAddEventPart() {
        EventDTO eventDTO = new EventDTO(); // create an event object

        Event event = new Event();
        event.setIdEvent(eventDTO.getIdEvent());
        event.setLogistics(eventDTO.getLogistics());
        event.setCout(eventDTO.getCout());
        event.setDescription(eventDTO.getDescription());
        event.setDateFin(eventDTO.getDateFin());
        event.setDateDebut(eventDTO.getDateDebut());
        event.setParticipants(eventDTO.getParticipants());

        int idPart = 1;
        when(eventServices.addAffectEvenParticipant(any(), eq(idPart))).thenReturn(event);

        Event result = eventRestController.addEventPart(eventDTO, idPart);

        assertEquals(event, result);
    }

    @Test
    void testAddEvent() {
        EventDTO eventDTO = new EventDTO(); // create an event object



        Event event = new Event();
        event.setIdEvent(eventDTO.getIdEvent());
        event.setLogistics(eventDTO.getLogistics());
        event.setCout(eventDTO.getCout());
        event.setDescription(eventDTO.getDescription());
        event.setDateFin(eventDTO.getDateFin());
        event.setDateDebut(eventDTO.getDateDebut());
        event.setParticipants(eventDTO.getParticipants());

        when(eventServices.addAffectEvenParticipant(any())).thenReturn(event);


        Event result = eventRestController.addEvent(eventDTO);

        assertEquals(event, result);
    }
    @Test

    void testAddAffectLog() {
        LogisticsDTO logisticsDTO = new LogisticsDTO(); // create a logistics object
        Logistics logistics = new Logistics();
        logistics.setIdLog(logisticsDTO.getIdLog());
        logistics.setDescription(logisticsDTO.getDescription());
        logistics.setQuantite(logisticsDTO.getQuantite());
        logistics.setPrixUnit(logistics.getPrixUnit());
        logistics.setReserve(logisticsDTO.isReserve());
        String descriptionEvent = "eventDescription";
        when(eventServices.addAffectLog(any(), eq(descriptionEvent))).thenReturn(logistics);

        Logistics result = eventRestController.addAffectLog(logisticsDTO, descriptionEvent);

        assertEquals(logistics, result);
    }

    @Test
    void testGetLogistiquesDates() {
        LocalDate date_debut = LocalDate.now();
        LocalDate date_fin = LocalDate.now().plusDays(1);
        List<Logistics> logisticsList = List.of(new Logistics()); // create a list of logistics
        when(eventServices.getLogisticsDates(any(), any())).thenReturn(logisticsList);

        List<Logistics> result = eventRestController.getLogistiquesDates(date_debut, date_fin);

        assertEquals(logisticsList, result);
    }
}
