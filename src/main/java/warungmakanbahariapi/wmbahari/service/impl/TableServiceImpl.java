package warungmakanbahariapi.wmbahari.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import warungmakanbahariapi.wmbahari.entity.Table;
import warungmakanbahariapi.wmbahari.model.request.TableRequest;
import warungmakanbahariapi.wmbahari.repository.TableRepository;
import warungmakanbahariapi.wmbahari.service.TableService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {
    private final TableRepository tableRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Table create(Table request) {
        Table table = Table.builder()
                .type(request.getType())
                .build();

        return tableRepository.save(table);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Table> getAll(TableRequest request) {

        Specification<Table> tableSpecification = getTableSpecification(request);
        return tableRepository.findAll(tableSpecification);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Table getById(String id) {
        Optional<Table> byId = tableRepository.findById(id);

        return byId.orElseThrow();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Table update(Table request) {
        Optional<Table> byId = tableRepository.findById(request.getId());
        if (byId.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Id Not Found");

        Table table = Table.builder()
                .type(byId.get().getType())
                .build();

        Table tablesave = tableRepository.saveAndFlush(table);
        return tablesave;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Table deleteById(String id) {

        Optional<Table> byId = tableRepository.findById(id);
        byId.orElseThrow();


        tableRepository.deleteById(byId.get().getId());

        return Table.builder()
                .Id(byId.get().getId())
                .build();
    }

    private Specification<Table> getTableSpecification(TableRequest request) {
        Specification<Table> specification = (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            if (request.getName() != null){
                Predicate addNamePredicate =  criteriaBuilder.like(root.get("name"), "%" + request.getName() + "%");
                predicates.add(addNamePredicate);
            }
            if (request.getMinPrice() != null){
                Predicate minPricePredicate =  criteriaBuilder.greaterThanOrEqualTo(root.get("price"),+ request.getMinPrice());
                predicates.add(minPricePredicate);
            }
            if (request.getMaxPrice() != null){
                Predicate maxPricePredicate =  criteriaBuilder.lessThanOrEqualTo(root.get("price"),+ request.getMaxPrice());
                predicates.add(maxPricePredicate);
            }

            return query.where(predicates.toArray(new Predicate[] {})).getRestriction();
        };
        return specification;
    }
}
