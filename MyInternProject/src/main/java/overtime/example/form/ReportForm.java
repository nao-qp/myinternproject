package overtime.example.form;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReportForm {
	private Integer workPatternsId;
	private LocalDate reportDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime startTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime endTime;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime restPeriod;
	private String reason;
}
