package com.denys.toukdemo.controller;

import com.denys.toukdemo.dto.Seans;
import com.denys.toukdemo.service.SeansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class SeansController {

    @Autowired
    SeansService seansService;

    @RequestMapping(value = "/seanses", method = RequestMethod.GET)
    public List<Seans> getSeansesByDateTime(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm") Date date){
        List<Seans> seanses = seansService.getSeansesByStartDatetime(date);
        return seanses;
    }
}
