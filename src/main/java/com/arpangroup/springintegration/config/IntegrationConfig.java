package com.arpangroup.springintegration.config;

import com.arpangroup.springintegration.integration.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.*;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;

import java.io.File;

@Configuration
public class IntegrationConfig {

    @Autowired
    private Transformer transformer;

    @Bean
    public IntegrationFlow integrationFlow(){
        return IntegrationFlows.from(fileReader(),
                spec -> spec.poller(Pollers.fixedDelay(1000)))// 1millisecond <==It can be configurable
                .transform(transformer, "transformStringToJson") //transformData <==This method convert CSV input to JSON output
                .handle(fileWriter())
                .get();
    }

    @Bean
    public FileWritingMessageHandler fileWriter(){
        FileWritingMessageHandler handler = new FileWritingMessageHandler(
                new File("destination") //this "destination" directory name could also be dynamically configurable from property file, for time being I just hard coded the directory name
        );
        handler.setExpectReply(false);
        return handler;
    }

    @Bean
    public FileReadingMessageSource fileReader(){
        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setDirectory(new File("source"));//this "destination" source name could also be dynamically configurable from property file, for time being I just hard coded the directory name
        return source;
    }
}
