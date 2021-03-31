package kr.or.ddit.rmi.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rmi.vo.FileInfoVO;
import kr.or.ddit.rmi.vo.TestVO;

//RMI를 할 때, 인터페이스를 구현하여 원격 객체를 받고 보내준다. 
//원격을 통해 받을 객체는 받는 사람도 어떤 객체인지는 정보를 알아야하기 때문에 인터페이스를 구현해 놓는다.
//RMI용 인터페이스는 Remote를 상속해야 한다.
public interface RemoteInterface extends Remote { //RMI에 맞게 사용해야함 규칙이 있음.
	//이 인터페이스는 선언된 모든 메서드에서 RemoteException을 throws 해야 한다.
	//RMI를 통해 정보를 가져올 때 발생할 문제(네트워크 이상 등등..)들을 ReomoteException을 사용하여 처리하도록 하기 위해.
	
	//이 곳에서 선언된 메서드의 파라미터 변수는 클라이언트에서 보내오는 데이터가 저장되고,
	//반환값은 서버에서 클라이언트 쪽으로 보내는 데이터가 된다. 
	
	public int doRemotePrint(String str) throws RemoteException;
	
	public void doPrintList(List<String> list) throws RemoteException;
	
	public void doPrintVO(TestVO vo) throws RemoteException;
	
	public void setFiles(FileInfoVO[] fInfo) throws RemoteException;
	

}
