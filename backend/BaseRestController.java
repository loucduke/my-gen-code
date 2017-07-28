package backend;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ec.dao.GenericDAO;

import groovy.lang.Binding;


public abstract class BaseRestController<E> {

	@Autowired
	protected GenericDAO<E> genericDAO;
	
	protected Class<E> clazz;
	
	
	public BaseRestController(Class<E> clazz) {
		this.clazz = clazz;
	}
	
	@PostConstruct
	public void init(){
		genericDAO.setClazz(clazz);
	}	
	
	@RequestMapping(value = {"/", ""})
	public @ResponseBody List<E> getAll(){
		
		preGetAll();
		List<E> list = genericDAO.findAll();
		postGetAll(list);
		
		return list;
	}
	
	@RequestMapping(value = "/paged/{limit}/{page}")
	public @ResponseBody List<E> getAllPaged(
			@PathVariable Integer limit,
			@PathVariable Integer page){
		
		preGetAll();
		List<E> list = genericDAO.findAllPaged(limit, page);
		postGetAll(list);
		
		return list;
	}
	
	

	@RequestMapping(value = "/{id}")
	public @ResponseBody E get(
			@PathVariable Long id,
			Model model){
		preGet(model, id);
		
		E item = genericDAO.find(id);
		model.addAttribute("item", item);
		postGet(model, id, item);
		
		return item;
	}
	

	@RequestMapping(value = "/create")
	public @ResponseBody E create(
			Model model) throws InstantiationException, IllegalAccessException{
		E e = clazz.newInstance();
		
		preCreate(model, e);
		model.addAttribute("newItem", e);
		postCreate(model, e);
		
		return e;
	}	


	@RequestMapping(value = "/edit/{id}")
	public @ResponseBody E edit(
			@PathVariable Long id,
			Model model){
		
		preEdit(model, id);
		E item = genericDAO.find(id);
		model.addAttribute("item", item);
		postEdit(model, id, item);
		
		return item;
	}	


	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity save(
			@RequestBody @Valid E item, BindingResult result){

		preSave(item);
		if(result.hasErrors()){
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.OK);
		}
		
		genericDAO.save(item);
		postSave(item);
		
		return new ResponseEntity<E>(item, HttpStatus.OK);
	}	


	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity update(
			@RequestBody E item,
			BindingResult result){
		
		preUpdate(item);

		if(result.hasErrors()){
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.OK);
		}		
		
		genericDAO.update(item);
		postUpdate(item);
		
		return new ResponseEntity<>(item, HttpStatus.OK);
	}	

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<GenericResponse> delete(
			@PathVariable Long id,
			Model model){
		
		preDelete(model, id);
		E item = genericDAO.deleteById(id);
		postDelete(model, id);
		
		return (ResponseEntity<GenericResponse>) ResponseEntity.ok().body(new GenericResponse("Success"));
	}
	
	protected void postDelete(Model model, Long id) {
		// TODO Auto-generated method stub
		
	}

	protected void preDelete(Model model, Long id) {
		// TODO Auto-generated method stub
		
	}

	protected void postUpdate(E item) {
		// TODO Auto-generated method stub
		
	}
	
	protected void preUpdate(E item) {
		// TODO Auto-generated method stub
		
	}
	
	protected void postSave(E item) {
		// TODO Auto-generated method stub
		
	}
	
	protected void preSave(E item) {
		// TODO Auto-generated method stub
		
	}
	
	private void postEdit(Model model, Long id, E item) {
		// TODO Auto-generated method stub
		
	}
	
	private void preEdit(Model model, Long id) {
		// TODO Auto-generated method stub
		
	}
	
	protected void postCreate(Model model, E e) {
		// TODO Auto-generated method stub
		
	}
	
	protected void preCreate(Model model, E e) {
		// TODO Auto-generated method stub
		
	}
	
	protected void postGetAll(List<E> list) {
		// TODO Auto-generated method stub
		
	}
	
	protected void preGetAll() {
		// TODO Auto-generated method stub
		
	}
	
	protected void postGet(Model model, Long id, E item) {
		// TODO Auto-generated method stub
		
	}
	
	protected void preGet(Model model, Long id) {
		// TODO Auto-generated method stub
		
	}	
	
	public class GenericResponse{
		
		private String message;
		
		public GenericResponse() {
			// TODO Auto-generated constructor stub
		}

		public GenericResponse(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
	} 
	
}
