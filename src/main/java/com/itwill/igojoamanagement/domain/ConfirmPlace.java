
package com.itwill.igojoamanagement.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@ToString(callSuper = true)
@Table(name = "confirmPlace")
@IdClass(ConfirmPlaceId.class)
public class ConfirmPlace {

	@Id
	@Column(length = 20, nullable = false)
	@NotBlank(message = "장소 이름은 필수 입력 항목입니다.")
	private String placeName;

	@Id
	@Column(length = 12, nullable = false)
	@NotBlank(message = "보고자 ID는 필수 입력 항목입니다.")
	private String reporterId;

	@Column(length = 10)
	@Size(max = 10, message = "대주소는 최대 10자까지 입력할 수 있습니다.")
	private String largeAddress;

	@Column(length = 10)
	@Size(max = 10, message = "중주소는 최대 10자까지 입력할 수 있습니다.")
	private String mediumAddress;

	@Column(length = 30)
	@Size(max = 30, message = "소주소는 최대 30자까지 입력할 수 있습니다.")
	private String smallAddress;

	@Column(length = 400)
	@Size(max = 400, message = "장소 설명은 최대 400자까지 입력할 수 있습니다.")
	private String placeDescription;

	@Column(nullable = false)
	@NotNull(message = "경도는 필수 입력 항목입니다.")
	private double placeLongitude;

	@Column(nullable = false)
	@NotNull(message = "위도는 필수 입력 항목입니다.")
	private double placeLatitude;

	@Column(length = 500)
	@Size(max = 500, message = "운영 시간은 최대 500자까지 입력할 수 있습니다.")
	private String operatingHours;

	@Column
	private Integer radius;

	@Column
	private LocalDateTime displayDate;
}
