package com.ruiyi.carassistant.utils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

/**
 * 负责文件的存储。
 * 
 * @author Yipd
 * @date 2016-10-19 下午5:47:37
 */
public class FileAccess implements Serializable {
    /**
     * 序列.
     */
    private static final long serialVersionUID = 1343784436693377391L;
    RandomAccessFile oSavedFile;
    long nPos;

    public FileAccess() throws IOException {
        this("", 0);
    }

    public FileAccess(String sName, long nPos) throws IOException {
        oSavedFile = new RandomAccessFile(sName, "rw");
        this.nPos = nPos;
        oSavedFile.seek(nPos);
    }

    public synchronized int write(byte[] b, int nStart, int nLen) {
        int n = -1;
        try {
            oSavedFile.write(b, nStart, nLen);
            n = nLen;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return n;
    }
}
