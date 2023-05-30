package com.huasheng.dingding.oaService;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

/**
 * WebService客户端
 * 
 */
public class WebServiceClient {

	/**
	 * 主方法
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		WebServiceConfig cfg = WebServiceConfig.getInstance();
		Object service = callService(cfg.getAddress(), cfg.getServiceClass());
		WebServiceClient kmCalendarWebserviceService = (WebServiceClient) service;
		add(kmCalendarWebserviceService);
		// 请在此处添加业务代码

	}
	
	/**
	 * 调用服务，生成客户端的服务代理
	 * 
	 * @param address WebService的URL
	 * @param serviceClass 服务接口全名
	 * @return 服务代理对象
	 * @throws Exception
	 */
	public static Object callService(String address, Class serviceClass)
			throws Exception {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		// 记录入站消息
		factory.getInInterceptors().add(new LoggingInInterceptor());
		// 记录出站消息
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		// 添加消息头验证信息。如果服务端要求验证用户密码，请加入此段代码
		// factory.getOutInterceptors().add(new AddSoapHeader());
		factory.setServiceClass(serviceClass);
		factory.setAddress(address);
		// 使用MTOM编码处理消息。如果需要在消息中传输文档附件等二进制内容，请加入此段代码
		// Map<String, Object> props = new HashMap<String, Object>();
		// props.put("mtom-enabled", Boolean.TRUE);
		// factory.setProperties(props);
        // 创建服务代理并返回
		return factory.create();
	}

	public static void add(WebServiceClient client){
		KmReviewParamterForm form = new KmReviewParamterForm();
		// 文档模板id
		form.setFdTemplateId("131eb0cfd7db55e6980e9ce4985a1387");
		// 文档标题
		form.setDocSubject("物料采购申请单");
		// 流程发起人
		form.setDocCreator("{\"PersonNo\": \"00012\"}");
		// 文档关键字
		form.setFdKeyword("[\"物料\", \"采购\"]");
		// 流程表单
		String formValues = "{\"fd_2eddbf023c8292\":\"张三\", \"fd_2edd2f83f68242\":\"咨询部\", \"fd_2edd2fa69f6fc6\":\"\", \"fd_2eddbf09f9bc96\":\"2011-10-26\", \"fd_2edd2fb18e7f90\":{\"fd_2edd2fb18e7f90.fd_2eddbef4da4688\":[\"555555\",\"777777\"], \"fd_2edd2fb18e7f90.fd_2edd2fc8001062\":[\"444444\",\"666666\"], \"fd_2edd2fb18e7f90.fdId\":[\"1332472122898ac618f3e22460cab595\",\"13324721228b50c184d82c44ceca5301\"]}}";
		form.setFormValues(formValues);
		// 审批过程
		String flowParam = "{auditNode:\"请审核\", futureNodeId:\"N7\", changeNodeHandlers:[\"N7:1183b0b84ee4f581bba001c47a78b2d9;131d019fbac792eab0f0a684c8a8d0ec\"]}";
		form.setFlowParam(flowParam);
//		client.addReview(form);
	}

}
