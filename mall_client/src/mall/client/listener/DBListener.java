package mall.client.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener 
public class DBListener implements ServletContextListener {

	// Tomcat stop
    public void contextDestroyed(ServletContextEvent sce)  { 

    }

    // Tomcat Start
    public void contextInitialized(ServletContextEvent sce)  { 
    	try {
			Class.forName("org.mariadb.jdbc.Driver");
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();

    	}

    }

}