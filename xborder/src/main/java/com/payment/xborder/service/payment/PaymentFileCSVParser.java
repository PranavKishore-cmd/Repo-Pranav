package com.payment.xborder.service.payment;

import com.payment.xborder.model.payment.PaymentRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Pradeep P
 * 12-11-2019
 */
public class PaymentFileCSVParser implements PaymentFileParser {

   private final String[] header = { "First Name", "Last Name" };

    @Override
    public PaymentRecord parseLine(String line) {
        String[] columns = line.split(",");
//        Mapping mapping=new Mapping();
//        mapping.setMappingId(columns[0]);
//        mapping.setmappingValues(new HashSet<String>(Arrays.asList(columns[1].split("\\|"))));
//        mapping.setMappingType(MappingType.valueOf(columns[2]));
//        mapping.setDate(LocalDateTime.now());
//        return mapping;
        return null;
    }

    @Override
    public List<String> readLines(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException("Error while  reading the lines");
        }
    }
}
