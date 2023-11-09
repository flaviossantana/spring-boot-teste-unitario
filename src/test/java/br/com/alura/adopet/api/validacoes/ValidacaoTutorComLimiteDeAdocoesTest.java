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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ValidacaoTutorComLimiteDeAdocoesTest {

    @InjectMocks
    private ValidacaoTutorComLimiteDeAdocoes validacaoTutorComLimiteDeAdocoes;
    @Mock
    private AdocaoRepository adocaoRepository;
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private SolicitacaoAdocaoDto solicitacaoAdocao;
    @Mock
    private Tutor tutor;
    @Mock
    private Pet pet;
    @Spy
    private List<Adocao> adocoes = new ArrayList<>();

    @Test
    void deveriaValidaTutorSemLimiteDeAdocoes() {

        Adocao primeiraAdocao = new Adocao(tutor, pet, "PRIMEIRA ADOÇÃO");
        primeiraAdocao.marcarComoAprovada();
        adocoes.add(primeiraAdocao);

        given(adocaoRepository.findAll()).willReturn(adocoes);
        given(tutorRepository.getReferenceById(tutor.getId())).willReturn(tutor);

        this.validacaoTutorComLimiteDeAdocoes.validar(solicitacaoAdocao);

        then(adocaoRepository).should().findAll();
        then(tutorRepository).should().getReferenceById(tutor.getId());
    }


    @Test
    void deveriaValidaTutorComLimiteDeAdocoes() {

        Adocao primeiraAdocao = new Adocao(tutor, pet, "PRIMEIRA ADOÇÃO");
        primeiraAdocao.marcarComoAprovada();
        adocoes.add(primeiraAdocao);
        adocoes.add(primeiraAdocao);
        adocoes.add(primeiraAdocao);
        adocoes.add(primeiraAdocao);
        adocoes.add(primeiraAdocao);
        adocoes.add(primeiraAdocao);

        given(adocaoRepository.findAll()).willReturn(adocoes);
        given(tutorRepository.getReferenceById(tutor.getId())).willReturn(tutor);

        assertThrows(ValidacaoException.class, () -> this.validacaoTutorComLimiteDeAdocoes.validar(solicitacaoAdocao));
    }
}
