package com.itwill.igojoamanagement.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "buttons")
public class Button {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer btnId;

	private String btnName;

	@ManyToOne
	@JoinColumn(name = "tabId", nullable = false)
	private Tab tab;

}