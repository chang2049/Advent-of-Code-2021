package xyz.changliu.adventofcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.changliu.adventofcode.repository.FileHelper;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class Day01Service {
    @Autowired
    FileHelper fileHelper;

    public List<Integer> loadData(){
        return fileHelper.convertStringToIntList(fileHelper.loadContent(1));
    }

    public long getAnswerForQuestion1(){
        List<Integer> data = loadData();
        return IntStream.range(1,data.size()).parallel().filter(x-> data.get(x) > data.get(x - 1)).count();
    }

    public long getAnswerForQuestion2(){
        List<Integer> data = loadData();
        int[] threeSumVal = new int[data.size()-2];
        IntStream.range(0,data.size()-2).forEach(i->threeSumVal[i] = i==0?data.get(i)+data.get(i+1)+data.get(i+2):threeSumVal[i-1]-data.get(i-1)+data.get(i+2));
        return IntStream.range(1,threeSumVal.length).parallel().filter(x-> threeSumVal[x] > threeSumVal[x-1]).count();
    }
}
