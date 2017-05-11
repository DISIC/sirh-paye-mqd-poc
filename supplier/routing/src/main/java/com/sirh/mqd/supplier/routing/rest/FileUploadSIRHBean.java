package com.sirh.mqd.supplier.routing.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sirh.mqd.commons.exchanges.enums.InteractionSirhEnum;
import com.sirh.mqd.commons.utils.exception.TechnicalException;
import com.sirh.mqd.supplier.core.constantes.CoreConstantes;
import com.sirh.mqd.supplier.core.sirh.SirhDgacService;
import com.sirh.mqd.supplier.core.sirh.SirhMsoService;

/**
 * Bean permettant la réponse à un GET restfull d'une demande de log d'echange.
 * <br/>
 * <b>Usage : </b><br/>
 * <i>curl http://localhost:8080/upload/datasource/mso -F
 * "source1=@/home/test.csv"</i>
 *
 * @author alexandre
 */
@ControllerAdvice
@RestController
public class FileUploadSIRHBean {

	/**
	 * Service de gestion des inputs du SIRH MSO
	 */
	@Autowired
	@Qualifier(CoreConstantes.SIRH_MSO_SERVICE)
	private SirhMsoService sirhMsoService;

	/**
	 * Service de gestion des inputs du SIRH DGAC
	 */
	@Autowired
	@Qualifier(CoreConstantes.SIRH_DGAC_SERVICE)
	private SirhDgacService sirhDgacService;

	@PostMapping("/upload/datasource/{sirh}")
	@ResponseBody
	public ResponseEntity<String> uploadSIRHDatasource(@RequestParam("sirh") final String sirh,
			@RequestParam("file") final MultipartFile file) throws TechnicalException {
		try {
			if (InteractionSirhEnum.MSO.getLibelle().equals(sirh)) {
				sirhMsoService.dropSIRHDatasource(file.getInputStream());
			} else if (InteractionSirhEnum.DGAC.getLibelle().equals(sirh)) {
				sirhDgacService.dropSIRHDatasource(file.getInputStream());
			} else {
				return ResponseEntity.badRequest().body("No SIRH called " + sirh + " found in MQD application.");
			}
			return ResponseEntity.ok().body("SIRH importation achieved with success.");
		} catch (final IOException e) {
			throw new TechnicalException(e);
		}
	}

	@ExceptionHandler(TechnicalException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> handleTechnicalException(final TechnicalException exc) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
