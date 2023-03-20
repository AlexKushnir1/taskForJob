package com.example.nbu_rusl;

import java.util.List;
import java.util.stream.Collectors;

public class Filtration {
    public List<DataOfMonth> startFilter(List<DataOfMonth> startList) {
        String searchString = "RES_OffReserveAssets";
        return startList.stream()
                .filter(obj -> obj.getId_api() != null && obj.getId_api().contains(searchString))
                .collect(Collectors.toList());
    }

    public List<Result> secondFilter(List<DataOfMonth> filteredList) {
        return filteredList.stream()
                .map(obj -> new Result(obj.getDate(), obj.getValue()))
                .collect(Collectors.toList());
    }

    public void lastFilter(List<Result> filteredList) {
        filteredList.get(0).setDifference(0);
        for (int i = 1; i < filteredList.size(); i++) {
            filteredList.get(i).setDifference(filteredList.get(i).getValue() - filteredList.get(i - 1).getValue());
        }
    }


}
