package com.chmykhun.restaurant;

import com.chmykhun.restaurant.kitchen.Dish;
import com.chmykhun.restaurant.kitchen.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public interface Messages {
        String orderHeadFormat = "\n *** Order from tablet #%d ***";
        String chooseDish = "Please, choose a dish from the list:";
        String orderInfo = "Your order: [%s] of Tablet{number=%d}";
        String consoleUnavailable = "Console is unavailable.";
        String incorrectDish = " is not detected";
        String startCooking = "Start cooking - ";
        String cookingTime = ", cooking time %s min";
        String cookedAndServed = " was cooked by %s and served by %s";
        String videoProcessing = "%s is displaying... %d, %d";
        String noSuitableVideo = "No video is available for the order ";

        String reportHead = "\n=== Restaurant overall report ===";
        String advertisementReportHead = "\n--- Advertisement profit report ---";
        String commonReportFormat = "%s - %d";
        String cookWorkloadReportHead = "\n--- Cook workload report ---";
        String cookWorkloadReportFormat = "%s - %d min";
        String activeVideoReportHead = "\n--- Active advertisement video report ---";
        String archivedVideoReportHead = "\n--- Archived advertisement video report ---";
    }

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> order = new ArrayList<>();
        writeMessage(Messages.chooseDish);
        writeMessage(Order.allDishesToString());
        while (true) {
            String input = readString();
            if (input.equals("exit")) {
                break;
            } else if (Dish.isDish(input)) {
                order.add(Dish.valueOf(input));
            } else {
                writeMessage(input + Messages.incorrectDish);
            }
        }
        return order;
    }
}
