package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ValidacaoPetDisponivelTest {

    @InjectMocks
    private ValidacaoPetDisponivel validacaoPetDisponivel;
    @Mock
    private PetRepository petRepository;
    @Mock
    private Pet pet;

    @Test
    void deveriaValidarPetDisponivel() {

        BDDMockito
                .given(petRepository.getReferenceById(any()))
                .willReturn(pet);

        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(1L,1L, "");

        Assertions.assertDoesNotThrow(() -> validacaoPetDisponivel.validar(dto));
    }

    @Test
    void deveriaValidarPetNaoDisponivel() {

        BDDMockito
                .given(petRepository.getReferenceById(any()))
                .willReturn(pet);

        BDDMockito
                .given(pet.getAdotado())
                .willReturn(true);

        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(1L,1L, "");

        Assertions.assertThrows(ValidacaoException.class, () -> validacaoPetDisponivel.validar(dto));
    }

}
