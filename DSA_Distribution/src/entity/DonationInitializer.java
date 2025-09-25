/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.ArrayQueue;
import adt.List;
import adt.ListInterface;
import adt.QueueInterface;
import entity.Cash;
import entity.Donor;
import entity.Food;
import java.time.LocalDate;

/**
 *
 * @author Asus
 */
public class DonationInitializer {

    LocalDate date1 = LocalDate.of(2024, 12, 25);
    LocalDate date2 = LocalDate.of(2024, 11, 19);
    LocalDate date3 = LocalDate.of(2024, 10, 25);
    QueueInterface<Food> queue = new ArrayQueue<>();

    public QueueInterface<Food> InitializeFood() {
        queue.enqueue(new Food(date3, "Preserved", 10, "DN003", LocalDate.now()));
        queue.enqueue(new Food(date2, "Preserved", 6, "DN005", LocalDate.now()));
        queue.enqueue(new Food(date1, "Preserved", 3, "DN001", LocalDate.now()));

        return queue;
    }

}
