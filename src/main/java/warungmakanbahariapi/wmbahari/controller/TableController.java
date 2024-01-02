package warungmakanbahariapi.wmbahari.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warungmakanbahariapi.wmbahari.entity.Table;
import warungmakanbahariapi.wmbahari.model.request.TableRequest;
import warungmakanbahariapi.wmbahari.service.TableService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/tables")
public class TableController {

    private final TableService tableService;

    @PostMapping
    public ResponseEntity<Table> createTable(@RequestBody Table request){

        Table tableSave = tableService.create(request);

        return ResponseEntity.ok(tableSave);
    }

    @GetMapping
    public ResponseEntity<List<Table>> getAllTable(@RequestParam(required = false) TableRequest request){

        List<Table> TableList = tableService.getAll(request);

        return ResponseEntity.ok(TableList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Table> getTableById(@PathVariable String request){

        Table tablebyId = tableService.getById(request);

        return ResponseEntity.ok(tablebyId);
    }
    @PutMapping
    public ResponseEntity<Table> updateTable(@RequestBody Table request){

        Table tablebyId = tableService.update(request);

        return ResponseEntity.ok(tablebyId);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Table> deleteById(@RequestParam(required = false) String request){

        Table deleteById = tableService.deleteById(request);

        return ResponseEntity.ok(deleteById);
    }
}
