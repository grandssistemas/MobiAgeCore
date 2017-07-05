package br.com.codein.mobiagecore.api.marking;


import br.com.codein.mobiagecore.application.service.marking.MarkingService;
import br.com.codein.mobiagecore.domain.model.marking.Marking;
import io.gumga.presentation.GumgaAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by nakamura on 01/06/17.
 */
@RestController
@RequestMapping("/api/marking")
public class MarkingAPI extends GumgaAPI<Marking, Long> {

    private MarkingService service;

    @Autowired
    public MarkingAPI(MarkingService service) {
        super(service);
        this.service = service;
    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "/byorigin/{origin}", method = RequestMethod.GET)
    public List<Marking> findByOrigin(@PathVariable String origin){
        return service.findByOrigin(origin);
    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "/byoriginandvalue/{origin}/{value}", method = RequestMethod.GET)
    public List<Marking> findByOrigin(@PathVariable String origin, @PathVariable String value){
        return service.findByOriginAndValue(origin, value);
    }
}
