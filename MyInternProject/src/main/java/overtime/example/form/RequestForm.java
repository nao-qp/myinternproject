package overtime.example.form;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Data;

@Data
public class RequestForm {
	private Integer workPatternsId;
	private LocalDate requestDate;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private LocalTime restPeriod;
	private String reason;
}
