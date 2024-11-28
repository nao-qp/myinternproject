package overtime.example.domain.user.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Data;

@Data
public class Reports {
	private Integer id;
	private Integer usersId;
	private Integer requestsId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private LocalTime restPeriod;
	private String reason;
	private Integer isChecked;
	private Integer wdayDtUnder60;
	private Integer wdayDtOver60;
	private Integer wdayEmnUnder60;
	private Integer wdayEmnOver60;
	private Integer hdayDtUnder60;
	private Integer hdayDtOver60;
	private Integer hdayEmnUnder60;
	private Integer hdayEmnOver60;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;
	//申請データ情報
	private Integer departmentsId;
	private Integer workPatternsId;
	private LocalDate requestDate;
	private LocalDateTime requestStartTime;
	private LocalDateTime requestEndTime;
	private LocalTime requestRestPeriod;
	private String requestReason;
	private LocalDate approvalDate;
	private Integer approvalUsersId;
	private String usersName;
	private String departmentsName;
	private String approvalUsersName;
}









