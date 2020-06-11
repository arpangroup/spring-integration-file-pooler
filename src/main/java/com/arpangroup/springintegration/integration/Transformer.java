package com.arpangroup.springintegration.integration;

import com.arpangroup.springintegration.model.Employee;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class Transformer {
    public String transformStringToJson(String filePath) throws Exception{
        Pattern pattern = Pattern.compile(",");
        Path path = Paths.get(filePath);
        File csvFile = path.toFile();

        try (BufferedReader in = new BufferedReader(new FileReader(csvFile));) {
            List<Employee> employees = in .lines().skip(1).map(line -> {
                    String[] x = pattern.split(line);
            return new Employee(Integer.parseInt(x[0]), x[1], Integer.parseInt(x[2]));
            }).collect(Collectors.toList());

            return new Gson().toJson(employees);
        }

    }
}
