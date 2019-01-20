package org.luban.common.encrypt.uuid;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * UUID generator
 */
public class UUIDGeneratorMd5 {
    private static String baseUUID = null;
    private static long incrementingValue = 0;


    private static Random myRand = null;

    /**
     * MD5 a random string with localhost/date etc will return 128 bits
     * construct a string of 18 characters from those bits.
     *
     * @return string
     */
    public static String getUUID() {
        if (baseUUID == null) {
            getInitialUUID();
        }
        long i = ++incrementingValue;
        if (i >= Long.MAX_VALUE || i < 0) {
            incrementingValue = 0;
            i = 0;
        }
        return baseUUID + System.currentTimeMillis() + i;
    }

    protected static synchronized void getInitialUUID() {
        if (baseUUID != null) {
            return;
        }
        if (myRand == null) {
            myRand = new Random();
        }
        long rand = myRand.nextLong();
        String sid;
        try {
            sid = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            sid = Thread.currentThread().getName();
        }
        StringBuffer sb = new StringBuffer();
        sb.append(sid);
        sb.append(":");
        sb.append(Long.toString(rand));
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            //todo have to be properly handled
        }
        md5.update(sb.toString().getBytes());
        byte[] array = md5.digest();
        StringBuffer sb2 = new StringBuffer();
        for (int j = 0; j < array.length; ++j) {
            int b = array[j] & 0xFF;
            sb2.append(Integer.toHexString(b));
        }
        int begin = myRand.nextInt();
        if (begin < 0) begin = begin * -1;
        begin = begin % 8;
        baseUUID = sb2.toString().substring(begin, begin + 18).toUpperCase();
    }
}
