package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ValidacaoPetComAdocaoEmAndamentoTest {

    @InjectMocks
    private ValidacaoPetComAdocaoEmAndamento validacaoPetComAdocaoEmAndamento;
    @Mock
    private AdocaoRepository adocaoRepository;
    @Mock
    private SolicitacaoAdocaoDto solicitacaoAdocaoDto;


    @Test
    void deveriaValidacaoPetComAdocaoEmAndamento() {

        given(adocaoRepository.existsByPetIdAndStatus(solicitacaoAdocaoDto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO))
                .willReturn(true);

         assertThrows(ValidacaoException.class, () -> this.validacaoPetComAdocaoEmAndamento.validar(solicitacaoAdocaoDto));

    }

    @Test
    void deveriaValidacaoPetSemAdocaoEmAndamento() {

        assertDoesNotThrow(() -> this.validacaoPetComAdocaoEmAndamento.validar(solicitacaoAdocaoDto));

    }

}
