package br.com.api.login.mapper;


import br.com.api.login.model.Usuario;
import br.com.api.login.model.dto.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper {

    @Mappings({
            @Mapping(target = "usuarioCriacaoId", source = "usuarioCriacao.id"),
            @Mapping(target = "usuarioAlteracaoId", source = "usuarioAlteracao.id")
    })
    UsuarioDTO entityToDto(Usuario usuario);

    @Mappings({
            @Mapping(target = "usuarioCriacao.id", source = "usuarioCriacaoId"),
            @Mapping(target = "usuarioAlteracao.id", source = "usuarioAlteracaoId")
    })
    Usuario dtoToEntity(UsuarioDTO usuarioDTO);
}
