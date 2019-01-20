package com.shibro.nativeproducts.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class FileUtil {

	  /**
     * 在指定目录下生成文件
     * 
     * @param fileString 文件(路径+文件名)，文件不存在会自动创建
     * @param dataList 详细数据数据
     * @return
     */
    public static boolean createFile(String fileString, List<String> dataList){
        boolean isSucess=false;
//        String separator = System.getProperty("file.separator");
        File filePath = new File(fileString.substring(0, fileString.lastIndexOf("/")));
        mkDir(filePath);
        //判断文件是否存在，不存在则递归创建文件夹，创建文件
        File file = new File(fileString);
        FileOutputStream out=null;
        OutputStreamWriter osw=null;
        BufferedWriter bw=null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out);
            bw =new BufferedWriter(osw);
            if(dataList!=null && !dataList.isEmpty()){
                for(String data : dataList){
                    bw.append(data).append("\r\n");
                }
            }
            isSucess=true;
        } catch (Exception e) {
            isSucess=false;
        }finally{
            if(bw!=null){
                try {
                    bw.close();
                    bw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
            if(osw!=null){
                try {
                    osw.close();
                    osw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
            if(out!=null){
                try {
                    out.close();
                    out=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
        }
        
        return isSucess;
    }
    
    /**
     * 递归创建文件夹
     * @param file
     */
    public static void mkDir(File file){
    	if(file.getParentFile().exists()){
    		file.mkdir();
    	}else{
    		mkDir(file.getParentFile());
    		file.mkdir();
    	}
    }
    
    /**
     * 获取文件的md5值
     * @param file
     * @return 32位文件md5值
     */
    public static String getFileMD5(File file)
    {
//        File file = new File(fileName);
        if(!file.exists() || !file.isFile()){
            return "";
        }
        
        byte[] buffer = new byte[2048];
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            FileInputStream in = new FileInputStream(file);
            while(true){
                int len = in.read(buffer,0,2048);
                if(len != -1){
                    digest.update(buffer, 0, len);
                }else{
                    break;
                }
            }
            in.close();
            
            byte[] md5Bytes  = digest.digest();
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
            
            //String hash = new BigInteger(1,digest.digest()).toString(16);
            //return hash;
            
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }
    
    /**
     * 获取文件大小，kb
     * @param file
     * @return 文件大小，kb单位
     */
    public static int getFileSizeKB(File file){
    	int size = (int) ((Math.ceil(file.length()/1024)==0.0?1:(Math.ceil(file.length()/1024))));
    	return size;
    }
    
    /**
     * 文件改名
     * @return
     */
    public static boolean updateFileName(String path,String oldname,String newname){
    	
    	boolean flag =false;
    	
    	 if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名 
             File oldfile=new File(path+"/"+oldname); 
             File newfile=new File(path+"/"+newname); 
             if(!oldfile.exists()){
            	 flag=false;
//                 return;//重命名文件不存在
             }
             if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名 
                 System.out.println(newname+"已经存在！"); 
             else{ 
                 oldfile.renameTo(newfile); 
             } 
         }else{
        	 flag=false;
             System.out.println("新文件名和旧文件名相同...");
         }
    	 return flag;
    }
    
    
    
}
