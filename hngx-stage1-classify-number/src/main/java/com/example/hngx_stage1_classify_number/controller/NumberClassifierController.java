package com.example.hngx_stage1_classify_number.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class NumberClassifierController {

    @GetMapping("/classify-number")
    public Map<String, Object> classifyNumber(@RequestParam("number") String numberStr) {
        Map<String, Object> response = new HashMap<>();
        try {
            int number = Integer.parseInt(numberStr);
            response.put("number", number);
            response.put("is_prime", isPrime(number));
            response.put("is_perfect", isPerfect(number));
            response.put("digit_sum", digitSum(number));
            response.put("properties", classifyProperties(number));
            response.put("fun_fact", getFunFact(number));
        } catch (NumberFormatException e) {
            response.put("number", numberStr);
            response.put("error", true);
        }
        return response;
    }

    private boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    private boolean isPerfect(int number) {
        int sum = 0;
        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) sum += i;
        }
        return sum == number;
    }

    private int digitSum(int number) {
        int sum = 0;
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    private List<String> classifyProperties(int number) {
        List<String> properties = new ArrayList<>();
        if (isArmstrong(number)) properties.add("armstrong");
        properties.add(number % 2 == 0 ? "even" : "odd");
        return properties;
    }

    private boolean isArmstrong(int number) {
        int original = number, sum = 0, digits = String.valueOf(number).length();
        while (number != 0) {
            sum += Math.pow(number % 10, digits);
            number /= 10;
        }
        return sum == original;
    }

    private String getFunFact(int number) {
        String url = "http://numbersapi.com/" + number + "/math";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}

