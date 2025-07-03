package com.example.SaludVital.services;

import com.example.SaludVital.domain.entities.*;
import com.example.SaludVital.domain.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private EstadoCitaRepository estadoCitaRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ServicioAdicionalRepository servicioAdicionalRepository;

    @Autowired
    private ServicioPacienteRepository servicioPacienteRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    // ✅ CREAR una nueva cita
    public Cita createCita(Cita cita) {
        // Validar que el usuario exista
        if (cita.getUsuarioRegistro() == null ||
                !usuarioRepository.existsById(cita.getUsuarioRegistro().getIdUsuario())) {
            throw new RuntimeException("Usuario no válido o no encontrado");
        }

        // Establecer fecha de creación si no está definida
        if (cita.getFechaCreacion() == null) {
            cita.setFechaCreacion(LocalDateTime.now());
        }

        return citaRepository.save(cita);
    }

    // ✅ OBTENER todas las citas
    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    // ✅ OBTENER una cita por ID
    public Cita getCitaById(Integer id) {
        return citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + id));
    }

    public List<Cita> getCitasByPacienteId(Integer idPaciente) {
        return citaRepository.findByPaciente_IdPaciente(idPaciente);
    }

    // ✅ OBTENER citas por idUsuario del médico
    public List<Cita> getCitasByMedicoUsuarioId(Integer idUsuario) {
        // Buscar al médico usando el idUsuario
        Medico medico = medicoRepository.findByidUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado para el usuario con ID: " + idUsuario));

        // Devolver las citas asociadas a ese médico
        return citaRepository.findByMedico_IdMedico(medico.getIdMedico());
    }

    public Long countCanceledAppointments() {
        return citaRepository.countAllCanceledAppointments();
    }

    public List<Cita> getAllCanceledAppointments() {
        return citaRepository.findAllCanceledAppointments();
    }

    public List<Map<String, Object>> getMedicoCitasStats() {
        return citaRepository.countCitasByMedico();
    }

    public Cita updateCita(Integer id, Cita citaDetails) {
        Cita cita = getCitaById(id);

        // Solo actualizamos estadoCita si viene información
        if (citaDetails.getEstadoCita() != null && citaDetails.getEstadoCita().getIdEstado() != null) {
            Integer idEstado = citaDetails.getEstadoCita().getIdEstado();

            EstadoCita estadoCompleto = estadoCitaRepository.findById(idEstado)
                    .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

            cita.setEstadoCita(estadoCompleto); // Aquí asignas el objeto completo
        }

        // Actualiza otros campos solo si vienen datos nuevos
        if (citaDetails.getPaciente() != null) {
            cita.setPaciente(citaDetails.getPaciente());
        }
        if (citaDetails.getMedico() != null) {
            cita.setMedico(citaDetails.getMedico());
        }
        if (citaDetails.getTipoConsulta() != null) {
            cita.setTipoConsulta(citaDetails.getTipoConsulta());
        }
        if (citaDetails.getFechaCita() != null) {
            cita.setFechaCita(citaDetails.getFechaCita());
        }
        if (citaDetails.getHoraCita() != null) {
            cita.setHoraCita(citaDetails.getHoraCita());
        }
        if (citaDetails.getObservaciones() != null) {
            cita.setObservaciones(citaDetails.getObservaciones());
        }
        if (citaDetails.getCostoConsulta() != null) {
            cita.setCostoConsulta(citaDetails.getCostoConsulta());
        }
        if (citaDetails.getDescuentoAplicado() != null) {
            cita.setDescuentoAplicado(citaDetails.getDescuentoAplicado());
        }
        if (citaDetails.getCostoTotal() != null) {
            cita.setCostoTotal(citaDetails.getCostoTotal());
        }
        if (citaDetails.getUsuarioRegistro() != null &&
                usuarioRepository.existsById(citaDetails.getUsuarioRegistro().getIdUsuario())) {
            cita.setUsuarioRegistro(citaDetails.getUsuarioRegistro());
        }
        if (citaDetails.getHoraLlegada() != null) {
            cita.setHoraLlegada(citaDetails.getHoraLlegada());
        }

        return citaRepository.save(cita);
    }

    // Método para agregar servicios adicionales a una cita
    public void addAdditionalServicesToAppointment(Integer citaId, List<Integer> servicioIds, Map<Integer, Integer> cantidades) {
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + citaId));

        if (cita.getPaciente() == null || cita.getMedico() == null) {
            throw new RuntimeException("La cita debe tener un paciente y un médico asociados");
        }

        List<ServicioAdicional> serviciosValidos = servicioIds.stream()
                .map(id -> servicioAdicionalRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Servicio adicional no encontrado con ID: " + id)))
                .toList();

        for (ServicioAdicional servicio : serviciosValidos) {
            Integer cantidad = cantidades.getOrDefault(servicio.getIdServicio(), 1);
            Double subtotal = servicio.getCosto() * cantidad;

            ServicioPaciente servicioPaciente = new ServicioPaciente();
            servicioPaciente.setCita(cita);
            servicioPaciente.setServicioAdicional(servicio);
            servicioPaciente.setCantidad(cantidad);
            servicioPaciente.setFechaServicio(LocalDateTime.now());
            servicioPaciente.setCostoUnitario(servicio.getCosto());
            servicioPaciente.setCostoTotal(subtotal);
            servicioPaciente.setObservaciones("Servicio agregado durante la cita");

            // ✅ Asignar idPaciente desde la cita
            servicioPaciente.setIdPaciente(cita.getPaciente().getIdPaciente());

            servicioPacienteRepository.save(servicioPaciente);
        }
    }

    // Método para finalizar la cita y generar la factura
    public Factura finalizeAppointmentAndGenerateInvoice(Integer citaId) {
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + citaId));

        EstadoCita estadoFinalizado = estadoCitaRepository.findByNombreEstado("COMPLETADA")
                .orElseThrow(() -> new RuntimeException("Estado 'COMPLETADA' no encontrado"));

        // Actualizar estado de la cita
        cita.setEstadoCita(estadoFinalizado);
        citaRepository.save(cita);

        // Crear factura
        Factura factura = new Factura();
        factura.setNumeroFactura(generateUniqueInvoiceNumber());
        factura.setPaciente(cita.getPaciente());
        factura.setCita(cita);
        factura.setFechaFactura(LocalDateTime.now());

        Double subtotalBase = cita.getCostoTotal(); // Costo base de la consulta

        List<ServicioPaciente> serviciosPaciente = servicioPacienteRepository.findByCita_IdCita(citaId);

        for (ServicioPaciente sp : serviciosPaciente) {
            Double subtotalServicio = sp.getServicioAdicional().getCosto() * sp.getCantidad();

            DetalleFactura detalle = new DetalleFactura();
            detalle.setFactura(factura);
            detalle.setTipoItem("Servicio Adicional");
            detalle.setDescripcion(sp.getServicioAdicional().getNombreServicio());
            detalle.setCantidad(sp.getCantidad());
            detalle.setPrecioUnitario(sp.getServicioAdicional().getCosto());
            detalle.setSubtotal(subtotalServicio);


            detalleFacturaRepository.save(detalle);

            subtotalBase += subtotalServicio; // Acumular al total
        }

        factura.setSubtotal(subtotalBase);
        factura.setDescuentos(0.0); // Puedes implementar descuentos si es necesario
        factura.setImpuestos(calcularImpuestos(subtotalBase));
        factura.setTotal(factura.getSubtotal() + factura.getImpuestos());
        factura.setEstadoPago("PENDIENTE");

        return facturaRepository.save(factura);
    }

    // Método auxiliar para generar un número de factura único
    private String generateUniqueInvoiceNumber() {
        // Lógica para generar un número de factura único (puede ser basado en timestamp o secuencia)
        return "INV-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    // Método auxiliar para calcular impuestos
    private Double calcularImpuestos(Double subtotal) {
        // Ejemplo: Impuesto del 16%
        return subtotal * 0.16;
    }

    // ✅ ELIMINAR una cita
    public void deleteCita(Integer id) {
        if (!citaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Cita no encontrada");
        }
        citaRepository.deleteById(id);
    }
}