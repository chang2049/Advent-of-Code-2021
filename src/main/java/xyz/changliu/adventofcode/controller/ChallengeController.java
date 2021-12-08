package xyz.changliu.adventofcode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChallengeController {

    @GetMapping("/challenge")
    public String getChallengeAnswer(@RequestHeader("day-count") int dayCount, @RequestHeader("part-count") int partCount){
        return "hello";
    }
}
