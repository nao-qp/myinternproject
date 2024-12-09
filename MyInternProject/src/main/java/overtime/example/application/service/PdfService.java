package overtime.example.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import overtime.example.domain.user.model.Requests;
import overtime.example.domain.user.model.Users;
import overtime.example.domain.user.service.RequestService;
import overtime.example.domain.user.service.UserService;
import overtime.example.domain.user.service.impl.CustomUserDetails;

@Service
public class PdfService {

		@Autowired
        private TemplateEngine templateEngine;
		
		
		   @Autowired
		   private UserService userService;
		   
		   @Autowired
		   private RequestService requestService;
		   
		   @Autowired
		   private EditForDisplayService editForDisplayService;
		   
		   
        // HTMLを生成するメソッド
        public String generateHtmlContent(Integer id, Model model) {
            // ThymeleafContextに変数を設定
            Context context = new Context();
            
            
            
            //必要な情報をセット
         // 他のモデルデータ
  	      model.addAttribute("pdfGeneration", true); // PDF生成フラグ
  	      
  		// 現在のユーザーの認証情報を取得
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

          //認証情報がない場合は、ログインページにリダイレクトする
          if (authentication == null) {
          	 return "redirect:/user/login";
          }

          // 認証されたユーザーのIDを取得
          Integer currentUserId = ((CustomUserDetails) authentication.getPrincipal()).getId();
          // 権限を取得
          String role = authentication.getAuthorities().stream()
          				.map(GrantedAuthority::getAuthority)
          				.findFirst()
          				.orElse(null);
          model.addAttribute("role", role);
          // ユーザー情報を取得
          Users user = userService.getUser(currentUserId);
          model.addAttribute("user", user);

          // 残業申請データ取得
          Requests request = requestService.getRequest(id);	//requestsテーブルのid
          model.addAttribute("request", request);
          //表示用に編集、モデルに設定
          if (request != null) {
          	editForDisplayService.editRequestForm(model, request);
          }
          

          
          
              
            context.setVariables(model.asMap());  // Modelの内容をContextにコピー
           

            // テンプレートファイルを処理してHTMLを生成
            return templateEngine.process("request/detail", context); // templateNameは実際のテンプレートファイル名
        }
        
//
//	    /**
//	     * HTMLをPDFに変換するメソッド
//	     */
//	    public byte[] generatePdf(String htmlContent) throws IOException, DocumentException {
//	    	
//	    	// HTMLのコンテンツとリソースに対するベースURI
//	    	String baseUri = "http://localhost:8080"; // 例えばローカル開発環境の場合
//
//	        // HTMLのコンテンツに必要なリソース（CSS）のリンクを追加
//	        String htmlWithResources = "<html><head>" +
//	                                   "<link rel=\"stylesheet\" href=\"/webjars/bootstrap/css/bootstrap.min.css\"/>" +
//	                                   "<link rel=\"stylesheet\" href=\"/css/style.css\"/>" +
//	                                   "</head><body>" +
//	                                   htmlContent +  // ここで引数のhtmlContentを追加
//	                                   "</body></html>";
//
//	    	
//	    	
//	    	
//	        // Flying SaucerにHTMLを渡してPDFを生成
//	        ITextRenderer renderer = new ITextRenderer();
//	        
//	     // HTMLコンテンツとベースURIを指定してHTMLを解析
//	        renderer.setDocumentFromString(htmlWithResources, baseUri);
//	        
//	        
//	      //フォント
////          renderer.getFontResolver().addFont("classpath:fonts/NotoSerifJP[wght].ttf", true);
////          renderer.getFontResolver().addFont(getClass().getClassLoader().getResource("fonts/NotoSerifJP.ttf").getPath(), true);
//         
//          URL fontUrl = getClass().getClassLoader().getResource("fonts/NotoSerifJP.ttf");
////          if (fontUrl != null) {
////              System.out.println("Font file found at: " + fontUrl.getPath());
////              renderer.getFontResolver().addFont(fontUrl.getPath(), true);
////          } else {
////              System.out.println("Font file not found.");
////          }
//          
//          if (fontUrl != null) {
//              System.out.println("Font file found at: " + fontUrl.getPath());
//
//              // WAR/JAR 内のリソースはファイルパスではなく、InputStream で取得して使う
//              try (InputStream fontStream = fontUrl.openStream()) {
//                  // 一時ファイルに書き込む
//                  File tempFontFile = File.createTempFile("NotoSerifJP", ".ttf");
//                  tempFontFile.deleteOnExit();  // JVM終了時に削除
//                  try (FileOutputStream fos = new FileOutputStream(tempFontFile)) {
//                      byte[] buffer = new byte[1024];
//                      int length;
//                      while ((length = fontStream.read(buffer)) > 0) {
//                          fos.write(buffer, 0, length);
//                      }
//                  }
//
//                  // 一時ファイルを Flying Saucer に登録
//                  renderer.getFontResolver().addFont(tempFontFile.getAbsolutePath(), true);
//              }
//          } else {
//              System.out.println("Font file not found.");
//          }
//          
//          
//          
//          
//          
////          InputStream fontStream = getClass().getClassLoader().getResourceAsStream("fonts/NotoSerifJP.ttf");
////          if (fontStream != null) {
////              System.out.println("Font file found in resources.");
////              renderer.getFontResolver().addFont(fontStream, true);
////          } else {
////              System.out.println("Font file not found.");
////          }
//
//	        
//	        
//	        renderer.setDocumentFromString(htmlContent);
//	        renderer.layout();
//	       
//	         
//            
//	        // PDFをバイト配列として生成
//	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//	        renderer.createPDF(outputStream);
//
//	        return outputStream.toByteArray();
//	    }
}
