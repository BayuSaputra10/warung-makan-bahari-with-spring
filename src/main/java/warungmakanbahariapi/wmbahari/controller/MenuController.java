package warungmakanbahariapi.wmbahari.controller;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warungmakanbahariapi.wmbahari.entity.Menu;
import warungmakanbahariapi.wmbahari.model.request.MenuRequest;
import warungmakanbahariapi.wmbahari.model.request.MenumixmaxRequest;
import warungmakanbahariapi.wmbahari.model.response.MenuResponse;
import warungmakanbahariapi.wmbahari.service.MenuService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/menus")
public class MenuController {


    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<MenuResponse> createMenu(@RequestBody MenuRequest request) {

    MenuResponse menuResponse = menuService.create(request);

    return ResponseEntity.ok(menuResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllMenu(@RequestParam(required = false) String name,
                                                   @RequestParam(required = false) Long minPrice,
                                                   @RequestParam(required = false) Long maxPrice) {
        MenumixmaxRequest menumixmaxRequest = MenumixmaxRequest.builder()
                .name(name)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .build();


        List<Menu> menuServiceAll = menuService.getAll(menumixmaxRequest);

        return ResponseEntity.ok(menuServiceAll);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getMenuById(@RequestParam(required = false) String id,
                                                   @RequestParam(required = false) Long minPrice,
                                                   @RequestParam(required = false) Long maxPrice) {


        Menu menu = Menu.builder()
                .Id(id)
                .build();

        MenuRequest menuRequest = MenuRequest.builder()
                .name(menu.getId())
                .build();
        MenuResponse byId = menuService.getById(menuRequest);

        return ResponseEntity.ok(byId);
    }
    @PutMapping
    public ResponseEntity<?> updateMenu(@RequestBody MenuRequest request) {
        Menu menu = Menu.builder()
                .Id(request.getId())
                .build();

        MenuRequest menuRequest = MenuRequest.builder()
                .name(menu.getId())
                .build();
        MenuResponse byId = menuService.updateById(menuRequest);

        return ResponseEntity.ok(byId);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteMenu(@RequestBody String id) {
        Menu menu = Menu.builder()
                .Id(id)
                .build();
        MenuRequest menuRequest = MenuRequest.builder()
                .name(menu.getId())
                .build();
        MenuResponse byId = menuService.deleteById(menuRequest);

        return ResponseEntity.ok(byId);
    }

}
