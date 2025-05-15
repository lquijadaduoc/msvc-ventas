package cl.duoc.msvc_ventas.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.msvc_ventas.model.dto.DtoVentaRequest;
import cl.duoc.msvc_ventas.model.dto.DtoVentaResponse;
import cl.duoc.msvc_ventas.services.VentaService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/ventas")
public class VentaController {

    @Autowired
    private VentaService service;

    @PostMapping
    public ResponseEntity<?> crearVenta(@RequestBody DtoVentaRequest postVenta) {
            service.crearVentaConDetalles(postVenta);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerVenta(@PathVariable Integer id) {
        try {
            DtoVentaResponse dto = service.obtenerVenta(id);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{fecha}")
    public ResponseEntity<List<DtoVentaResponse>> obtenerVentasPorFecha(@PathVariable String fecha) {
        try {
            LocalDate fechaVenta = LocalDate.parse(fecha); // Formato: yyyy-MM-dd
            List<DtoVentaResponse> ventas = service.obtenerVentasPorFecha(fechaVenta);
            return ResponseEntity.ok(ventas);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
