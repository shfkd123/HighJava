package kr.or.ddit.basic;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class MySessionBindingListener implements HttpSessionBindingListener {

	/**
	 * HTTP세션 영역내에서 HttpSessionBindingListener를 구현한 객체가 바인딩 되면 호출됨.
	 */
	@Override
	public void valueBound(HttpSessionBindingEvent hsbe) {
		String attrName = hsbe.getName();
		System.out.println("[MySessionBindingListener]" + this.getClass()
							+ " 객체가 " + attrName + "으로 바인딩 됨.");	
	}

	/**
	 * HTTP세션 영역내에서 HttpSessionBindingListener를 구현한 객체가 언바인딩 되면 호출됨.
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent hsbe) {
		String attrName = hsbe.getName();
		System.out.println("[MySessionBindingListener]" + this.getClass()
							+ " 객체가 " + attrName + "으로 바인딩 됨.");	
	}
}
