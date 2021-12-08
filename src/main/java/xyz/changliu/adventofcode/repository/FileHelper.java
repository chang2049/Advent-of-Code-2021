package xyz.changliu.adventofcode.repository;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FileHelper {
    public List<String> loadContent(int dayCount) throws IOException {
        String dayCountStr = String.valueOf(dayCount);
        String fileName = (dayCountStr.length()>1?dayCountStr:"0"+dayCountStr) +".txt";
        return readFile(fileName);
    }

    public List<String> loadContent(int dayCount, int partCount) throws IOException {
        String dayCountStr = String.valueOf(dayCount);
        String fileName = (dayCountStr.length()>1?dayCountStr:"0"+dayCountStr) +"-"+partCount+".txt";
        return readFile(fileName);
    }

    public List<String> readFile(String fileName) throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName);
        return IOUtils.readLines(is, "UTF-8");
    }

    public static List<Integer> convertStringToIntList(List<String> list){
        return list.parallelStream().map(item-> Integer.parseInt(item)).collect(Collectors.toList());
    }
}

