package xyz.changliu.adventofcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.changliu.adventofcode.repository.FileHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class Day03Service {
    @Autowired
    FileHelper fileHelper;

    public int getAnswerForQuestion1(){
        List<String> data = this.fileHelper.loadContent(3);
        int len = data.size()/2;
        int[] counts = countOneNumber(data);
        List<String> gammaBinary = new LinkedList<>();
        List<String> epsilonBinary = new LinkedList<>();
        for (int count : counts) {
            gammaBinary.add(count > len ? "1" : "0");
            epsilonBinary.add(count > len ? "0" : "1");
        }
        int gamma = Integer.parseInt(String.join("",gammaBinary),2);
        int epsilon = Integer.parseInt(String.join("",epsilonBinary),2);
        return gamma*epsilon;
    }

    public int getAnswerForQuestion2(){
        List<String> data = this.fileHelper.loadContent(3);
        String oxygenBinary = findExtreme(true,data);
        String co2Binary = findExtreme(false,data);
        int oxygen = Integer.parseInt(String.join("",oxygenBinary),2);
        int co2 = Integer.parseInt(String.join("",co2Binary),2);
        return oxygen*co2;
    }

    private String findExtreme(boolean isFindingMax, List<String> data){
        List<String> collect = new ArrayList<>(data);
        for(int i = 0; i<data.get(0).length(); i++){
            if(collect.size()==1) return collect.get(0);
            char extremeDigit = isFindingMax? findMostCommonDigit(collect,i):findLeastCommonDigit(collect,i);
            int finalI = i;
            collect = collect.stream().filter(x->x.charAt(finalI)==extremeDigit).collect(Collectors.toList());
        }
        return collect.get(0);
    }

    public int[] countOneNumber(List<String> list){
        int[] counter = new int[list.get(0).length()];
        list.forEach(str->{
            for(int i = 0; i<str.length(); i++){
                if(str.charAt(i) == '1'){
                    counter[i]++;
                }
            }
        });
        System.out.println(Arrays.toString(counter));
        return counter;
    }

    public char findMostCommonDigit(List<String> list, int index){
        long count = list.stream().filter(x -> x.charAt(index) == '1').count();
        if(count*2>=list.size()) return '1';
        else return '0';
    }

    public char findLeastCommonDigit(List<String> list, int index){
        long count = list.stream().filter(x -> x.charAt(index) == '1').count();
        if(count*2>=list.size()) return '0';
        else return '1';
    }
}
