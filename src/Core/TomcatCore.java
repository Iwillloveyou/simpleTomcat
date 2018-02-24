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
        //�ڶ��е�������ʾtomcat��ɳ�ʼ����������servlet
        //Thread.sleep(1000);  
        System.out.println("Now I will start  the Servlet.");  
        //ͨ��TomcatConf.txt�����ļ��µ�servlet�ļ�ϵͳ·�������·����Ϣ������������servlet
		FileReader reader = new FileReader("./TomcatConf.txt");
		BufferedReader br = new BufferedReader(reader);
		String path = br.readLine();
		String className = br.readLine();
		br.close();
		reader.close();
		//�����ѻ�ȡServlet����Ŀ·���������·����ͨ��URL������������ļ�ϵͳ�е�ĳ��.class 
		File file = new File(path);
		URL url = file.toURI().toURL();
		@SuppressWarnings("resource")
		URLClassLoader  loader = new URLClassLoader(new URL[]{url});////�������������������"web��Ŀ"·����URL�����������ʹTomcat֮���"web"����Tomcat��classpath֮�С�  
		Class<?> tidyClazz = loader.loadClass(className); //���÷��������  
		MyServlet serv=(MyServlet)tidyClazz.newInstance();//ת��ΪServlet�ӿ�ִ��service������  
        //ʵ�ʵ�Tomcat������ֱ���������������ǵ�service���ǳ�ʼ��web��Ŀ����һ���ȴ�״̬���Ż�����service���������������е�doXXX�Ⱥ�������ʵ������
		serv.service();//��Ȼ��ʵ�ʵ�tomcat�������������service���������ǽ����¼�ѭ�����������������ʱ�ŵ���service��           
	}
}
