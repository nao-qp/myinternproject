package overtime.example.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import overtime.example.application.service.PdfService;

@RestController
public class PdfController {

	   @Autowired
	   private PdfService pdfService;





	   @GetMapping("/pdf/generate/{id}")
	    public ResponseEntity<byte[]> generatePdf(@PathVariable("id") Integer id, Model model) throws IOException {
	        // ThymeleafでHTMLを生成
	    	String htmlContent = pdfService.generateHtmlContent(id, model);


	        // wkhtmltopdfコマンドを実行してPDFを生成
	        File pdfFile = generatePdfWithWkHtml(htmlContent);

	        // PDFをバイト配列で返す
	        byte[] pdfContent = Files.readAllBytes(pdfFile.toPath());

	        // PDFをブラウザでダウンロードさせるためのレスポンス設定
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Type", "application/pdf");
	        headers.add("Content-Disposition", "attachment; filename=generated.pdf");

	        return ResponseEntity.ok()
	                .headers(headers)
	                .body(pdfContent);
	    }

	    // wkhtmltopdfコマンドを実行するメソッド
	    private File generatePdfWithWkHtml(String htmlContent) throws IOException {
	        // HTMLを一時ファイルに保存
	        File htmlFile = File.createTempFile("temp", ".html");
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(htmlFile))) {
	            writer.write(htmlContent);
	        }

	        
	     // 出力PDFファイル
	        File outputDir = new File("/Users/hosoyanaomi/pdf"); // 出力先ディレクトリを指定
	        if (!outputDir.exists()) {
	            outputDir.mkdirs(); // ディレクトリが存在しない場合は作成
	        }
	        File pdfFile = new File(outputDir, "output.pdf");
	        
	     // wkhtmltopdfを実行
		    String wkHtmlPath = "/usr/local/bin/wkhtmltopdf"; // wkhtmltopdfのフルパスを指定
		    String baseUri = "http://localhost:8080"; // baseUriを指定

		    ProcessBuilder processBuilder = new ProcessBuilder(
		        wkHtmlPath, 
		        "--enable-local-file-access", // ローカルファイルアクセスを有効化
		        "--base-url", baseUri,  // base-urlオプションを指定
		        htmlFile.getAbsolutePath(), 
		        pdfFile.getAbsolutePath()
		    );

		    //PDFが作成し終わるまで待機

		    Process process = processBuilder.start();
		    int exitCode = 1;
			try {
				exitCode = process.waitFor();
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}  
			// プロセスが終了するまで待機
		    if (exitCode == 0) {
		        System.out.println("PDF generated successfully");
		    } else {
		        System.out.println("Error generating PDF");
		    }
		    
	        return pdfFile;
	    }
	 
	   
	   
//	    @GetMapping("/generate/{id}")
//	    public ResponseEntity<ByteArrayResource> generatePdf(@PathVariable Integer id, Model model) throws IOException {
//	        // idをModelに追加
//	        model.addAttribute("id", id);
////	        @GetMapping("/generate")
////		    public ResponseEntity<ByteArrayResource> generatePdf(Model model) throws IOException {
//		        // idをModelに追加
////		        model.addAttribute("id", id);
//	    	
//	        	// 現在のユーザーの認証情報を取得
//	            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//	            // 認証されたユーザーのIDを取得
//	            Integer currentUserId = ((CustomUserDetails) authentication.getPrincipal()).getId();
//
//	            // ユーザー情報を取得
//	            Users user = userService.getUser(currentUserId);
//	            model.addAttribute("user", user);
//	        	
//	            // 残業申請データ取得
//	            Requests request = requestService.getRequest(id);	//requestsテーブルのid
//	            model.addAttribute("request", request);
//	            //表示用に編集、モデルに設定
//	            if (request != null) {
//	            	editForDisplayService.editRequestForm(model, request);
//	            }
//	            
//	            
//
//	            
//	    	
//	        model.addAttribute("title", "Sample PDF");
//	        model.addAttribute("message", "This is a sample PDF generated from Thymeleaf.");
//	        
//	        // 他のモデルデータ
//	        model.addAttribute("pdfGeneration", true); // PDF生成フラグ
//	        
//	        Context context = new Context();
//	        context.setVariables(model.asMap());		 //id情報も含む
//     
//	        String htmlContent = templateEngine.process("request/detail", context);
//	        
//	     // HTMLコンテンツ全体を生成する
//	        Document document = Jsoup.parse(htmlContent);
//	        
//	        // PDF化したい部分（id="pdf-content"）のみを抽出
//	        Element pdfContent = document.getElementById("pdf-content");
//	        
//	        // 抽出した部分のHTMLを取得
//	        String filteredHtml = pdfContent.outerHtml();
//	        
//	        
//	        
//	        // PDFを生成
////	        byte[] pdfBytes = pdfService.generatePdf(htmlContent);
//	        byte[] pdfBytes = pdfService.generatePdf(filteredHtml);
//	        
//	        // PDFをレスポンスとして返す
//	        ByteArrayResource resource = new ByteArrayResource(pdfBytes);
//	        return ResponseEntity.ok()
//	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=generated.pdf")
//	                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
//	                .body(resource);
//	    }
}
