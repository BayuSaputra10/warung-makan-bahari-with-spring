package warungmakanbahariapi.wmbahari.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import warungmakanbahariapi.wmbahari.entity.Menu;
import warungmakanbahariapi.wmbahari.model.request.MenuRequest;
import warungmakanbahariapi.wmbahari.model.request.MenumixmaxRequest;
import warungmakanbahariapi.wmbahari.model.response.MenuResponse;
import warungmakanbahariapi.wmbahari.repository.MenuRepository;
import warungmakanbahariapi.wmbahari.service.MenuService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    public MenuResponse create(MenuRequest request) {

        MenuRequest menuRequest = MenuRequest.builder()
                .name(request.getName())
                .price(request.getPrice())
                .build();

        Menu menu = Menu.builder()
                .name(menuRequest.getName())
                .price(menuRequest.getPrice())
                .build();
        Menu saveMenu = menuRepository.save(menu);


        return MenuResponse.builder()
                .name(saveMenu.getName())
                .price(saveMenu.getPrice())
                .build();
    }

    @Override
    public MenuResponse getById(MenuRequest request) {
        Optional<Menu> byId = menuRepository.findById(request.getName());
        if (byId.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Menu Not Found");

        return MenuResponse.builder()
                .name(byId.get().getName())
                .price(byId.get().getPrice())
                .build();
    }

    @Override
    public MenuResponse updateById(MenuRequest request) {
        Optional<Menu> byId = menuRepository.findById(request.getName());
        if (byId.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Menu Not Found");

        Menu menu = Menu.builder()
                .name(byId.get().getName())
                .price(byId.get().getPrice())
                .build();
        Menu saveMenu = menuRepository.save(menu);



        return MenuResponse.builder()
                .name(saveMenu.getName())
                .price(saveMenu.getPrice())
                .build();
    }

    @Override
    public MenuResponse deleteById(MenuRequest request) {
        menuRepository.deleteById(request.getName());

        return MenuResponse.builder()
                .name("Succed Delete Menu ")
                .price(null)
                .build();
    }

    @Override
    public List<Menu> getAll(MenumixmaxRequest request) {
        Specification<Menu> productSpecification = getProductSpecification(request);

        return menuRepository.findAll(productSpecification);
    }

    private Specification<Menu> getProductSpecification(MenumixmaxRequest request) {
        Specification<Menu> specification = (root, query, criteriaBuilder) -> {

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
