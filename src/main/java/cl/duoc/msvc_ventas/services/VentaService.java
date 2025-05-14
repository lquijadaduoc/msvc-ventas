package cl.duoc.msvc_ventas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.msvc_ventas.model.interfaces.DetalleVentaInterface;
import cl.duoc.msvc_ventas.repositories.DetalleVentaRepository;

@Service
public class VentaService {
    
    @Autowired
    private DetalleVentaRepository repository;

    public List<DetalleVentaInterface> obtenerDetallePorNumeroVenta(Integer numeroVenta){
        return repository.obtenerDetallePorNumeroVenta(numeroVenta);
    }
}
