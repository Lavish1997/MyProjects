package com.makemytour.controller;

import com.makemytour.payload.CabDto;
import com.makemytour.payload.TouristPointDto;
import com.makemytour.service.impl.CabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cab")
public class CabController {

    @Autowired
    private CabService cabService;

    //http://localhost:8080/api/cab/addcab/1
    @PostMapping("/addcab/{tpid}")
    public ResponseEntity<CabDto> addCab(@RequestBody CabDto cabDto, @PathVariable long tpid) {
        CabDto cab = cabService.addCab(cabDto,tpid);
        return new ResponseEntity<>(cab, HttpStatus.CREATED);

    }
}
