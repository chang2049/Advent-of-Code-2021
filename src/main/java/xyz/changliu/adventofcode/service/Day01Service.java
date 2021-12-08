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

    public List<Integer> loadData() throws IOException {
        return fileHelper.convertStringToIntList(fileHelper.loadContent(1));
    }

    public long getAnswerForQuestion1() throws IOException {
        List<Integer> data = loadData();
        return IntStream.range(1,data.size()).parallel().filter(x-> data.get(x) > data.get(x - 1)).count();
    }

    public long getAnswerForQuestion2() throws IOException {
        List<Integer> data = loadData();
//        long[] threeSumVal = IntStream.range(2,data.size()).mapToLong(i-> i>2?data.get(i)+data.get(i-1)+data.get(i-3):data.get(i)+data.get(i-1)+data.get(i-2)).toArray();
        int[] threeSumVal = new int[data.size()-2];
        for(int i = 0, sum = 0; i<data.size(); i++){
            sum = i<3?sum+data.get(i):sum+data.get(i)-data.get(i-3);
            if(i>=2){
                threeSumVal[i-2] = sum;
            }
        }
        return IntStream.range(1,threeSumVal.length).parallel().filter(x-> threeSumVal[x]>threeSumVal[x-1]).count();
    }
}
