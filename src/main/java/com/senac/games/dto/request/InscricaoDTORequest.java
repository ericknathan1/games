package com.senac.games.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class InscricaoDTORequest {
    private int status;

    private int participanteId;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getParticipanteId() {
        return participanteId;
    }

    public void setParticipanteId(int participanteId) {
        this.participanteId = participanteId;
    }
}
