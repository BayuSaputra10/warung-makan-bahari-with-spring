package warungmakanbahariapi.wmbahari.service;

import warungmakanbahariapi.wmbahari.entity.Table;
import warungmakanbahariapi.wmbahari.model.request.TableRequest;

import java.util.List;

public interface TableService {

    Table create(Table request);

    List<Table> getAll(TableRequest request);

    Table getById(String id);

    Table update(Table request);

    Table deleteById(String id);

}
