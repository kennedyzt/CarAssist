package com.ruiyi.carassistant.web.controller;

import com.ruiyi.carassistant.service.OBDExceptionInfoService;
import com.ruiyi.carassistant.utils.FileUtil;
import com.ruiyi.carassistant.utils.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by Lenovo on 2016/12/28.
 */
@Controller
public class ObdLogController {

    @Autowired
    private OBDExceptionInfoService obdExceptionInfoService;

    @RequestMapping(value = "/obdlog/{userId}", method = RequestMethod.GET)
    public void getObdLogFile(@PathVariable("userId") String userId, HttpServletRequest request, HttpServletResponse response) {
        /*try (
                InputStream bis = new BufferedInputStream(new FileInputStream("/usr/logs/obd/" + userId + "/log.txt"));
                OutputStream bos = new BufferedOutputStream(response.getOutputStream());
        ) {
            byte[] buff = new byte[2048];
            int i = 0;
            while (-1 != (i = bis.read(buff))) {
                bos.write(buff, 0, i);
            }
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            FileUtil.randomDownload(new File("/usr/logs/obd/" + userId + "/" + userId + "-log.txt"), request, response);
        } catch (IOException e) {
            LoggerUtil.error(e.getMessage(), e);
        }
    }

}
