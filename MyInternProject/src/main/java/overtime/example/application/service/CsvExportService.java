package overtime.example.application.service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;

import overtime.example.domain.user.model.Reports;
import overtime.example.domain.user.service.ReportService;

@Service
public class CsvExportService {

	@Autowired
    private ReportService reportService;

    public void exportMonthlyreportAllToCsv(OutputStream outputStream) throws IOException {
    	
    	//月次資料データ（全件）取得
        List<Reports> repotList = reportService.getMonthlyreportListAll();

        // CSV 書き込み準備
        try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(outputStream))) {
            // ヘッダー行を書き込む（必要に応じて）
            writer.writeNext(new String[] {"id", "usersId", "usersName", "departmentsName"});

            // データ行を書き込む
            for (Reports report : repotList) {
                writer.writeNext(new String[] {
                		String.valueOf(report.getId()),
                		String.valueOf(report.getUsersId()),
                		report.getUsersName(),
                		report.getDepartmentsName()
                });
            }
        }
    }
    
public void exportMonthlyreportToCsv(OutputStream outputStream) throws IOException {
    	
    	//月次資料データ（全件）取得
        List<Reports> repotList = reportService.getMonthlyreportListAll();

        // CSV 書き込み準備
        try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(outputStream))) {
            // ヘッダー行を書き込む（必要に応じて）
            writer.writeNext(new String[] {"id", "usersId", "usersName", "departmentsName"});

            // データ行を書き込む
            for (Reports report : repotList) {
                writer.writeNext(new String[] {
                		String.valueOf(report.getId()),
                		String.valueOf(report.getUsersId()),
                		report.getUsersName(),
                		report.getDepartmentsName()
                });
            }
        }
    }
}
