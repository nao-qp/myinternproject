package overtime.example.form;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReportForm {
	private Integer workPatternsId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate overtimeDate;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime startTime;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime endTime;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime restStartTime;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime restEndTime;
	private String reason;
	private LocalTime workPatternsStartTime;
	private LocalTime workPatternsEndTime;
}
