package com.example.nbu_rusl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@SpringBootApplication
public class NbuRuslApplication {

    public static void main(String[] args) throws IOException, java.text.ParseException {
        List<DataOfMonth> dataOfMonth= new ArrayList<>();
        String sUrl = "https://bank.gov.ua/NBUStatService/v1/statdirectory/res?date=200401&json";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        // перетворюємо рядки "200401" та "202302" у об'єкти LocalDate
        LocalDate startDate = LocalDate.parse("20040101", DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate endDate = LocalDate.parse("20230201", DateTimeFormatter.ofPattern("yyyyMMdd"));

        // визначаємо різницю між датами у місяцях
        long monthsBetween = ChronoUnit.MONTHS.between(startDate.withDayOfMonth(1), endDate.withDayOfMonth(1));

        for (int i = 0;i<monthsBetween;i++) {
            URL url = new URL(sUrl);
            JsonSimpleParser parser = new JsonSimpleParser();
            String responseFromParser = parser.parse(url);
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<DataOfMonth>>() {}.getType();
            dataOfMonth.addAll(gson.fromJson(responseFromParser, type));
            Date date = dateFormat.parse(sUrl.substring(sUrl.indexOf("=") + 1, sUrl.indexOf("&")));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH,1);
            String newDate =dateFormat.format(calendar.getTime());
            sUrl= sUrl.replaceAll("\\d{6}",newDate);
        }
        Filtration filtration = new Filtration();
        dataOfMonth = filtration.startFilter(dataOfMonth);
        List<Result> filteredList = filtration.secondFilter(dataOfMonth);
        filtration.lastFilter(filteredList);
        String path = "C:\\Users\\Admin\\Desktop\\Result.csv";
        ResultToCSV result1=new ResultToCSV();
        result1.writeData(path,filteredList);
    }

}
