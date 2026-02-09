package com.example.mvc_rest_api_examen_semi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mvc_rest_api_examen_semi.dto.ReservaDTO;
import com.example.mvc_rest_api_examen_semi.model.Aula;
import com.example.mvc_rest_api_examen_semi.model.Docente;
import com.example.mvc_rest_api_examen_semi.model.Reserva;
import com.example.mvc_rest_api_examen_semi.repository.AulaRepository;
import com.example.mvc_rest_api_examen_semi.repository.ReservaRepository;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private AulaRepository aulaRepository;

    public List<ReservaDTO> listarPorDocente(Docente docente) {
        return reservaRepository.findByDocente(docente).stream()
                .map(this::entityToDto).toList();
    }

    public List<ReservaDTO> listarPorDocenteYFechaAnterior(Docente docente, LocalDateTime fecha) {
        return reservaRepository.findByFechaReservaBeforeAndDocenteId(fecha, docente.getId())
                .stream()
                .map(this::entityToDto).toList();
    }

    public ReservaDTO crearReserva(ReservaDTO dto, Docente docente) {
        Aula aula = aulaRepository.findAll().stream()
                .filter(a -> a.getCodigo().equals(dto.getAulaCodigo()))
                .findFirst().orElseThrow(() -> new RuntimeException("Aula no encontrada"));

        // Validación de solapamiento (Lógica: InicioA < FinB Y InicioB < FinA)
        boolean solapada = reservaRepository.findAll().stream()
                .filter(r -> r.getAula().getId().equals(aula.getId()))
                .anyMatch(r -> dto.getFechaInicio().isBefore(r.getFechaFin()) && 
                               r.getFechaInicio().isBefore(dto.getFechaFin()));

        if (solapada) throw new RuntimeException("El aula ya está ocupada en ese horario");

        Reserva r = new Reserva();
        r.setFechaReserva(LocalDateTime.now());
        r.setFechaInicio(dto.getFechaInicio());
        r.setFechaFin(dto.getFechaFin());
        r.setDocente(docente);
        r.setAula(aula);
        
        return entityToDto(reservaRepository.save(r));
    }

    public ReservaDTO entityToDto(Reserva r) {
        return new ReservaDTO(r.getId(), r.getFechaReserva(), r.getFechaInicio(), 
                              r.getFechaFin(), r.getDocente().getId(), r.getAula().getCodigo());
    }
}