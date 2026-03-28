package com.dasafio1.Consumindo_API.controller;

import com.dasafio1.Consumindo_API.model.PersonagemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

// Define que é um controller Rest, logo que seguiremos o Padrão esperado.
@RestController
// Define o caminho base dos Endpoints
@RequestMapping("/personagens")
public class PersonagemController {

    //Endpoint GET que consome a API externa.
    @GetMapping("/favoritos/{id}")
    public ResponseEntity<PersonagemDTO> buscarPersonagem(@PathVariable Long id){
        // Cria um Cliente HTTP (RestTemplate).
        RestTemplate template = new RestTemplate();
        // URL da API externa, passando o ID para cada personagem.
        String url = "https://swapi.dev/api/people/" + id + "/";
        try{
            // Fazendo a Requisição GET e convertendo para Objeto Java.
            PersonagemDTO personagem = template.getForObject(url, PersonagemDTO.class);
            // Verificando se a Resposta é Nula.
            if(personagem == null){
                return ResponseEntity.notFound().build();
            }
            // Retorna os dados com o Status HTTP 200 (ok).
            return ResponseEntity.ok(personagem);
        }catch(Exception e){
            // Tratando os erros caso ocorra e Retornando o Status BAD_GATEWAY (502)
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        }
    }
}
