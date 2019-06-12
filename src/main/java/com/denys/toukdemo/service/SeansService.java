package com.denys.toukdemo.service;

import com.denys.toukdemo.dto.Seans;

import java.util.Date;
import java.util.List;

public interface SeansService {

    List<Seans> getSeansesByStartDatetime(Date date);
}
