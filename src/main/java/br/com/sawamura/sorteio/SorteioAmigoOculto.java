package br.com.sawamura.sorteio;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SorteioAmigoOculto {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SorteioAmigoOculto.class);

    private final ArquivoService arquivoService;
    private final EmailService emailService;

    public SorteioAmigoOculto(ArquivoService arquivoService, EmailService emailService) {
        this.arquivoService = arquivoService;
        this.emailService = emailService;
    }

    @Value("${app.titulo}")
    private String titulo;

    private List<Participante> participantes;

    public void realizaSorteio() {
        carregaParticipantes();
        mistura();
        defineAmigosOcultos();
        imprime();
        // enviaEmails();
    }

    private void carregaParticipantes() {
        participantes = arquivoService.carregaArquivo()
            .map(str -> str.split(":"))
            .map(str -> new Participante(str[0].trim(), str[1].trim()))
            .collect(Collectors.toList());
    }

    private void mistura() {
        Collections.shuffle(this.participantes);
    }

    /**
     * O amigo oculto eh o proximo da lista. Para que seja aleatorio, a lista
     * precisa estar misturada.
     */
    private void defineAmigosOcultos() {
        int quantidade = this.participantes.size();
        for (int i = 0; i < quantidade; i++) {
            Participante sorteando = this.participantes.get(i);
            Participante sorteado = this.participantes.get((i + 1) % quantidade);
            sorteando.setAmigoOculto(sorteado);
        }
    }

    private void enviaEmails() {
        String texto = "Olá %s!. Bem-vindo ao " //
                + titulo + "!! 🎅🏼🎄🎁\n\n\n" //
                + "Seu amigo oculto será: %s" + "\n";

        this.participantes.forEach((p) -> {
            log.info("Enviando email para: {}", p.getEmail());
            String textoEmail = String.format(texto, p.getNome(), p.getAmigoOculto().getNome());
            emailService.sendEmail(p.getEmail(), titulo, textoEmail);
        });
    }

    private void imprime() {
        this.participantes.forEach((p) -> {
            log.info(
                "Nome: {}. Email: {}. Amigo: {}", p.getNome(), p.getEmail(), p.getAmigoOculto().getNome());
        });
    }

}
