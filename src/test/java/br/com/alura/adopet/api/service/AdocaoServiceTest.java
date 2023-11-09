package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoSolicitacaoAdocao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    @Mock
    private List<ValidacaoSolicitacaoAdocao> validacoes;
    @Mock
    private Pet pet;
    @Mock
    private Abrigo abrigo;
    @Mock
    private Tutor tutor;
    @Captor
    private ArgumentCaptor<Adocao> adocaoCaptor;

    @Test
    void deveriaSolicitarAdocao() {

        SolicitacaoAdocaoDto solicitacaoAdocao = new SolicitacaoAdocaoDto(1L, 1L, "motivo");

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
    }
}
