package com.avc.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.avc.model.Categoria;
import com.avc.model.Vacante;
import com.avc.service.ICategoriasService;
import com.avc.service.IVacantesService;
import com.avc.util.Utils;

@Controller
@RequestMapping(value="/vacantes")
public class VacantesController {
	
	@Value("${empleosapp.ruta.imagenes}")
	private String ruta;
	
	@Autowired
	private IVacantesService vacantesService;
	
	@Autowired
	@Qualifier("categoriasServiceJpa")
	private ICategoriasService categoriasService;

	@GetMapping("/index")
	public String mostrarIndex (Model model) {
		List<Vacante> lista = vacantesService.buscarTodas();
    	model.addAttribute("vacantes", lista);
		return "vacantes/listVacantes";
	}
	
	@GetMapping("/create")
	public String crear(Vacante vacante, Model model) {
		return "vacantes/formVacante";
	}
	
	
	@PostMapping("/save")
	public String guardar(Vacante vacante, 
			BindingResult result,
			RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart) {

		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrió un error: " + error.getDefaultMessage());
			}
			return "vacantes/formVacante";
		}
		
		if (!multiPart.isEmpty()) {
			//String ruta = "C:/Users/AVECH3H/Documents/EjemploEmpleos/img-vacantes/";
			String nombreImagen = Utils.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ // La imagen si se subio
				vacante.setImagen(nombreImagen);
			}
		}
		
		vacantesService.guardar(vacante);
		attributes.addFlashAttribute("msg", "Registro guardado");
		
		return "redirect:/vacantes/index";
	}
	
	/*
	@PostMapping("/save")
	public String guardar(@RequestParam("nombre") String nombre,
			@RequestParam("descripcion") String descripcion,
			@RequestParam("estatus") String estatus,
			@RequestParam("fecha") String fecha,
			@RequestParam("destacado") int destacado,
			@RequestParam("salario") double salario,
			@RequestParam("detalles") String detalles) {
		
		System.out.println("Nombre vacante: " + nombre);
		System.out.println("Descripcion: " + descripcion);
		System.out.println("Estatus: " + estatus);
		System.out.println("Fecha: " + fecha);
		System.out.println("Destacado: " + destacado);
		System.out.println("Salario: " + salario);
		System.out.println("Detalles: " + detalles);
		
		return "vacantes/listVacantes";
	}*/
	
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idVacante, 
			Model model,
			RedirectAttributes attributes) {
		vacantesService.eliminar(idVacante);
		attributes.addFlashAttribute("msg", "La vacante está eliminada");
		return "redirect:/vacantes/index";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idVacante, Model model) {
		Vacante vacante = vacantesService.buscarPorId(idVacante);
		model.addAttribute("vacante", vacante);
		return "vacantes/formVacante";
	}
	
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model model) {
		Vacante vacante = vacantesService.buscarPorId(idVacante);
		model.addAttribute("vacante", vacante);
		return "detalle";
	}

	@GetMapping("/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Vacante> lista = vacantesService.buscarTodas(page);
		model.addAttribute("vacantes", lista);
		return "vacantes/listVacantes";
	}
	
	@ModelAttribute
	public void setGenericos(Model model) {
		model.addAttribute("categorias", categoriasService.buscarTodas());
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
