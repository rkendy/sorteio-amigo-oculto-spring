package br.com.sawamura.sorteio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class ArquivoService {
    
    private static final String NOME_ARQUIVO = "participantes.txt";
    private final ResourceLoader resourceLoader;

    public ArquivoService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Stream<String> carregaArquivo() {
        try {
            Resource resource = resourceLoader.getResource("classpath:" + NOME_ARQUIVO);
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            return reader.lines()
                    .filter(line -> !"".equals(line) && !line.startsWith("#"));
        }
        catch (IOException e) {
            throw new RuntimeException("Erro ao ler arquivo: " + NOME_ARQUIVO, e);
        }
    }
}
