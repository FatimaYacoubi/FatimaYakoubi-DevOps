package tn.esprit.eventsproject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.eventsproject.controllers.EventRestController;
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
        Participant participant = new Participant(); // create a participant object
        when(eventServices.addParticipant(any())).thenReturn(participant);

        Participant result = eventRestController.addParticipant(participant);

        assertEquals(participant, result);
    }

    @Test
    void testAddEventPart() {
        Event event = new Event(); // create an event object
        int idPart = 1;
        when(eventServices.addAffectEvenParticipant(any(), eq(idPart))).thenReturn(event);

        Event result = eventRestController.addEventPart(event, idPart);

        assertEquals(event, result);
    }

    @Test
    void testAddEvent() {
        Event event = new Event(); // create an event object
        when(eventServices.addAffectEvenParticipant(any())).thenReturn(event);

        Event result = eventRestController.addEvent(event);

        assertEquals(event, result);
    }
    @Test

    void testAddAffectLog() {
        Logistics logistics = new Logistics(); // create a logistics object
        String descriptionEvent = "eventDescription";
        when(eventServices.addAffectLog(any(), eq(descriptionEvent))).thenReturn(logistics);

        Logistics result = eventRestController.addAffectLog(logistics, descriptionEvent);

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
