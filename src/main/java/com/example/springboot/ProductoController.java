package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	@Autowired
	private ProductoRepository productoRepository;

	// Método para obtener todos los productos
	@GetMapping
	public List<Producto> obtenerTodosLosProductos() {
		return productoRepository.findAll();
	}

	// Método para obtener un producto por su ID
	@GetMapping("/{id}")
	public Producto obtenerProductoPorId(@PathVariable Long id) {
		return productoRepository.findById(id).orElse(null);
	}

	// Método para crear un nuevo producto
	@PostMapping
	public Producto crearProducto(@RequestBody Producto producto) {
		return productoRepository.save(producto);
	}

	// Método para actualizar un producto existente
	@PutMapping("/{id}")
	public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
		Producto producto = productoRepository.findById(id).orElse(null);
		if (producto != null) {
			producto.setNombre(productoActualizado.getNombre());
			producto.setPrecio(productoActualizado.getPrecio());
			producto.setCantidad(productoActualizado.getCantidad());
			producto.setCategoria(productoActualizado.getCategoria());
			return productoRepository.save(producto);
		}
		return null;
	}

	// Método para eliminar un producto por su ID
	@DeleteMapping("/{id}")
	public void eliminarProducto(@PathVariable Long id) {
		productoRepository.deleteById(id);
	}
}