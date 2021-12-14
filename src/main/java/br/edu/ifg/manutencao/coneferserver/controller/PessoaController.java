package br.edu.ifg.manutencao.coneferserver.controller;

import br.edu.ifg.manutencao.coneferserver.entity.Pessoa;
import br.edu.ifg.manutencao.coneferserver.exception.ConeferException;
import br.edu.ifg.manutencao.coneferserver.exception.InfoMessage;
import br.edu.ifg.manutencao.coneferserver.model.request.PessoaRequest;
import br.edu.ifg.manutencao.coneferserver.model.response.PessoaResponse;
import br.edu.ifg.manutencao.coneferserver.service.PessoaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaController extends GenericoController<Pessoa> {

    private final PessoaService grupoDocumentoService;

    @Autowired
    public PessoaController(PessoaService grupoDocumentoService) {
        this.grupoDocumentoService = grupoDocumentoService;
    }

    @GetMapping(value = "/lista-page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Realiza a listagem das pessoas")
    public ResponseEntity<?> listaPaginadoNew(@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
                                              Pageable pageable) {
        return ResponseEntity.ok().body(this.getService().listarTodosPage(nome, pageable));
    }

    @GetMapping(value = "/{uuid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Realiza a consulta de uma pessoa")
    public ResponseEntity<PessoaResponse> consultar(@PathVariable String uuid){
        PessoaResponse grupoDocumentoResponse = this.getService().consultarPeloUuid(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(grupoDocumentoResponse);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Realiza a criação de uma pessoa")
    public ResponseEntity<PessoaResponse> salvar(@Valid @RequestBody PessoaRequest pessoaRequest){
        PessoaResponse entidadeModeloCriada = this.getService().salvar(pessoaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(entidadeModeloCriada);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Realiza a atualização de uma pessoa")
    public ResponseEntity<PessoaResponse> atualizar(@Valid @RequestBody PessoaRequest pessoaRequest) throws ConeferException {
        PessoaResponse modeloDocumentoCriado = this.getService().atualizar(pessoaRequest);
        return ResponseEntity.status(HttpStatus.OK).body(modeloDocumentoCriado);
    }

    @DeleteMapping(value = "/{uuid}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Realiza a exclusão de uma pessoa")
    public ResponseEntity<InfoMessage> excluir(@PathVariable String uuid) throws ConeferException {
        this.getService().excluirPeloId(uuid);
        InfoMessage excluidoComSucesso = InfoMessage.builder().status(HttpStatus.OK.value()).mensagem("Excluído com sucesso").build();
        return ResponseEntity.status(HttpStatus.OK).body(excluidoComSucesso);
    }

    @Override
    public PessoaService getService() {
        return this.grupoDocumentoService;
    }
}
