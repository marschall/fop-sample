package com.github.marschall.fopsample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

public class Main {

  public static void main(String[] args) throws FOPException, TransformerException, IOException {
    FopFactory fopFactory = FopFactory.newInstance();
    TransformerFactory tFactory = TransformerFactory.newInstance();
    
    try (InputStream input = new FileInputStream("/Users/marschall/git/fop-sample/src/main/resources/sample.fo");
        OutputStream output = new FileOutputStream("/Users/marschall/git/fop-sample/sample.pdf")) {
      Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF,   output);
      Transformer transformer = tFactory.newTransformer();
      Source src = new StreamSource(input);
      Result res = new SAXResult(fop.getDefaultHandler());
      transformer.transform(src, res);
      
    }


  }

}
