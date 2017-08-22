package com.ruiyi.carassistant.web.controller;

import com.ruiyi.carassistant.utils.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ruiyi.carassistant.utils.LoggerUtil;

@Api(tags = "差分包下载接口")
@Controller
public class UpdateFileDownload {

    /*@RequestMapping(value = "/OtaUpdatePkg/{version}/{fileName}.zip", method = RequestMethod.GET)
    public void fileDownload(@ApiParam(name="version", value="版本号")@PathVariable("version") String version,
            @ApiParam(name="fileName", value="文件名")@PathVariable("fileName") String fileName, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        java.io.BufferedInputStream bis = null;
        java.io.BufferedOutputStream bos = null;

        String ctxPath = request.getSession().getServletContext().getRealPath("/");
        String downLoadPath = ctxPath + "OtaUpdatePkg\\" + version + "\\" + fileName + ".zip";
        
        try {
            File file = new File(downLoadPath);
            if (!file.isFile()) {
                throw new RuntimeException("文件不存在...");
            }
            response.setContentType("application/x-msdownload; charset=utf-8");
            response.setHeader("Content-disposition",
                    "attachment; filename=" + new String(file.getName().getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(file.length()));
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            LoggerUtil.info("File download successful! version: " + version);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }*/
    
    @RequestMapping(value = "/OtaUpdatePkg/{version}/{fileName}.zip", method = RequestMethod.GET)
    public void downFile(@ApiParam(name="version", value="版本号")@PathVariable("version") String version,
            @ApiParam(name="fileName", value="文件名")@PathVariable("fileName") String fileName, HttpServletResponse response, HttpServletRequest request){
        
        String ctxPath = request.getSession().getServletContext().getRealPath("/");
        String location = ctxPath.substring(0, ctxPath.lastIndexOf("CarAssist")) + "OtaUpdatePkg/" + version + "/" + fileName + ".zip";

        try {
            FileUtil.randomDownload(new File(location), request, response);
        } catch (IOException e) {
            LoggerUtil.error(e.getMessage(), e);
        }
    }

}
