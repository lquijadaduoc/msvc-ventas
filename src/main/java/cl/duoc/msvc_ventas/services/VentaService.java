package cl.duoc.msvc_ventas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.msvc_ventas.model.DetalleVenta;
import cl.duoc.msvc_ventas.model.Venta;
import cl.duoc.msvc_ventas.model.claves.DetalleVentaId;
import cl.duoc.msvc_ventas.model.dto.DtoVentaPost;
import cl.duoc.msvc_ventas.model.interfaces.DetalleVentaInterface;
import cl.duoc.msvc_ventas.repositories.DetalleVentaRepository;
import cl.duoc.msvc_ventas.repositories.VentaRepository;
import jakarta.transaction.Transactional;

@Service
public class VentaService {
    
    @Autowired
    private DetalleVentaRepository repository;

    @Autowired
    private VentaRepository repoVenta;

    public List<DetalleVentaInterface> obtenerDetallePorNumeroVenta(Integer numeroVenta){
        return repository.obtenerDetallePorNumeroVenta(numeroVenta);
    }

    @Transactional
    public void crearVentaConDetalles(DtoVentaPost postVenta) {
        Venta venta = new Venta();
        venta.setFechaVenta(postVenta.getFechaVenta());
        venta.setCorreoCliente(postVenta.getCorreoCliente());
        venta.setEstadoVenta(postVenta.getEstadoVenta());
        venta.setIdBodega(postVenta.getIdBodega());
        venta.setIdUsuario(postVenta.getIdUsuario());

        repoVenta.save(venta);

        for (DtoVentaPost.DetalleRequestVenta det : postVenta.getDetalles()) {
            DetalleVenta detalle = new DetalleVenta();
            DetalleVentaId id = new DetalleVentaId(venta.getNumeroVenta(), det.getIdProducto());
            detalle.setId(id);
            detalle.setCantidad(det.getCantidad());
            detalle.setPrecio(det.getPrecio());
            detalle.setVenta(venta);

            repository.save(detalle);
        }
    }
}
