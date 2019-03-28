package com.example.projekt.services;

import com.example.projekt.models.Game;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileUploadService
{
    private final String uploadRootPath = "/upload";

    public List< String > uploadFiles ( MultipartFile[] files, HttpServletRequest request, Game game )
    {
        List< String > filesURL = new ArrayList<> ();

        File uploadRootDir = new File ( request.getServletContext ()
                .getRealPath ( this.uploadRootPath ) );
        if ( !uploadRootDir.exists () )
        {
            uploadRootDir.mkdirs ();
        }

        for ( MultipartFile file : files )
        {
            String name = file.getOriginalFilename ();

            if ( name != null && !name.isBlank () )
            {
                try
                {
                    Date date = new Date ();
                    File serverFile = new File ( uploadRootDir.getAbsolutePath () + File.separator + date.getTime () + name );

                    BufferedOutputStream stream = new BufferedOutputStream ( new FileOutputStream ( serverFile ) );

                    stream.write ( file.getBytes () );
                    stream.close ();
                    filesURL.add ( this.uploadRootPath + "/" + date.getTime () + name );
                }
                catch ( IOException e )
                {
                    e.printStackTrace ();
                }
            }
            else
            {
                filesURL.add ( "" );
            }
        }
        return filesURL;
    }
}
