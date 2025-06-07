package cl.duoc.msvc_ventas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.duoc.msvc_ventas.model.DetalleVenta;
import cl.duoc.msvc_ventas.model.Venta;
import cl.duoc.msvc_ventas.model.claves.DetalleVentaId;
import cl.duoc.msvc_ventas.model.dto.DtoVentaRequest;
import cl.duoc.msvc_ventas.model.dto.DtoVentaResponse;
import cl.duoc.msvc_ventas.model.dto.DtoVentaResponse.DetalleResponseVenta;
import cl.duoc.msvc_ventas.model.dto.DtoVentaRequest.DetalleRequestVenta;
import cl.duoc.msvc_ventas.repositories.VentaRepository;
import cl.duoc.msvc_ventas.services.VentaService;

@ExtendWith(MockitoExtension.class)
public class VentaServiceTest {
    
    @Mock
    private VentaRepository repository;

    @InjectMocks
    private VentaService service;


    @Test
    void obtenerVentaTest(){

        Integer numeroVenta = 1;
        Venta ventaMock = new Venta();
        ventaMock.setNumeroVenta(numeroVenta);
        ventaMock.setFechaVenta(LocalDate.now());
        ventaMock.setCorreoCliente("cliente@example.com");
        ventaMock.setIdBodega(1);
        ventaMock.setIdUsuario(2);
        ventaMock.setEstadoVenta(1);

        DetalleVenta detalle = new DetalleVenta();

        DetalleVentaId id = new DetalleVentaId(1,2);

        detalle.setId(id);;
        detalle.setCantidad(2);
        detalle.setPrecio(150);
        ventaMock.addProducto(detalle);


        when(repository.findById(numeroVenta)).thenReturn(Optional.of(ventaMock));

        
        DtoVentaResponse resultadoResponse = service.obtenerVenta(numeroVenta);
        assertNotNull(resultadoResponse);
        assertEquals(ventaMock.getNumeroVenta(), resultadoResponse.getNumeroVenta());
        assertEquals(ventaMock.getCorreoCliente(), resultadoResponse.getCorreoCliente());
        assertEquals(ventaMock.getEstadoVenta(), resultadoResponse.getEstadoVenta());
        verify(repository).findById(numeroVenta);

    }
}
