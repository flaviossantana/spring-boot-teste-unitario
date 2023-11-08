package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class ValidacaoPetDisponivelTest {

    @InjectMocks
    private ValidacaoPetDisponivel validacaoPetDisponivel;
    @Mock
    private PetRepository petRepository;

    @Test
    void deveriaValidarPetDisponivel() {

        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(1L,1L, "");

        Assertions.assertDoesNotThrow(() -> validacaoPetDisponivel.validar(dto));

    }
}
