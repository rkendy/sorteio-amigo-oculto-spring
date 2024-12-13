package br.com.sawamura.sorteio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SorteioRunner implements CommandLineRunner {

    private final SorteioAmigoOculto sorteioAmigoOculto;

    public SorteioRunner(SorteioAmigoOculto sorteioAmigoOculto) {
        this.sorteioAmigoOculto = sorteioAmigoOculto;
    }

    @Override
    public void run(String... args) {
        sorteioAmigoOculto.realizaSorteio();
    }
    
}
