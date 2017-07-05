package br.com.codein.mobiagecore.application.service.marking;

import br.com.codein.mobiagecore.application.repository.marking.MarkingRepository;
import br.com.codein.mobiagecore.domain.model.marking.Marking;
import io.gumga.application.GumgaService;
import io.gumga.core.GumgaThreadScope;
import io.gumga.core.QueryObject;
import io.gumga.core.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

/**
 * Created by nakamura on 01/06/17.
 */
@Service
public class MarkingService extends GumgaService<Marking, Long> {

    private MarkingRepository repository;

    @Autowired
    public MarkingService(MarkingRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Marking> findByOrigin(String origin) {
        QueryObject query = new QueryObject();
        query.setAq(String.format("obj.origin = '%s' ", origin));
        query.setPageSize(Integer.MAX_VALUE);
        SearchResult<Marking> result = this.repository.search(query);
        return result.getValues();
    }


    @Transactional(readOnly = true)
    public List<Marking> findByOriginAndValue(String origin, String value) {
        QueryObject query = new QueryObject();
        query.setAq(String.format("obj.origin = '%s' and lower(obj.value) like '%%%s%%'", origin,value.toLowerCase()));
        SearchResult<Marking> result = this.repository.search(query);
        return result.getValues();
    }

    @Transactional(readOnly = true)
    public List<Marking> findByValue(String value) {
        QueryObject qo = new QueryObject();
        qo.setAq(String.format("obj.value = '%s'", value));
        return this.repository.search(qo).getValues();
    }

    @Transactional(readOnly = true)
    public Marking loadFat(Long id) {
        return repository.findOne(id);
    }

    @Transactional
    public Marking getOrCreate(Marking marking) {
        if (marking.getId() == null) {
            List<Marking> result = this.findByValue(marking.getValue());
            if (result.isEmpty()) {
                this.save(marking);
                return marking;
            } else {
                return result.get(0);
            }
        } else {
            return this.loadFat(marking.getId());
        }

    }
}

