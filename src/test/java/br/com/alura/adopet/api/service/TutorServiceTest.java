package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizacaoTutorDto;
import br.com.alura.adopet.api.dto.CadastroTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class TutorServiceTest {

    @InjectMocks
    private TutorService tutorService;
    @Mock
    private TutorRepository repository;
    @Captor
    private ArgumentCaptor<Tutor> tutorCaptor;
    @Mock
    private Tutor tutor;

    @Test
    void deveriaCadastrarTutor() {

        CadastroTutorDto cadastroTutor = new CadastroTutorDto(
                "Tutor Jao",
                "9925874103",
                "jao@email.com");

        this.tutorService.cadastrar(cadastroTutor);

        then(repository).should().save(tutorCaptor.capture());

        Tutor tutor = tutorCaptor.getValue();
        assertEquals(tutor.getNome(), cadastroTutor.nome());
    }

    @Test
    void deveriaValidaTutorJaCadastradp() {

        CadastroTutorDto cadastroTutor = new CadastroTutorDto(
                "Tutor Jao",
                "9925874103",
                "jao@email.com");

        given(repository.existsByTelefoneOrEmail(cadastroTutor.telefone(), cadastroTutor.email()))
                .willReturn(true);

        assertThrows(ValidacaoException.class, () -> this.tutorService.cadastrar(cadastroTutor));
    }

    @Test
    void deveriaAtualizarTutorJaCadastrado() {

        AtualizacaoTutorDto atualizacaoTutor = new AtualizacaoTutorDto(1L,
                "Tutor Jao",
                "9925874103",
                "jao@email.com");

        given(repository.getReferenceById(atualizacaoTutor.id())).willReturn(tutor);

        this.tutorService.atualizar(atualizacaoTutor);

        then(tutor).should().atualizarDados(atualizacaoTutor);
    }

}
