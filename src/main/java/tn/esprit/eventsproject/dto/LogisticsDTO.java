package tn.esprit.eventsproject.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogisticsDTO {
    int idLog;
    String description;
    boolean reserve;
    float prixUnit;
    int quantite;

}
