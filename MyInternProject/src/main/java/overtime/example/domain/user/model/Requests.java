package overtime.example.domain.user.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Data;

@Data
public class Requests {
	private Integer id;
	private Integer usersId;
	private Integer departmentsId;
	private Integer work_patternsId;
	private LocalDate requestDate;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private LocalTime restPeriod;
	private String reason;
	private LocalDate approvalDate;
	private Integer approvalUsersId;
	private Integer isChecked;
	private String approvalStatus;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
	private String departmentsName;
	private String approvalUsersName;
}
