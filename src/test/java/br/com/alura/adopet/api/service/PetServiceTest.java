package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @InjectMocks
    private PetService petService;
    @Mock
    private PetRepository repository;
    @Mock private Abrigo abrigo;
    @Mock
    private CadastroPetDto cadastroPet;
    @Captor
    private ArgumentCaptor<Pet> petCaptor;

    @Test
    void deveriaBuscarPetsDisponiveis() {

        List<PetDto> pets = this.petService.buscarPetsDisponiveis();

        assertNotNull(pets);
    }

    @Test
    void deveriaCadastrarPet() {

        this.petService.cadastrarPet(abrigo, cadastroPet);

        then(repository).should().save(petCaptor.capture());
    }
}
