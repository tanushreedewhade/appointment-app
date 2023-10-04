package com.webapp.webapp.service;

import com.webapp.webapp.model.Appointment;
import com.webapp.webapp.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void addAppointment(Appointment appointment){
    appointmentRepository.insert(appointment);

    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public void updateAppointment(Appointment appointment){
        Appointment savedAppointment = appointmentRepository.findById(appointment.getId()).orElseThrow(() -> new RuntimeException(String.format("Cannot Find Appointment by ID %s", appointment.getId())));
        savedAppointment.setDoctorName(appointment.getDoctorName());
        savedAppointment.setAppointmentDate(appointment.getAppointmentDate());
        savedAppointment.setDoctorType(appointment.getDoctorType());
        savedAppointment.setPatientName(appointment.getPatientName());
        appointmentRepository.save(savedAppointment);
    }

    public void deleteAppointment(String id) {
        appointmentRepository.deleteById(id);
    }


}
