package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CalculadoraProbabilidadeAdocaoTest {

    @Test
    @DisplayName("Testa Probabilidade de Adoção de Gato novo com pouco peso")
    void deveriaCalcularProbabilidadeAdocaoGatoAlta() {


        Pet pet = arrangePet(4, 4.0f);

        ProbabilidadeAdocao probabilidade = actCalculateProbability(pet);

        assertEquals(ProbabilidadeAdocao.ALTA, probabilidade);
    }

    private static ProbabilidadeAdocao actCalculateProbability(Pet pet) {
        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);
        return probabilidade;
    }

    private static Pet arrangePet(int idade, float peso) {
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
                "Abrigo feliz",
                "94999999999",
                "abrigofeliz@email.com.br"
        ));

        Pet pet = new Pet(new CadastroPetDto(
                TipoPet.GATO,
                "Miau",
                "Siames",
                idade,
                "Cinza",
                peso
        ), abrigo);
        return pet;
    }

    @Test
    @DisplayName("Testa Probabilidade de Adoção de Gato velho com pouco peso")
    void deveriaCalcularProbabilidadeAdocaoGatoMedia() {


        Pet pet = arrangePet(15, 4.0f);

        ProbabilidadeAdocao probabilidade = actCalculateProbability(pet);

        assertEquals(ProbabilidadeAdocao.MEDIA, probabilidade);
    }

    @Test
    @DisplayName("Testa Probabilidade de Adoção de Gato velho com muito peso")
    void deveriaCalcularProbabilidadeAdocaoGatoBaixa() {


        Pet pet = arrangePet(14, 11.0f);

        ProbabilidadeAdocao probabilidade = actCalculateProbability(pet);

        assertEquals(ProbabilidadeAdocao.BAIXA, probabilidade);
    }
}
