package com.ki5s.base.system;

import java.util.ResourceBundle;
import javax.servlet.ServletContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import com.ki5s.base.configure.SystemSetting;

public class SystemMonitor implements ApplicationListener<ContextRefreshedEvent>{
    private Log log = LogFactory.getLog(SystemMonitor.class);

    @Autowired(required = false)
    private ServletContext servletContent;

    public void onApplicationEvent(ContextRefreshedEvent event) {
        ResourceBundle resource = ResourceBundle.getBundle("resources/appconfig");
        String path = resource.getString("context.path");
        String version = resource.getString("system.version");

        if (servletContent != null) {
            servletContent.setAttribute(SystemSetting.SYSTEM_PATH, path);
            servletContent.setAttribute(SystemSetting.SYSTEM_VERSION, version);
            log.info("系统版本:"+version);
        }
    }
}
