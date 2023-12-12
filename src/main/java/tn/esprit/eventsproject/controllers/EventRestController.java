package tn.esprit.eventsproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.eventsproject.dto.EventDTO;
import tn.esprit.eventsproject.dto.LogisticsDTO;
import tn.esprit.eventsproject.dto.ParticipantDTO;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.services.IEventServices;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("event")
@RestController
public class EventRestController {
    private final IEventServices eventServices;

    @PostMapping("/addPart")
    public Participant addParticipant(@RequestBody ParticipantDTO participantDTO){
        Participant participant = new Participant();
        participant.setEvents(participantDTO.getEvents());
        participant.setTache(participantDTO.getTache());
        participant.setPrenom(participantDTO.getPrenom());
        participant.setNom(participantDTO.getNom());
        participant.setIdPart(participantDTO.getIdPart());
        return eventServices.addParticipant(participant);
    }
    @PostMapping("/addEvent/{id}")
    public Event addEventPart(@RequestBody EventDTO eventDTO, @PathVariable("id") int idPart){
        Event event = new Event();
        event.setIdEvent(eventDTO.getIdEvent());
        event.setLogistics(eventDTO.getLogistics());
        event.setCout(eventDTO.getCout());
        event.setDescription(eventDTO.getDescription());
        event.setDateFin(eventDTO.getDateFin());
        event.setDateDebut(eventDTO.getDateDebut());
        event.setParticipants(eventDTO.getParticipants());
        return eventServices.addAffectEvenParticipant(event, idPart);
    }
    @PostMapping("/addEvent")
    public Event addEvent(@RequestBody EventDTO eventDTO){
        Event event = new Event();
        event.setIdEvent(eventDTO.getIdEvent());
        event.setLogistics(eventDTO.getLogistics());
        event.setCout(eventDTO.getCout());
        event.setDescription(eventDTO.getDescription());
        event.setDateFin(eventDTO.getDateFin());
        event.setDateDebut(eventDTO.getDateDebut());
        event.setParticipants(eventDTO.getParticipants());
        return eventServices.addAffectEvenParticipant(event);
    }
    @PutMapping("/addAffectLog/{description}")
    public Logistics addAffectLog(@RequestBody LogisticsDTO logisticsDTO,@PathVariable("description") String descriptionEvent){
        Logistics logistics = new Logistics();
        logistics.setIdLog(logisticsDTO.getIdLog());
        logistics.setDescription(logisticsDTO.getDescription());
        logistics.setQuantite(logisticsDTO.getQuantite());
        logistics.setPrixUnit(logistics.getPrixUnit());
        logistics.setReserve(logisticsDTO.isReserve());
        return eventServices.addAffectLog(logistics,descriptionEvent);
    }
    @GetMapping("/getLogs/{d1}/{d2}")
    public List<Logistics> getLogistiquesDates (@PathVariable("d1") LocalDate dateDebut, @PathVariable("d2") LocalDate dateFin){
        return eventServices.getLogisticsDates(dateDebut,dateFin);
    }
}
