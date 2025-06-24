package org.example.rtms.controller;

import org.example.rtms.dto.DivisionDTO;
import org.example.rtms.exceptions.ApiException;
import org.example.rtms.service.DivisionServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class DivisionController {

    //Dependency injection
    private final DivisionServiceImp divisionServiceImp;
    public DivisionController(DivisionServiceImp divisionServiceImp){
         this.divisionServiceImp = divisionServiceImp;
    }

    //Get all divisions from DB
    @GetMapping("/division")
    public ResponseEntity<List<DivisionDTO>> getAll(){
        return new ResponseEntity<>(divisionServiceImp.getAllDivision(), HttpStatus.OK);
    }

    //Get division by its ID
    @GetMapping("/division/{id}")
    public ResponseEntity<DivisionDTO> getDivision(@PathVariable Long id) throws ApiException {
        return new ResponseEntity<>(divisionServiceImp.getDivisionById(id), HttpStatus.OK);
    }



    //Update division
    @PutMapping("/division/{id}")
    public ResponseEntity<?> updateDivisionById(@Valid @PathVariable Long id, @RequestBody DivisionDTO divisionDTO) throws ApiException {
        return new ResponseEntity<>(divisionServiceImp.updateDivisionById(id, divisionDTO), HttpStatus.OK);
    }


    //Create division
    @PostMapping("/division")
    public ResponseEntity<?> createDivision(@RequestBody DivisionDTO divisionDTO) throws ApiException {
        return new ResponseEntity<>(divisionServiceImp.createDivision(divisionDTO), HttpStatus.CREATED);
    }


    //Delete division
    @DeleteMapping("/division/{id}")
    public ResponseEntity<?> deleteDivision(@PathVariable Long id) throws ApiException {
        return new ResponseEntity<>(divisionServiceImp.deleteDivisionById(id), HttpStatus.OK);
    }
}
