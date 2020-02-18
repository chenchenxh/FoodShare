package com.foodsharetest.android.util;
/**处理调用相册或者拍照功能的回调
 方法1
 示例：
 Uri imageUri = photoUtils.take_photo_util(PersonInfo.this, "com.foodsharetest.android.fileprovider", "output_image.jpg");

 方法2,3
 arg：this，返回Intent的data
 示例：
 PhotoUtils photoUtils = new PhotoUtils();
 if(resultCode == RESULT_OK){
 //判断手机版本号
 if(Build.VERSION.SDK_INT >= 19){
 photoUtils.handleImageOnKitKat(this, data);
 }else {
 photoUtils.handleImageBeforeKitKat(this, data);
 }
 }
 返回：imagePath
 **/
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.foodsharetest.android.ui.activity.PersonInfo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PhotoUtils {
    //方法1，take photo
    public Uri take_photo_util(Context context, String auth, String filename){
        Uri imageUri;
        File outputImage = new File(context.getExternalCacheDir(), filename);
        //处理重复拍照问题
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //处理版本问题
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(context, auth, outputImage);
        } else {
            imageUri = Uri.fromFile(outputImage);
        }
        return imageUri;
    }

    //方法2，3， from albums
    @TargetApi(19)
    public String handleImageOnKitKat(Context context, Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        //如果是Document类型，则用document id处理，如果是content类型的uri用普通方式处理
        if(DocumentsContract.isDocumentUri(context, uri)){
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1]; //解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(context, contentUri, null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(context, uri,null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }
        return imagePath;
    }
    public String handleImageBeforeKitKat(Context context, Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(context,uri, null);
        return imagePath;
    }
    //私有方法
    private String getImagePath(Context context,Uri uri, String selection){
        String path = null;
        //通过Uri和selection获取真实的图片路径
        Cursor cursor = context.getContentResolver().query(uri,null,selection,null,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    //Bitmap转byte[]
    public byte[] bitmap2byte(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
    //byte[]转Bitmap
    public Bitmap byte2bitmap(byte[] bytes){
        if (bytes != null && bytes.length != 0) {
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } else {
            return null;
        }
    }

    //传入文件名，从assets中读取文件
    public byte[] file2byte(Context context, String filename){
        try {
            InputStream is = context.getAssets().open(filename);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap2byte(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
