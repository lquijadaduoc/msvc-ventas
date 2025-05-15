package cl.duoc.msvc_ventas.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cl.duoc.msvc_ventas.model.Venta;

public interface VentaRepository extends CrudRepository<Venta,Integer>{
    List<Venta> findByFechaVenta(LocalDate fechaVenta);
}
