package br.com.sus.resource;

import br.com.sus.dto.TriagemDTO;
import br.com.sus.model.Paciente;
import br.com.sus.model.Triagem;
import br.com.sus.service.TriagemService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Path("/triagens")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Triagens")
public class TriagemResource {

    @Inject
    TriagemService triagemService;

    @POST
    @Transactional
    public TriagemDTO criarTriagem(TriagemDTO dto) {
        Paciente paciente = Paciente.findById(dto.pacienteId);
        if (paciente == null) throw new NotFoundException("Paciente não encontrado");

        Triagem triagem = new Triagem();
        triagem.paciente = paciente;
        triagem.sintomas = dto.sintomas;
        triagem.classificacao = triagemService.classificar(dto.sintomas, paciente.idade);
        triagem.dataHora = LocalDateTime.now();
        triagem.persist();

        dto.id = triagem.id;
        dto.classificacao = triagem.classificacao;
        dto.dataHora = triagem.dataHora;
        return dto;
    }

    @GET
    public List<TriagemDTO> listar() {
        return Triagem.<Triagem>listAll().stream().map(t -> {
            TriagemDTO dto = new TriagemDTO();
            dto.id = t.id;
            dto.pacienteId = t.paciente.id;
            dto.sintomas = t.sintomas;
            dto.classificacao = t.classificacao;
            dto.dataHora = t.dataHora;
            return dto;
        }).collect(Collectors.toList());
    }
}
