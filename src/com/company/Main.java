package com.company;

import com.company.superclass.Coordinates;
import com.company.superclass.LabWork;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        System.out.println(input.length);
    }
}
