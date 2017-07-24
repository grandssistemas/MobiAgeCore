package br.com.codein.mobiagecore.api.certificado;

import br.com.codein.mobiagecore.application.service.certificado.CertificadoService;
import br.com.codein.mobiagecore.domain.exceptions.CertificateException;
import br.com.codein.mobiagecore.domain.model.certificado.Certificado;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gumga.application.GumgaService;
import io.gumga.presentation.GumgaAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;

@RestController
@RequestMapping("/api/certificado")
public class CertificadoAPI extends GumgaAPI<Certificado, Long> {

    private CertificadoService service;

    @Autowired
    public CertificadoAPI(CertificadoService service) {
        super(service);
        this.service = service;
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @Transactional
    public Certificado upload(MultipartHttpServletRequest request) {

        MultipartFile file = request.getFile("file");
        byte[] bytes;
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            throw new CertificateException("Error on opening the certificate");
        }
        String password = request.getParameter("password");

        return service.upload(bytes, password);
    }
}
