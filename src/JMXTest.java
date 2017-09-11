import java.lang.management.ManagementFactory;
import javax.management.*;
import com.sun.jdmk.comm.HtmlAdaptorServer;


public class JMXTest {
	public static void main(String[] args) throws Exception {

		MBeanServer server = MBeanServerFactory.createMBeanServer();
		try {
			ApplicationCache cache = new ApplicationCache();
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			ObjectName name = new ObjectName("org.javalobby.tnt.jmx:type=ApplicationCacheMBean,port=8082");
			HtmlAdaptorServer adaptor = new HtmlAdaptorServer();
			server.registerMBean(adaptor, name);
			mbs.registerMBean(cache, name);
			adaptor.start();
			//imitateActivity(cache);
		} catch (MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstanceAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MBeanRegistrationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotCompliantMBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void imitateActivity(ApplicationCache cache) {
		while(true) {
			try {
				cache.cacheObject(new Object());
				Thread.sleep(1000);
			}
			catch(InterruptedException e) { }
		}
	}
}
