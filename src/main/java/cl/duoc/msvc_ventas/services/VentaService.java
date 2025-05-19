package cl.duoc.msvc_ventas.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.msvc_ventas.model.DetalleVenta;
import cl.duoc.msvc_ventas.model.Venta;
import cl.duoc.msvc_ventas.model.claves.DetalleVentaId;
import cl.duoc.msvc_ventas.model.dto.DtoVentaRequest;
import cl.duoc.msvc_ventas.model.dto.DtoVentaResponse;
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

    public DetalleVentaInterface obtenerReporteFecha(LocalDate fecha){
        return repository.obtenerReportebyFecha(fecha);
    }

    @Transactional
    public void crearVentaConDetalles(DtoVentaRequest postVenta) {
        Venta venta = new Venta();
        venta.setFechaVenta(postVenta.getFechaVenta());
        venta.setCorreoCliente(postVenta.getCorreoCliente());
        venta.setEstadoVenta(postVenta.getEstadoVenta());
        venta.setIdBodega(postVenta.getIdBodega());
        venta.setIdUsuario(postVenta.getIdUsuario());

        repoVenta.save(venta);

        for (DtoVentaRequest.DetalleRequestVenta det : postVenta.getDetalles()) {
            DetalleVenta detalle = new DetalleVenta();
            DetalleVentaId id = new DetalleVentaId(venta.getNumeroVenta(), det.getIdProducto());
            detalle.setId(id);
            detalle.setCantidad(det.getCantidad());
            detalle.setPrecio(det.getPrecio());
            detalle.setVenta(venta);

            repository.save(detalle);
        }
    }

    public DtoVentaResponse obtenerVenta(Integer numeroVenta) {
        Venta venta = repoVenta.findById(numeroVenta)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        DtoVentaResponse dto = new DtoVentaResponse();
        dto.setNumeroVenta(venta.getNumeroVenta());
        dto.setFechaVenta(venta.getFechaVenta());
        dto.setCorreoCliente(venta.getCorreoCliente());
        dto.setEstadoVenta(venta.getEstadoVenta());
        dto.setIdBodega(venta.getIdBodega());
        dto.setIdUsuario(venta.getIdUsuario());

        List<DtoVentaResponse.DetalleResponseVenta> detalles = venta.getProductos().stream().map(det -> {
            DtoVentaResponse.DetalleResponseVenta d = new DtoVentaResponse.DetalleResponseVenta();
            d.setIdProducto(det.getId().getIdProducto());
            d.setCantidad(det.getCantidad());
            d.setPrecio(det.getPrecio());
            return d;
        }).toList();

        dto.setProductos(detalles);
        return dto;
    }

    public List<DtoVentaResponse> obtenerVentasPorFecha(LocalDate fecha) {
        List<Venta> ventas = repoVenta.findByFechaVenta(fecha);

        return ventas.stream().map(venta -> {
            DtoVentaResponse dto = new DtoVentaResponse();
            dto.setNumeroVenta(venta.getNumeroVenta());
            dto.setFechaVenta(venta.getFechaVenta());
            dto.setCorreoCliente(venta.getCorreoCliente());
            dto.setEstadoVenta(venta.getEstadoVenta());
            dto.setIdBodega(venta.getIdBodega());
            dto.setIdUsuario(venta.getIdUsuario());

            List<DtoVentaResponse.DetalleResponseVenta> detalles = venta.getProductos().stream().map(det -> {
                DtoVentaResponse.DetalleResponseVenta d = new DtoVentaResponse.DetalleResponseVenta();
                d.setIdProducto(det.getId().getIdProducto());
                d.setCantidad(det.getCantidad());
                d.setPrecio(det.getPrecio());
                return d;
            }).toList();

            dto.setProductos(detalles);
            return dto;
        }).toList();
    }

    public DtoVentaResponse eliminarVenta(Integer numeroVenta) {
        Venta venta = repoVenta.findById(numeroVenta)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        DtoVentaResponse dto = new DtoVentaResponse();
        dto.setNumeroVenta(venta.getNumeroVenta());
        dto.setFechaVenta(venta.getFechaVenta());
        dto.setCorreoCliente(venta.getCorreoCliente());
        dto.setEstadoVenta(venta.getEstadoVenta());
        dto.setIdBodega(venta.getIdBodega());
        dto.setIdUsuario(venta.getIdUsuario());

        List<DtoVentaResponse.DetalleResponseVenta> detalles = venta.getProductos().stream().map(det -> {
            DtoVentaResponse.DetalleResponseVenta d = new DtoVentaResponse.DetalleResponseVenta();
            d.setIdProducto(det.getId().getIdProducto());
            d.setCantidad(det.getCantidad());
            d.setPrecio(det.getPrecio());
            return d;
        }).toList();

        dto.setProductos(detalles);
        repoVenta.deleteById(numeroVenta);
        return dto;
    }

}
