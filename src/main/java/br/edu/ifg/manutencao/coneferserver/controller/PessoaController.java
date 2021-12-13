package br.edu.ifg.manutencao.coneferserver.controller;

import br.edu.ifg.manutencao.coneferserver.entity.Pessoa;
import br.edu.ifg.manutencao.coneferserver.model.response.PessoaResponse;
import br.edu.ifg.manutencao.coneferserver.service.GenericoService;
import br.edu.ifg.manutencao.coneferserver.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class PessoaController extends GenericoController{

    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService){
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<PessoaResponse>> listar(){
        return ResponseEntity.status(HttpStatus.OK).body(this.pessoaService.listarDataEqualsNull());
    }

    @Override
    public GenericoService<Pessoa> getService() { return pessoaService; }
}
