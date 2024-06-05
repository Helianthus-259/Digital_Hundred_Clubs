package org.example.util;

import static org.example.constants.FileConstants.fileServerDownloadUrl;

public class FileRequestUrlBuilder {
    public static String buildFileRequestUrl(String attachment){
        if(attachment==null) return null;
        else return fileServerDownloadUrl+attachment;
    }
}
