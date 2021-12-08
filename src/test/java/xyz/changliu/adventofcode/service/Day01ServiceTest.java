package xyz.changliu.adventofcode.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xyz.changliu.adventofcode.repository.FileHelper;

import java.io.IOException;

class Day01ServiceTest {

    Day01Service day01Service;

    @BeforeEach
    public void before(){
        day01Service = new Day01Service();
        day01Service.fileHelper = new FileHelper();
    }

    @Test
    void getAnswerForQuestion1() throws IOException {
        System.out.println(day01Service.getAnswerForQuestion1());
    }

    @Test
    void getAnswerForQuestion2() throws IOException {
        System.out.println(day01Service.getAnswerForQuestion2());
    }
}