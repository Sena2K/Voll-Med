package med.voll.api.controller;

import med.voll.api.medico.DadosCadastroMedicos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private static final Logger logger = LoggerFactory.getLogger(MedicoController.class);

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroMedicos dados) {
        logger.info("Dados recebidos: {}", dados);
    }
}
