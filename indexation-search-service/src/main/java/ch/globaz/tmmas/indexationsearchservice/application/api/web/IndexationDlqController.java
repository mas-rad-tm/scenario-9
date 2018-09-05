package ch.globaz.tmmas.indexationsearchservice.application.api.web;

import ch.globaz.tmmas.indexationsearchservice.infrastructure.dto.DlqDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dlq")
public class IndexationDlqController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IndexationDlqController.class);

	@Autowired
	private ApplicationContext appContext;

	@RequestMapping(value = "/start",method = RequestMethod.POST)
	public ResponseEntity<String> createRentesSampleData(@RequestBody DlqDto dto){
		LOGGER.debug("Starting dlq batch");

		LOGGER.info("Activate listening DLQ");

		JmsListenerEndpointRegistry registry = appContext.getBean(org.springframework.jms.config.JmsListenerEndpointRegistry
				.class);

		registry.getListenerContainer("dlq.q").start();

		LOGGER.info("DLQ queue listened");

		return new ResponseEntity<String>("Process DLQ Started", HttpStatus.OK);
	}

}
