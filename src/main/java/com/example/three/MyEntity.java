package com.example.three;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString
@Entity

public class MyEntity {

	
	@Column
	@NonNull
	private String oUrl;
	
	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column 
	private int urlNum; 
	
	
	@Column
	@NonNull
	private int count; 


}