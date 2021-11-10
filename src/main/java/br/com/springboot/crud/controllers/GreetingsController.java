package br.com.springboot.crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.crud.model.Usuario;
import br.com.springboot.crud.repository.UsuarioRepository;


@RestController
public class GreetingsController {

	@Autowired 
	private UsuarioRepository usuarioRepository;
	
  
    @PostMapping(value = "salvar")
    @ResponseBody
    public ResponseEntity<Usuario> save (@RequestBody Usuario usuario){
    	
    Usuario user =	usuarioRepository.save(usuario);  
    return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    	
    }
    
    @PutMapping(value = "atualizar")
    @ResponseBody
    public ResponseEntity<?> update	 (@RequestBody Usuario usuario){
    	if (usuario.getId() == null) {
    		return new ResponseEntity<String>("Id não informada para atualização.", HttpStatus.OK);
    	}
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    	
    }
    
    @DeleteMapping(value = "delete")
    @ResponseBody
    public ResponseEntity<String> delete (@RequestParam Long iduser){
    	
    	usuarioRepository.deleteById(iduser); 	
    	return new ResponseEntity<String>("Usuario deletado com sucesso!", HttpStatus.OK);
    	
    }
    
    @GetMapping(value = "listartodos")
    @ResponseBody /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<List<Usuario>> listUsuario(){
    	
    	List<Usuario> usuarios = usuarioRepository.findAll(); /*Executa a consulta no banco de dados*/   	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    	
    }
    
    @GetMapping(value = "buscaruserid")
    @ResponseBody
    public ResponseEntity<Usuario> searchuserid(@RequestParam (name = "iduser") Long iduser){
    	
    	Usuario usuario = usuarioRepository.findById(iduser).get(); 	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    	
    }
    
    @GetMapping(value = "buscarpornome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> searchbyname(@RequestParam (name = "name") String name){
    	List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase()); /*Ignora buscar por letras Maiuscula e espaçamento*/
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }
}
