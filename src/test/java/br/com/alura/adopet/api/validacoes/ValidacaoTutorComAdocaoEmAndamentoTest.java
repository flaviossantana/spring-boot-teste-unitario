package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ValidacaoTutorComAdocaoEmAndamentoTest {

    @InjectMocks
    private ValidacaoTutorComAdocaoEmAndamento validacaoTutorComAdocaoEmAndamento;
    @Mock
    private AdocaoRepository adocaoRepository;
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private SolicitacaoAdocaoDto solicitacaoAdocaoDto;
    @Mock
    private Tutor tutor;
    @Mock
    private Pet pet;
    @Spy
    private List<Adocao> adocoes = new ArrayList<>();

    @Test
    void deveriaValidaTutorComAdocaoEmAndamentoTest() {


        Adocao primeiraAdocao = new Adocao(tutor, pet, "PRIMEIRA ADOÇÃO?");
        adocoes.add(primeiraAdocao);

        given(adocaoRepository.findAll()).willReturn(adocoes);
        given(tutorRepository.getReferenceById(tutor.getId())).willReturn(tutor);

        assertThrows(ValidacaoException.class, () -> this.validacaoTutorComAdocaoEmAndamento.validar(solicitacaoAdocaoDto));
    }

    @Test
    void deveriaValidaTutorSemAdocaoEmAndamentoTest() {

        assertDoesNotThrow(() -> this.validacaoTutorComAdocaoEmAndamento.validar(solicitacaoAdocaoDto));
    }

}
