package cl.duoc.msvc_ventas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cl.duoc.msvc_ventas.model.DetalleVenta;
import cl.duoc.msvc_ventas.model.claves.DetalleVentaId;
import cl.duoc.msvc_ventas.model.interfaces.DetalleVentaInterface;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta,DetalleVentaId>{
    @Query(value = """
        SELECT 
            dv.numero_venta AS numeroVenta,
            dv.id_producto AS idProducto,
            p.nombre_producto AS nombreProducto,
            v.fecha_venta AS fechaVenta,
            dv.precio AS precioUnitario,
            (dv.precio * dv.cantidad) AS precioTotal
        FROM detalle_venta dv
        JOIN venta v ON dv.numero_venta = v.numero_venta
        JOIN baseproductos.producto p ON dv.id_producto = p.id_producto
        WHERE dv.numero_venta = :numeroVenta
        """, nativeQuery = true)
    List<DetalleVentaInterface> obtenerDetallePorNumeroVenta(@Param("numeroVenta") Integer numeroVenta);
}
