package kr.or.ddit.upload;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 예전에 많이 사용하던 방식인데, 요즘도 자주 사용하는 예제임. 
 * 자카르타 프로젝트의 fileupload 모듈을 이용한 파일업로드 예제
 */
public class UploadServlet2 extends HttpServlet {
	
	private static final String UPLOAD_DIR = "upload_files";
	// 메모리 임계크기(이 크기가 넘어가면 레파지토리 위치에 임시파일로 저장됨)
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;
	// 파일 1개당 최대 크기 --> 파일 업로드 시 제어하고 싶어서 파일 1개당 최대 크기 지정
	private static final long MAX_FILE_SIZE = 1024 * 1024 * 40;
	// 요청 파일 최대 크기 --> 최대로 요청할 수 있는 크기
	private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 50;
	
	@Override
	protected void doPost(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		
		if(ServletFileUpload.isMultipartContent(req)) {
			// 인코딩 타입이 multipart/form-data 인 경우...
			
			// 폼필드 데이터 저장용...
			Map<String, String> formMap = 
					new HashMap<String, String>();
			
			DiskFileItemFactory factory = 
					new DiskFileItemFactory();
			factory.setSizeThreshold(MEMORY_THRESHOLD);
			factory.setRepository(
					new File(
					System.getProperty("java.io.tmpdir")));
			System.out.println("임시경로 : " 
					+ System.getProperty("java.io.tmpdir"));
			
			ServletFileUpload upload = 
					new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE); //파일 1개당 최대 크기
			upload.setSizeMax(MAX_REQUEST_SIZE); //요청 파일 최대 크기
			
			// 웹애플리케이션 루트 디렉토리 기준 업로드 경로 설정하기
			String uploadPath = getServletContext().getRealPath("") 
					+ File.separator + UPLOAD_DIR;
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir(); //없으면 생성 
			}
			
			List<FileItem> formItems;
			
			try {
				formItems = upload.parseRequest(req); //요청 객체로부터 파싱 (실제 파싱 하는 부분) 
			
				if(formItems != null && formItems.size() > 0) {
					for(FileItem item : formItems) { //조회
						if(!item.isFormField()) {// FormField가 아닌 경우 -> 파일인 경우... 업로드 작업 시작
							// 전체경로를 제외한 파일명만 추출하기
							String fileName = 
									new File(item.getName()).getName(); //처음의 getName에는 경로가 들어옴. 거기에 다시 getName하면 ~.jpg 파일 이름이 넘어옴 (substr해도 됨)
							String filePath = 
								uploadPath + File.separator + fileName; //separator는 /를 뜻함. ====> 하나의 경로를 만들음. 
 							File storeFile = new File(filePath); //경로를 가지고 파일객체를 만듦.
							item.write(storeFile); //경로에 해당하는 파일 객체에 write
							req.setAttribute("message", 
									"업로드 완료됨. => 파일명 : " + fileName); //실제 업로드가 완료되는 부분 
							
						}else { // 폼 데이터인 경우... 도 생각 해줘야 함.
							// 폼필드의 값이 한글인 경우에는 해당 문자열을 적절히 변환을
							// 해주어야 한다.
							// 방법1. value = new String(fileItem.getString()
							//            .getBytes("8859-1"), "UTF-8");
							// 방법2. value = fileItem.getString("UTF-8");
							formMap.put(item.getFieldName(), 
									item.getString("UTF-8")); //인코딩 처리로 데이터 값을 가져옴. 
						}
					}
				}
				
			} catch (Exception e) {
				req.setAttribute("message", "예외발생: " + e.getMessage()); 
				e.printStackTrace();
			}
			//잘가져왔는지 form 데이터 한번 찍어 봄. 
			for(Entry<String, String> entry : formMap.entrySet()) {
				System.out.println("파라미터명 : " + entry.getKey());
				System.out.println("파라미터값 : " + entry.getValue());
			}
			resp.setContentType("text/html");
			resp.getWriter().print("업로드 완료됨.!!!"); //완료가되면 상대방에게 완료됐다고 보내준다.
	
		}	
	}
}
