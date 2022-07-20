package com.example.consumer.queueController;

import com.example.consumer.configuration.RpcConfig;
import com.example.consumer.dto.CodeRequest;
import com.example.consumer.dto.CodeResponse;
import com.example.consumer.executor.*;
import com.example.consumer.service.RedisService;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Component
class RpcServer {

    @Autowired
    RedisService redisService;
    @Autowired
    CodeExecutor codeExecutor;

    @SneakyThrows
    @RabbitListener(queues = RpcConfig.queueName)
    public String ExecuteCode(String request) throws InterruptedException {
        System.out.println("Received: " + request);
        Gson gson = new Gson();
        CodeRequest codeRequest = gson.fromJson(request, CodeRequest.class);
        System.out.println(codeRequest.toString());

        String language = codeRequest.getLanguage();
        CodeResponse response = null;
        System.out.println("langage : " + language);
        System.out.println(" test : " + Objects.equals(language, "java"));
        System.out.println("/////////////////1");
        response = codeExecutor.executeCode(codeRequest);

        if( response == null){

            response = new CodeResponse(UUID.randomUUID().toString(),
                    new ArrayList<>(),
                    new ArrayList<>(Arrays.asList("une erreur est survenue")),
                    "failed");
        }

        if (codeRequest.isTest()) {
            redisService.save(codeRequest.getId(), gson.toJson(response));
        } else {
            redisService.save(codeRequest.getId(), gson.toJson(response));
        }
        System.out.println("id du code de test" + codeRequest.getId().toString());

        return codeRequest.getId();

    }
}

