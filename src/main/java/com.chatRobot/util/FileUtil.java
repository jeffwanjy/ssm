package com.chatRobot.util;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanjiayuan on 2018/4/2.
 */
public class FileUtil {
    List<File> list = new ArrayList<File>();
    // 得到某一目录下的所有文件夹
    public List<File> visitAll(File root)
    {
        File[] dirs = root.listFiles();
        if (dirs != null)
        {
            for (int i = 0; i < dirs.length; i++)
            {
                if (dirs[i].isDirectory())
                {
                    System.out.println("name:" + dirs[i].getPath());
                    list.add(dirs[i]);
                }
                visitAll(dirs[i]);
            }
        }
        return list;
    }
    /**
     * 删除空的文件夹
     * @param list
     */
    public void removeNullFile(List<File> list)
    {
        for (int i = 0; i < list.size(); i++)
        {
            File temp = list.get(i);
            // 是目录且为空
            if (temp.isDirectory() && temp.listFiles().length <= 0)
            {
                temp.delete();
            }
        }
    }
    /**
     * @param args
     */







    private void deleteEmptyDir(String filePath){
        File file=new File(filePath);
        if(file.isDirectory()){
            File[] subFiles=file.listFiles();
            if(subFiles!=null&&subFiles.length>0){
                for(int i=0;i<subFiles.length;i++){
                    deleteEmptyDir(subFiles[i].getPath());
                }
            }else{
                if(file.delete()){
                    System.out.println("删除空文件夹成功！");
                }else{
                    System.out.println("删除空文件夹失败！");
                }
            }
        }
    }






    private static void getFile(String path, int deep) {
        // 获得指定文件对象
        File file = new File(path);
        // 获得该文件夹内的所有文件
        File[] array = file.listFiles();
        for (int i = 0; i < array.length; i++) {
            String name1 = array[i].getName();
            String path1 = array[i].getPath();
            // 获得指定文件对象
            File file2 = new File(path1);
            // 获得该文件夹内的所有文件
            File[] array2 = file2.listFiles();
            for (int j = 0; j < array2.length; j++) {
                String name2 = array2[j].getName();
                //初始文件夹路径
                String path12 = array2[j].getPath();
                if (!name2.endsWith(".jpg")){
                    file.delete();
                }
            }

        }

    }












}
