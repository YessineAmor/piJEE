/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.managedbeans.util;

import java.io.File;
import java.io.IOException;
import javax.activation.MimetypesFileTypeMap;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;

/**
 *
 * @author aminos
 */
public class FileDetector {
    public static boolean  isPNG(File file) throws TikaException, IOException {
        TikaConfig tika = new TikaConfig();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.RESOURCE_NAME_KEY, file.toString());
        MediaType mimeType = tika.getDetector().detect(TikaInputStream.get(file), metadata);
        System.out.println(mimeType.getType());
        System.out.println(mimeType.getSubtype());
        return mimeType.getSubtype().contains("png");
    }
    
        public static boolean  isJPG(File file) throws TikaException, IOException {
        TikaConfig tika = new TikaConfig();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.RESOURCE_NAME_KEY, file.toString());
        MediaType mimeType = tika.getDetector().detect(TikaInputStream.get(file), metadata);
        return mimeType.getSubtype().contains("jpeg");
    }
        
             public static boolean  isMP4(File file) throws TikaException, IOException {
        TikaConfig tika = new TikaConfig();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.RESOURCE_NAME_KEY, file.toString());
        MediaType mimeType = tika.getDetector().detect(TikaInputStream.get(file), metadata);
        return mimeType.getSubtype().contains("mp4");
    }
           
    
    public static void main(String[] args) throws TikaException, IOException {
        File f = new File("/home/aminos/Videos/[EgyBest].Eternal.Sunshine.Of.The.Spotless.Mind.2004.BluRay.480p.x264_ar.mp4");
        System.out.println(isMP4(f));
        
    }
}