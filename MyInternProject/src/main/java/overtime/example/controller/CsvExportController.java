package overtime.example.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;
import overtime.example.application.service.CsvExportService;

@Controller
public class CsvExportController {

	@Autowired
	private CsvExportService csvExportService;
	
	//残業申請一覧画面表示
	@GetMapping("export/csv")
	public void exportMonthlyreportAllToCsv (HttpServletResponse response) throws IOException {
		
		// 出力先のバイトストリームを作成
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        
        // CSV データをエクスポート
//        int i = 0;
//        if (i == 1) {
//        	csvExportService.exportMonthlyreportAllToCsv(byteArrayOutputStream);
//        } else {
//        	csvExportService.exportMonthlyreportToCsv(byteArrayOutputStream);
//        }
        
        csvExportService.exportMonthlyreportAllToCsv(byteArrayOutputStream);
        String fileNmae = "MonthlyreportAll.csv";
        
        // レスポンスのヘッダー設定
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileNmae + "\"");
        response.setStatus(HttpStatus.OK.value());

        // 出力バイトストリームをレスポンスに書き込む
        response.getOutputStream().write(byteArrayOutputStream.toByteArray());
        response.getOutputStream().flush();
	}
}
