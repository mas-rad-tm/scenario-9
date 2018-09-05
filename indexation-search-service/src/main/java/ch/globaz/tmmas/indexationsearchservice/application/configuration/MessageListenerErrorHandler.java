package ch.globaz.tmmas.indexationsearchservice.application.configuration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

public class MessageListenerErrorHandler implements ErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListenerErrorHandler.class);

    public void handleError(Throwable throwable) {

        LOGGER.error("Error during listening message",throwable);

    }
}
