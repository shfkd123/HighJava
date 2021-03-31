package kr.or.ddit.rmi.server;

import java.rmi.RemoteException;
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

	@Override
	public void setFiles(FileInfoVO[] fInfo) throws RemoteException {
		//
		
	}

}
