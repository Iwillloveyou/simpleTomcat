package Core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import com.sun.org.apache.bcel.internal.util.ClassLoader;

import Servelet.MyServlet;

public class TomcatCore {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InterruptedException {
		System.out.println("Hellow,I'm MyTomcat ,and I'm in the initialization... ");  
        //Thread.sleep(3000);  
        System.out.println("Now,I have finished the initialization .");  
        //第二行第三行显示tomcat完成初始化并将进入servlet
        //Thread.sleep(1000);  
        System.out.println("Now I will start  the Servlet.");  
        //通过TomcatConf.txt配置文件下的servlet文件系统路径及类包路径信息，加载所部署servlet
		FileReader reader = new FileReader("./TomcatConf.txt");
		BufferedReader br = new BufferedReader(reader);
		String path = br.readLine();
		String className = br.readLine();
		br.close();
		reader.close();
		//根据已获取Servlet的项目路径名及类包路径名通过URL类加载器加载文件系统中的某个.class 
		File file = new File(path);
		URL url = file.toURI().toURL();
		@SuppressWarnings("resource")
		URLClassLoader  loader = new URLClassLoader(new URL[]{url});////创建持有我们所部署的"web项目"路径的URL类加载器，以使Tomcat之外的"web"纳入Tomcat的classpath之中。  
		Class<?> tidyClazz = loader.loadClass(className); //利用反射加载类  
		MyServlet serv=(MyServlet)tidyClazz.newInstance();//转化为Servlet接口执行service操作。  
        //实际的Tomcat并不会直接在这里运行我们的service而是初始化web项目并在一个等待状态中伺机触发service方法，并进入其中的doXXX等函数处理实际请求
		serv.service();//当然，实际的tomcat并不在这里调用service，而仅仅是进入事件循环，在有浏览器请求时才调用service。           
	}
}
