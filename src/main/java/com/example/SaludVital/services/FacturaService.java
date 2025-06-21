package com.example.SaludVital.services;

import com.example.SaludVital.domain.entities.Factura;
import com.example.SaludVital.domain.repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    // Crear una nueva factura
    public Factura createFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    // Obtener todas las facturas
    public List<Factura> getAllFacturas() {
        return facturaRepository.findAll();
    }

    // Obtener una factura por ID
    public Factura getFacturaById(Integer id) {
        return facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con ID: " + id));
    }

    // Actualizar una factura
    public Factura updateFactura(Integer id, Factura facturaDetails) {
        Factura factura = getFacturaById(id);

        factura.setNumeroFactura(facturaDetails.getNumeroFactura());
        factura.setPaciente(facturaDetails.getPaciente());
        factura.setCita(facturaDetails.getCita());
        factura.setFechaFactura(facturaDetails.getFechaFactura());
        factura.setSubtotal(facturaDetails.getSubtotal());
        factura.setDescuentos(facturaDetails.getDescuentos());
        factura.setImpuestos(facturaDetails.getImpuestos());
        factura.setTotal(facturaDetails.getTotal());
        factura.setEstadoPago(facturaDetails.getEstadoPago());
        factura.setFechaPago(facturaDetails.getFechaPago());
        factura.setMetodoPago(facturaDetails.getMetodoPago());
        factura.setObservaciones(facturaDetails.getObservaciones());

        return facturaRepository.save(factura);
    }

    // Eliminar una factura
    public void deleteFactura(Integer id) {
        if (!facturaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Factura no encontrada");
        }
        facturaRepository.deleteById(id);
    }
}