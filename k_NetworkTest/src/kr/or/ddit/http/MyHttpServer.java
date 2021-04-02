package kr.or.ddit.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.util.StringTokenizer;

/**
 * 간단한 웹서버 예제
 */
public class MyHttpServer {
	private final int port = 80;
	private final String encoding = "UTF-8";
	
	/**
	 * 응답 헤더 생성하기
	 * @param contentLength 응답내용 크기
	 * @param mimeType 마임타입
	 * @return 바이트 배열
	 */
	private byte[] makeResponseHeader(int contentLength, String mimeType) {
		
		String header = "HTTP/1.1 200 OK\r\n" //status Line
					+ "Server:MyHTTPServer 1.0\r\n"
					+ "Content-length: " + contentLength + "\r\n"
					+ "Content-type: " + mimeType + "; charset="
					+ this.encoding + "\r\n\r\n"; //\r\n\r\n ---> empty Line
		
		return header.getBytes();
		
	}
	
	/**
	 * 응답 내용 생성하기
	 * @param filePath 응답으로 사용할  파일경로
	 * @return 바이트배열 데이터
	 * @throws IOException
	 */
	private byte[] makeResponseBody(String filePath) throws IOException {
		//파일경로를 파라미터로 받는다.
		FileInputStream fis = null;
		byte[] data = null;
		try {
			File file = new File(filePath);
			data = new byte[(int)file.length()]; //Length로 필요한 byte수를 확보한다.
			
			fis = new FileInputStream(file); 
			fis.read(data);//바이트 배열로 파일을 읽는다.
			
		}finally {
			if(fis != null) {
				fis.close();
			}
		}
		
		return data; //바이트 배열 리턴한다.
	}
	
	/**
	 * 에러 페이지 생성
	 * @param out
	 * @param statusCode
	 * @param errMsg
	 */
	private void makeErrorPage(OutputStream out, int statusCode, String errMsg) {
		String statusLine = "HTTP/1.1" + " " + statusCode + " " + errMsg;
		
		try {
			out.write(statusLine.getBytes());
			out.flush();
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * HTTP 서버 시작
	 */
	public void startServer() {
		 try(ServerSocket server = new ServerSocket(this.port)) { //port는 멤버변수로 선언해놓음.
			 
			 while(true) { //무한루프
				 try {
					Socket socket = server.accept(); //블락상태에서 연결 요청으로 소켓 객체가 생성되면 풀린다. 
					
					//Http 요청처리를 위한 스레드 객체 생성
					HttpHandler handler = new HttpHandler(socket);
					new Thread(handler).start();
					
					
				} catch(IOException ex) {
					System.out.println("커넥션 오류!!!");
					ex.printStackTrace();
				} catch(RuntimeException ex) {
					System.err.println("알 수 없는 오류!!!");
					ex.printStackTrace();
				}
			 }
			 
		 }catch(IOException ex) {
			 ex.printStackTrace();
		 }
	}
	
	/**
	 * HTTP 요청 처리를 위한 Runnable 객체
	 */
	private class HttpHandler implements Runnable {
		private final Socket socket;
		
		public HttpHandler(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			OutputStream out = null;
			BufferedReader br = null;
			
			try {
				out = new BufferedOutputStream(
						socket.getOutputStream());
				br = new BufferedReader( //HTTP파일은 텍스트 파일이여서 문자열을 읽기 위해 Reader  
						new InputStreamReader(
								socket.getInputStream())); //소켓의 데이터를 읽음. 
				
				//요청헤더 정보 파싱하기
				StringBuilder request = new StringBuilder(); //문자열을 효율적으로 다루기 위해 String Builder사용
				while(true) {
					String str = br.readLine(); //한줄 한줄 문자열을 읽어준다. 
					
					if(str.equals("")) break; //emptyLine체크 --> Enter부분을 읽을 때(끝) while문을 빠져나온다. 
					
					request.append(str + "\n"); //Header부분만 읽어온다. 
				}
				
				System.out.println("요청헤더 :\n" + request.toString());
				
				String reqPath = "";
				
				//요청페이지 정보 가져오기
				StringTokenizer st = new StringTokenizer(request.toString()); //default는 공백 (공백으로 쪼개짐)
				//String을 의미있게 쪼개기 위해 StringTokenizer 클래스를 이용함.
				while(st.hasMoreTokens()) {
					String token = st.nextToken(); //차례대로 토큰, 쪼갠것들을 가져옴. 
					if(token.startsWith("/")) {
						reqPath = token;
					}
				}
				
				//상대경로(프로젝트 폴더 기준) 설정
				String fileName = "./WebContent" + reqPath;
				
				//해당 파일이름을 이용하여 Content-type 정보 추출하기
				String contentType = URLConnection
									.getFileNameMap()
									.getContentTypeFor(fileName);
				//fileName으로 파일 유형을 파악한다. --> MIME타입으로 
				
				if(contentType == null) {
					contentType = "text/css";
				}
				
				
				System.out.println("contentType => " + contentType);
				
				File file = new File(fileName);
				if(!file.exists()) {
					makeErrorPage(out, 404, "Not Found");
					return;
				}
				
				byte[] body = makeResponseBody(fileName); //바이트 배열로 만든다.
				byte[] header = makeResponseHeader(body.length, contentType);
				
				//요청헤더가 HTTP/1.0이나 그 이후의 버전을 지원할 경우 MIME 헤더를 전송한다.
				if(request.toString().indexOf("HTTP/") != -1) {
					out.write(header); //응답헤더 보내기
				}
				
				System.out.println("응답헤더:\n" + new String(header));
				out.write(body); //응답내용 보내기
				out.flush();
				
			}catch (IOException ex) {
				System.err.println("입출력 오류!!!");
				ex.printStackTrace();
			}finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		new MyHttpServer().startServer();
	}
}
