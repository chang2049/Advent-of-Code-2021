package xyz.changliu.adventofcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.changliu.adventofcode.repository.FileHelper;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Service
public class Day02Service {

    @Autowired
    FileHelper fileHelper;

    Map<String, int[]> directions = Map.of("forward", new int[]{1,0},"down",new int[]{0,1},"up",new int[]{0,-1});
    List<String> dirctionInstructions;
    List<Integer> stepsInstructions;

    public void loadData() throws IOException {
        dirctionInstructions = new ArrayList<>();
        stepsInstructions = new ArrayList<>();
        this.fileHelper.loadContent(2).forEach(str->{
            String[] s = str.split(" ");
            dirctionInstructions.add(s[0]);
            stepsInstructions.add(Integer.parseInt(s[1]));
        });
    }

    public int getAnswerForQuestion1() throws IOException {
        loadData();
        int[] location = new int[]{0,0};
        IntStream.range(0,dirctionInstructions.size()).forEach(i->calculateLocation(location,directions.get(dirctionInstructions.get(i)),stepsInstructions.get(i)));
        return location[0]*location[1];
    }

    public int getAnswerForQuestion2() throws IOException {
        loadData();
        int[] location = new int[]{0,0};
        AtomicInteger depth = new AtomicInteger(0);
        IntStream.range(0,dirctionInstructions.size()).forEach(i->{
            calculateLocation(location,directions.get(dirctionInstructions.get(i)),stepsInstructions.get(i));
            if(dirctionInstructions.get(i).equals("forward")){
                depth.addAndGet(location[1] * stepsInstructions.get(i));
            }
        });
        return location[0]*depth.get();
    }

    public void calculateLocation(int[] originalLoc, int[] direction, int steps){
        IntStream.range(0,originalLoc.length).forEach(i->originalLoc[i] += direction[i]*steps);
    }


}
