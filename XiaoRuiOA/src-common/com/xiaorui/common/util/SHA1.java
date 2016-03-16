// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2007-7-21 11:55:00 AM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   SHA1.java

package com.xiaorui.common.util;

public class SHA1
{

    public SHA1()
    {
    }

    public static String b64_hmac_sha1(String key, String data)
    {
        return binb2b64(core_hmac_sha1(key, data));
    }

    public static String b64_sha1(String s)
    {
        s = s != null ? s : "";
        return binb2b64(core_sha1(str2binb(s), s.length() * 8));
    }

    private static String binb2b64(int binarray[])
    {
        String tab = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        String str = "";
        binarray = strechBinArray(binarray, binarray.length * 4);
        for(int i = 0; i < binarray.length * 4; i += 3)
        {
            int triplet = (binarray[i >> 2] >> 8 * (3 - i % 4) & 0xff) << 16 | (binarray[i + 1 >> 2] >> 8 * (3 - (i + 1) % 4) & 0xff) << 8 | binarray[i + 2 >> 2] >> 8 * (3 - (i + 2) % 4) & 0xff;
            for(int j = 0; j < 4; j++)
                if(i * 8 + j * 6 > binarray.length * 32)
                    str = str + "=";
                else
                    str = str + tab.charAt(triplet >> 6 * (3 - j) & 0x3f);

        }

        return cleanB64Str(str);
    }

    private static String binb2hex(int binarray[])
    {
        String hex_tab = "0123456789abcdef";
        String str = "";
        for(int i = 0; i < binarray.length * 4; i++)
        {
            char a = hex_tab.charAt(binarray[i >> 2] >> (3 - i % 4) * 8 + 4 & 0xf);
            char b = hex_tab.charAt(binarray[i >> 2] >> (3 - i % 4) * 8 & 0xf);
            str = str + (new Character(a)).toString() + (new Character(b)).toString();
        }

        return str;
    }

    private static String binb2str(int bin[])
    {
        String str = "";
        int mask = 255;
        for(int i = 0; i < bin.length * 32; i += 8)
            str = str + (char)(bin[i >> 5] >>> 24 - i % 32 & mask);

        return str;
    }

    @SuppressWarnings("unused")
	private static int bit_rol(int num, int cnt)
    {
        return num << cnt | num >>> 32 - cnt;
    }

    private static String cleanB64Str(String str)
    {
        str = str != null ? str : "";
        int len = str.length();
        if(len <= 1)
            return str;
        char trailChar = str.charAt(len - 1);
        String trailStr = "";
        for(int i = len - 1; i >= 0 && str.charAt(i) == trailChar; i--)
            trailStr = trailStr + str.charAt(i);

        return str.substring(0, str.indexOf(trailStr));
    }

    private static int[] complete216(int oldbin[])
    {
        if(oldbin.length >= 16)
            return oldbin;
        int newbin[] = new int[16 - oldbin.length];
        for(int i = 0; i < newbin.length; i++)
            newbin[i] = 0;

        return concat(oldbin, newbin);
    }

    private static int[] concat(int oldbin[], int newbin[])
    {
        int retval[] = new int[oldbin.length + newbin.length];
        for(int i = 0; i < oldbin.length + newbin.length; i++)
            if(i < oldbin.length)
                retval[i] = oldbin[i];
            else
                retval[i] = newbin[i - oldbin.length];

        return retval;
    }

    private static int[] core_hmac_sha1(String key, String data)
    {
        key = key != null ? key : "";
        data = data != null ? data : "";
        int bkey[] = complete216(str2binb(key));
        if(bkey.length > 16)
            bkey = core_sha1(bkey, key.length() * 8);
        int ipad[] = new int[16];
        int opad[] = new int[16];
        for(int i = 0; i < 16; i++)
        {
            ipad[i] = 0;
            opad[i] = 0;
        }

        for(int i = 0; i < 16; i++)
        {
            ipad[i] = bkey[i] ^ 0x36363636;
            opad[i] = bkey[i] ^ 0x5c5c5c5c;
        }

        int hash[] = core_sha1(concat(ipad, str2binb(data)), 512 + data.length() * 8);
        return core_sha1(concat(opad, hash), 672);
    }

