package kr.or.ddit.rmi.server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rmi.inf.RemoteInterface;
import kr.or.ddit.rmi.vo.FileInfoVO;
import kr.or.ddit.rmi.vo.TestVO;

/**
 * RMI용 서비스를 제공하는 객체 RMI용 인터페이스를 구현하고 UnicastRemoteObject객체를 상속해서 작성한다.
 */

//이 객체를 원격으로 동작할 것임. (다른 사람들이 원격으로 접속해서 사용할 수 있도록)
public class RemoteServer extends UnicastRemoteObject implements RemoteInterface{

	protected RemoteServer() throws RemoteException { //객체를 만드는 순간에도 예외가 있을 수 있음! RemoteException 사용해야함.
		super();
		
	}
	
	public static void main(String[] args) {
		try {
			//구현한 RMI용 객체를 클라이언트에서 사용할 수 있도록
			//RMI서버에 등록한다.
			
			//1. RMI용 인터페이스를 구현한 객체 생성
			RemoteInterface inf = new RemoteServer();
			
			//2. 구현한 객체를 클라이언트에서 찾을 수 있도록 
			// Registry객체를 생성해서 등록한다.
			
			// 포트 번호를 지정하여 Registry객체 생성(기본포트: 1099)
			Registry reg = LocateRegistry.createRegistry(8888);
			//Registry는 서버 역할, Registry 서비스를 제공할 포트번호를 알려준다.
			
			// Registry 서버에서 제공하고자 하는 객체 등록
			// 형식) Registry객체변수.rebind("객체의 Alias", 객체);
			reg.rebind("server", inf); //이 서버에 내가 제공할 객체 등록 이름을 server로 지정했음.
			
			//bind는 처음에 등록할 때 사용
			//rebind는 기존에 bind 했던게 있어도 다시 덮어서 등록한다.
			
			System.out.println("서버가 준비되었습니다.");
	
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}
 
	@Override
	public int doRemotePrint(String str) throws RemoteException {
		//원격객체를 이용해서 원격객체.length 파라미터로 받은 str의 사이즈를 리턴
		int length = str.length();
		System.out.println("클라이언트에서 보내온 메시지 : " + str);
		System.out.println("출력 끝.");
		
		return length;
	}
 
	@Override
	public void doPrintList(List<String> list) throws RemoteException {
		System.out.println("클라이언트에서 보낸 List값들...");
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i+1) + "번째 : " + list.get(i));
		}
		System.out.println("List 출력 끝...");
		
	}

	@Override
	public void doPrintVO(TestVO vo) throws RemoteException {
		//전달된 객체 출력
		System.out.println("클라이언트에서 보내온 TestVO객체 값 출력");
		System.out.println("testId : " + vo.getTestId());
		System.out.println("testNum : " + vo.getTestNum());
		System.out.println("TestVO 객체 출력 끝...");
		
	}

	//파일저장기능
	@Override
	public void setFiles(FileInfoVO[] fInfo) throws RemoteException {
		//RMI가 FileInfoVO 배열을 받음
		FileOutputStream fos = null;

		String dir = "d:C_Lib/"; // 파일이 저장될 폴더 지정

		System.out.println("파일 저장 시작...");

		for (FileInfoVO fvo : fInfo) {
			try {
				//하나씩 꺼낸다. 
				fos = new FileOutputStream(dir + fvo.getFileName());
				fos.write(fvo.getFileData()); //바이트배열이기 때문에 한번에 write 가능하다.
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				// 클라이언트에서 전달한 파일데이터(byte[])를 서버측에 저장한다.
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("파일 저장 완료...");
	}
}
