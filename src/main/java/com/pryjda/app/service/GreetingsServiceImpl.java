package com.pryjda.app.service;

import com.pryjda.app.model.Greetings;
import com.pryjda.app.stream.GreetingsStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
@Slf4j
public class GreetingsServiceImpl implements GreetingsService {

    private final GreetingsStreams greetingsStreams;

    @Autowired
    public GreetingsServiceImpl(GreetingsStreams greetingsStreams) {
        this.greetingsStreams = greetingsStreams;
    }

    @Override
    public void sendGreeting(final Greetings greetings) {

        log.info("Sending greetings {}", greetings);
        MessageChannel messageChannel = greetingsStreams.outboundGreetings();
        messageChannel.send(MessageBuilder
                .withPayload(greetings)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}
