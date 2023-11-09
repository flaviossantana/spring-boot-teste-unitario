package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoPetDisponivel;
import br.com.alura.adopet.api.validacoes.ValidacaoSolicitacaoAdocao;
import br.com.alura.adopet.api.validacoes.ValidacaoTutorComAdocaoEmAndamento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AdocaoServiceTest {

    @InjectMocks
    private AdocaoService  adocaoService;
    @Mock
    private AdocaoRepository adocaoRepository;
    @Mock
    private PetRepository petRepository;
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private EmailService emailService;
    @Spy
    private List<ValidacaoSolicitacaoAdocao> validacaoSolicitacaoAdocao = new ArrayList<>();
    @Mock
    private ValidacaoTutorComAdocaoEmAndamento validacaoTutorComAdocaoEmAndamento;
    @Mock
    private ValidacaoPetDisponivel validacaoPetDisponivel;
    @Mock
    private Pet pet;
    @Mock
    private Abrigo abrigo;
    @Mock
    private Tutor tutor;
    @Captor
    private ArgumentCaptor<Adocao> adocaoCaptor;

    @Test
    void deveriaSalvarSolicitarAdocao() {

        SolicitacaoAdocaoDto solicitacaoAdocao = new SolicitacaoAdocaoDto(1L, 1L, "motivo");

        this.validacaoSolicitacaoAdocao.add(validacaoPetDisponivel);
        this.validacaoSolicitacaoAdocao.add(validacaoTutorComAdocaoEmAndamento);

        given(petRepository.getReferenceById(solicitacaoAdocao.idPet()))
                .willReturn(pet);
        given(tutorRepository.getReferenceById(solicitacaoAdocao.idTutor()))
                .willReturn(tutor);
        given(pet.getAbrigo())
                .willReturn(abrigo);


        this.adocaoService.solicitar(solicitacaoAdocao);


        then(adocaoRepository).should().save(adocaoCaptor.capture());
        Adocao adocao = adocaoCaptor.getValue();
        assertEquals(pet, adocao.getPet());
        assertEquals(tutor, adocao.getTutor());
        assertEquals(solicitacaoAdocao.motivo(), adocao.getMotivo());


        then(validacaoPetDisponivel).should().validar(solicitacaoAdocao);
        then(validacaoTutorComAdocaoEmAndamento).should().validar(solicitacaoAdocao);

    }

    @Test
    void deveriaValidarSolicitarAdocao() {

        SolicitacaoAdocaoDto solicitacaoAdocao = new SolicitacaoAdocaoDto(1L, 1L, "motivo");

        this.validacaoSolicitacaoAdocao.add(validacaoPetDisponivel);
        this.validacaoSolicitacaoAdocao.add(validacaoTutorComAdocaoEmAndamento);

        given(petRepository.getReferenceById(solicitacaoAdocao.idPet()))
                .willReturn(pet);
        given(tutorRepository.getReferenceById(solicitacaoAdocao.idTutor()))
                .willReturn(tutor);
        given(pet.getAbrigo())
                .willReturn(abrigo);


        this.adocaoService.solicitar(solicitacaoAdocao);


        then(validacaoPetDisponivel).should().validar(solicitacaoAdocao);
        then(validacaoTutorComAdocaoEmAndamento).should().validar(solicitacaoAdocao);

    }
}
