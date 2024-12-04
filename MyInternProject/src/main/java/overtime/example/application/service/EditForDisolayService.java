package overtime.example.application.service;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class EditForDisolayService {

	//前残業、後残業表示用に編集
	
	/**
	 * 前残業、後残業表示用に編集
	 * 表示用Stringを格納したMapを返す。Key:"before","after"（無い場合はnullが返る）
	 * "前残業　startTime 〜 workPatternsStartTime"
	 * "後残業　workPatternsEndTime 〜 endTime"
	 * 
	 * @param startTime
	 * @param endTime
	 * @param workPatternsStartTime
	 * @param workPatternsEndTime
	 * @return overtimeBefAftDisplayMap
	 */
	public Map<String, String> getOvertimeBefAftDisplay(LocalTime startTime, LocalTime endTime, 
											LocalTime workPatternsStartTime, LocalTime workPatternsEndTime) {
		
		Map<String, String> overtimeBefAftDisplayMap = new HashMap<>();
		
		//残業開始時間が勤務パターン開始時間よりも前
		if (startTime.isBefore(workPatternsStartTime)) {
			//前残業あり
			String beforeOvertimeDisplay = "前残業　" + startTime + "　〜　" + workPatternsStartTime;
			overtimeBefAftDisplayMap.put("before", beforeOvertimeDisplay);
		}
		//残業終了時間が勤務パターン終了時間よりも後
		if (endTime.isAfter(workPatternsEndTime)) {
			//後残業あり
			String afterOvertimeDisplay = "後残業　" + workPatternsEndTime + "　〜　" + endTime;
			overtimeBefAftDisplayMap.put("after", afterOvertimeDisplay);
		}
		
		return overtimeBefAftDisplayMap;
	}
}
