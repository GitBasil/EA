package soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
@EnableWs 
@Configuration 
public class WebServiceConfig extends WsConfigurerAdapter { 
   @SuppressWarnings({"unchecked", "rawtypes" })
   @Bean 
   public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) { 
      MessageDispatcherServlet servlet = new MessageDispatcherServlet(); 
      servlet.setApplicationContext(applicationContext); 
      servlet.setTransformWsdlLocations(true); 
      return new ServletRegistrationBean(servlet, "/ws/*"); 
   } 
 
   @Bean(name = "calc") 
   public DefaultWsdl11Definition calcdefaultWsdl11Definition(XsdSchema calcSchema) { 
      DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition(); 
      wsdl11Definition.setPortTypeName("CalcPort"); 
      wsdl11Definition.setLocationUri("/ws"); 
      wsdl11Definition.setTargetNamespace("http://springtraining/calculator"); 
      wsdl11Definition.setSchema(calcSchema); 
      return wsdl11Definition; 
   } 
 
   @Bean 
   public XsdSchema calcSchema() { 
      return new SimpleXsdSchema(new ClassPathResource("xsds/calculator.xsd")); 
   } 
}