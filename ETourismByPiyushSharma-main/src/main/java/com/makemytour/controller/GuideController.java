package com.makemytour.controller;

import com.makemytour.payload.CabDto;
import com.makemytour.payload.GuideDto;
import com.makemytour.service.impl.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guide")
public class GuideController {

    @Autowired
    private GuideService guideService;

    //http://localhost:8080/api/guide/addguide/1
    @PostMapping("/addguide/{id}")
    public ResponseEntity<GuideDto> addGuide(@RequestBody GuideDto guideDto, @PathVariable long id) {
        GuideDto guide = guideService.addGuide(guideDto,id);
        return new ResponseEntity<>(guide, HttpStatus.CREATED);


    }
}
