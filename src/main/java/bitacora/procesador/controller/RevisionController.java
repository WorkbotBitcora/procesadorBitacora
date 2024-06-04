package bitacora.procesador.controller;

import bitacora.procesador.domain.Revision;
import bitacora.procesador.repository.IRevisionesRepository;
import bitacora.procesador.service.pruebas.PuebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ALUCARD")
public class RevisionController {


    @Autowired
    private PuebaService puebaService;


    @PostMapping("/")
    public String alucard() {
        return puebaService.GuardarRevision();
    }


    @GetMapping
    public List<Revision> getRevision() {
        return puebaService.obtenerRevisiones();
    }

}
