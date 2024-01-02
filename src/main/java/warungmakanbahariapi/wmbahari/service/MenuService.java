package warungmakanbahariapi.wmbahari.service;

import warungmakanbahariapi.wmbahari.entity.Menu;
import warungmakanbahariapi.wmbahari.model.request.MenuRequest;
import warungmakanbahariapi.wmbahari.model.request.MenumixmaxRequest;
import warungmakanbahariapi.wmbahari.model.response.MenuResponse;

import java.util.List;

public interface MenuService {

    MenuResponse create(MenuRequest request);

    MenuResponse getById(MenuRequest request);

    MenuResponse updateById(MenuRequest request);
    MenuResponse deleteById(MenuRequest request);

    List<Menu> getAll(MenumixmaxRequest request);
}
