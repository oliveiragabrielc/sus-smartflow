package br.com.sus.resource;

import br.com.sus.dto.PacienteDTO;
import br.com.sus.dto.TriagemDTO;
import br.com.sus.model.Paciente;
import br.com.sus.model.Triagem;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;

@Path("/pacientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Pacientes")
public class PacienteResource {

    @POST
    @Transactional
    public PacienteDTO criar(PacienteDTO dto) {
        Paciente paciente = new Paciente();
        paciente.nome = dto.nome;
        paciente.cpf = dto.cpf;
        paciente.idade = dto.idade;
        paciente.doencasPreExistentes = dto.doencasPreExistentes;
        paciente.persist();

        dto.id = paciente.id;
        return dto;
    }

    @GET
    public List<PacienteDTO> listar() {
        return Paciente.<Paciente>listAll().stream().map(p -> {
            PacienteDTO dto = new PacienteDTO();
            dto.id = p.id;
            dto.nome = p.nome;
            dto.cpf = p.cpf;
            dto.idade = p.idade;
            dto.doencasPreExistentes = p.doencasPreExistentes;
            return dto;
        }).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public PacienteDTO buscar(@PathParam("id") Long id) {
        Paciente p = Paciente.findById(id);
        if (p == null) throw new NotFoundException("Paciente não encontrado");

        PacienteDTO dto = new PacienteDTO();
        dto.id = p.id;
        dto.nome = p.nome;
        dto.cpf = p.cpf;
        dto.idade = p.idade;
        dto.doencasPreExistentes = p.doencasPreExistentes;
        return dto;
    }

    // 🔥 HISTÓRICO DO PACIENTE
    @GET
    @Path("/{id}/historico")
    public List<TriagemDTO> historico(@PathParam("id") Long id) {
        Paciente p = Paciente.findById(id);
        if (p == null) throw new NotFoundException("Paciente não encontrado");

        return Triagem.<Triagem>list("paciente.id", id)
                .stream()
                .map(t -> {
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
