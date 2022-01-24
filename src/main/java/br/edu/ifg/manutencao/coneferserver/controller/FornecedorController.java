package br.edu.ifg.manutencao.coneferserver.controller;

import br.edu.ifg.manutencao.coneferserver.entity.Fornecedor;
import br.edu.ifg.manutencao.coneferserver.exception.ConeferException;
import br.edu.ifg.manutencao.coneferserver.exception.InfoMessage;
import br.edu.ifg.manutencao.coneferserver.model.request.FornecedorRequest;
import br.edu.ifg.manutencao.coneferserver.model.response.FornecedorResponse;
import br.edu.ifg.manutencao.coneferserver.service.FornecedorService;
import br.edu.ifg.manutencao.coneferserver.service.FornecedorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController extends GenericoController<Fornecedor> {

    private final FornecedorService fornecedorService;

    @Autowired
    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @GetMapping(value = "/lista-page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Realiza a listagem de fornecedores")
    public ResponseEntity<?> listaPaginadoNew(@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
                                              Pageable pageable) {
        return ResponseEntity.ok().body(this.getService().listarTodosPage(nome, pageable));
    }

    @GetMapping(value = "/{uuid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Realiza a consulta de um fornecedor")
    public ResponseEntity<Fornecedor> consultar(@PathVariable String uuid){
        Fornecedor fornecedor = this.getService().consultarPeloUuid(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(fornecedor);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Realiza a criação de um fornecedor")
    public ResponseEntity<Fornecedor> salvar(@Valid @RequestBody FornecedorRequest fornecedorRequest){
        Fornecedor fornecedor = this.getService().salvar(fornecedorRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedor);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Realiza a atualização de um fornecedor")
    public ResponseEntity<Fornecedor> atualizar(@Valid @RequestBody FornecedorRequest fornecedorRequest) throws ConeferException {
        Fornecedor fornecedor = this.getService().atualizar(fornecedorRequest);
        return ResponseEntity.status(HttpStatus.OK).body(fornecedor);
    }

    @DeleteMapping(value = "/{uuid}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Realiza a exclusão de um fornecedor")
    public ResponseEntity<InfoMessage> excluir(@PathVariable String uuid) throws ConeferException {
        this.getService().excluirPeloId(uuid);
        InfoMessage excluidoComSucesso = InfoMessage.builder().status(HttpStatus.OK.value()).mensagem("Excluído com sucesso").build();
        return ResponseEntity.status(HttpStatus.OK).body(excluidoComSucesso);
    }

    @Override
    public FornecedorService getService() {
        return this.fornecedorService;
    }
}