    private static int[] core_sha1(int x[], int len)
    {
        int size = len >> 5;
        x = strechBinArray(x, size);
        x[len >> 5] |= 128 << 24 - len % 32;
        size = ((len + 64 >> 9) << 4) + 15;
        x = strechBinArray(x, size);
        x[((len + 64 >> 9) << 4) + 15] = len;
        int w[] = new int[80];
        int a = 0x67452301;
        int b = 0xefcdab89;
        int c = 0x98badcfe;
        int d = 0x10325476;
        int e = 0xc3d2e1f0;
        for(int i = 0; i < x.length; i += 16)
        {
            int olda = a;
            int oldb = b;
            int oldc = c;
            int oldd = d;
            int olde = e;
            for(int j = 0; j < 80; j++)
            {
                if(j < 16)
                    w[j] = x[i + j];
                else
                    w[j] = rol(w[j - 3] ^ w[j - 8] ^ w[j - 14] ^ w[j - 16], 1);
                int t = safe_add(safe_add(rol(a, 5), sha1_ft(j, b, c, d)), safe_add(safe_add(e, w[j]), sha1_kt(j)));
                e = d;
                d = c;
                c = rol(b, 30);
                b = a;
                a = t;
            }

            a = safe_add(a, olda);
            b = safe_add(b, oldb);
            c = safe_add(c, oldc);
            d = safe_add(d, oldd);
            e = safe_add(e, olde);
        }

        int retval[] = new int[5];
        retval[0] = a;
        retval[1] = b;
        retval[2] = c;
        retval[3] = d;
        retval[4] = e;
        return retval;
    }

    @SuppressWarnings("unused")
    private static void doTest()
    {
		String key = "key";
        String data = "king";
        //System.out.println("hex_sha1(" + data + ")=" + hex_sha1(data));
        //System.out.println("b64_sha1(" + data + ")=" + b64_sha1(data));
        //System.out.println("str_sha1(" + data + ")=" + str_sha1(data));
        //System.out.println("hex_hmac_sha1(" + key + "," + data + ")=" + hex_hmac_sha1(key, data));
        //System.out.println("b64_hmac_sha1(" + key + "," + data + ")=" + b64_hmac_sha1(key, data));
        //System.out.println("str_hmac_sha1(" + key + "," + data + ")=" + str_hmac_sha1(key, data));
    }

    public static String hex_hmac_sha1(String key, String data)
    {
        return binb2hex(core_hmac_sha1(key, data));
    }

    public static String hex_sha1(String s)
    {
        s = s != null ? s : "";
        return binb2hex(core_sha1(str2binb(s), s.length() * 8));
    }

    private static int rol(int num, int cnt)
    {
        return num << cnt | num >>> 32 - cnt;
    }

    private static int safe_add(int x, int y)
    {
        int lsw = (x & 0xffff) + (y & 0xffff);
        int msw = (x >> 16) + (y >> 16) + (lsw >> 16);
        return msw << 16 | lsw & 0xffff;
    }

    private static int sha1_ft(int t, int b, int c, int d)
    {
        if(t < 20)
            return b & c | ~b & d;
        if(t < 40)
            return b ^ c ^ d;
        if(t < 60)
            return b & c | b & d | c & d;
        else
            return b ^ c ^ d;
    }

    private static int sha1_kt(int t)
    {
        return t >= 20 ? t >= 40 ? t >= 60 ? 0xca62c1d6 : 0x8f1bbcdc : 0x6ed9eba1 : 0x5a827999;
    }

    @SuppressWarnings("unused")
	private static boolean sha1_vm_test()
    {
        return hex_sha1("abc").equals("a9993e364706816aba3e25717850c26c9cd0d89d");
    }

    public static String str_hmac_sha1(String key, String data)
    {
        return binb2str(core_hmac_sha1(key, data));
    }

    public static String str_sha1(String s)
    {
        s = s != null ? s : "";
        return binb2str(core_sha1(str2binb(s), s.length() * 8));
    }

    private static int[] str2binb(String str)
    {
        str = str != null ? str : "";
        int tmp[] = new int[str.length() * 8];
        int mask = 255;
        for(int i = 0; i < str.length() * 8; i += 8)
            tmp[i >> 5] |= (str.charAt(i / 8) & mask) << 24 - i % 32;

        int len = 0;
        for(int i = 0; i < tmp.length && tmp[i] != 0;)
        {
            i++;
            len++;
        }

        int bin[] = new int[len];
        for(int i = 0; i < len; i++)
            bin[i] = tmp[i];

        return bin;
    }

    private static int[] strechBinArray(int oldbin[], int size)
    {
        int currlen = oldbin.length;
        if(currlen >= size + 1)
            return oldbin;
        int newbin[] = new int[size + 1];
        for(int i = 0; i < size; i++)
            newbin[i] = 0;

        for(int i = 0; i < currlen; i++)
            newbin[i] = oldbin[i];

        return newbin;
    }

    public static String encryptWithSHA1(String str)
    {
        return b64_sha1(str).substring(0, 24);
        //return b64_sha1(str);
    }

    public static void main(String args1[])
    {
    	System.out.println(encryptWithSHA1("coltrip"));
    }

    @SuppressWarnings("unused")
	private static final boolean hexcase = false;
    @SuppressWarnings({ "unused" })
	private static final String b64pad = "=";
    @SuppressWarnings("unused")
	private static final int chrsz = 8;
}