package ru.otus.bot.metric.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Car {
    private String id;
    private String chatId;
    private String model;
    private int year;
    private float engineVolume;
    private String transmission;
    private int mileage;
}
