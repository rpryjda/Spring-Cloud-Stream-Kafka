package com.pryjda.app.listener;

import com.pryjda.app.model.Greetings;
import com.pryjda.app.stream.GreetingsStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GreetingsListenerImpl implements GreetingListener {

    @StreamListener(GreetingsStreams.INPUT)
    @Override
    public void handleGreetings(@Payload Greetings greetings) {

        log.info("Received greetings: {}", greetings);
    }
}
