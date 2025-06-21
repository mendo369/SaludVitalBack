package com.example.SaludVital.services;

import com.example.SaludVital.domain.entities.DetalleFactura;
import com.example.SaludVital.domain.repositories.DetalleFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleFacturaService {

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    // Crear un nuevo detalle de factura
    public DetalleFactura createDetalleFactura(DetalleFactura detalleFactura) {
        return detalleFacturaRepository.save(detalleFactura);
    }

    // Obtener todos los detalles de factura
    public List<DetalleFactura> getAllDetallesFactura() {
        return detalleFacturaRepository.findAll();
    }

    // Obtener un detalle de factura por ID
    public DetalleFactura getDetalleFacturaById(Integer id) {
        return detalleFacturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de factura no encontrado con ID: " + id));
    }

    // Actualizar un detalle de factura
    public DetalleFactura updateDetalleFactura(Integer id, DetalleFactura detalleFacturaDetails) {
        DetalleFactura detalleFactura = getDetalleFacturaById(id);

        detalleFactura.setTipoItem(detalleFacturaDetails.getTipoItem());
        detalleFactura.setIdReferencia(detalleFacturaDetails.getIdReferencia());
        detalleFactura.setDescripcion(detalleFacturaDetails.getDescripcion());
        detalleFactura.setCantidad(detalleFacturaDetails.getCantidad());
        detalleFactura.setPrecioUnitario(detalleFacturaDetails.getPrecioUnitario());
        detalleFactura.setSubtotal(detalleFacturaDetails.getSubtotal());

        return detalleFacturaRepository.save(detalleFactura);
    }

    // Eliminar un detalle de factura
    public void deleteDetalleFactura(Integer id) {
        if (!detalleFacturaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Detalle de factura no encontrado");
        }
        detalleFacturaRepository.deleteById(id);
    }
}