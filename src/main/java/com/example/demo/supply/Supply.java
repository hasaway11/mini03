package com.example.demo.supply;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@ToString
public class Supply {
	private int sno;
	private String name;
	private LocalDate regDate = LocalDate.now();
	private int count;
}
