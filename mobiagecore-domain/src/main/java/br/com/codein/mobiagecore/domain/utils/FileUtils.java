package br.com.codein.mobiagecore.domain.utils;

import br.com.codein.mobiagecore.domain.exceptions.FileException;

import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.zip.GZIPInputStream;

public class FileUtils {

    public static String createTempFile(InputStream is, String fileName){
        String tempFileName;
        OutputStream out = null;
        try {
            File file = File.createTempFile(fileName, ".tmp");
            out = new FileOutputStream(file);
            byte[] bytes = new byte[1024];

            while(true) {
                int read;
                if((read = is.read(bytes)) == -1) {
                    out.close();
                    tempFileName = file.getAbsolutePath();
                    file.deleteOnExit();
                    break;
                }
                out.write(bytes, 0, read);
            }
        } catch (IOException ex) {
            return null;
        } finally {
            if (out != null)
                try {
                    out.close();
                } catch (IOException e) {
                   throw new FileException(e.getMessage());
                }
        }

        return tempFileName;
    }

    public static String gZipToXml(byte[] conteudo) throws IOException {
        if (conteudo == null || conteudo.length == 0) {
            return "";
        }
        GZIPInputStream gis;
        gis = new GZIPInputStream(new ByteArrayInputStream(conteudo));
        BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
        StringBuffer outStr = new StringBuffer();
        String line;
        while ((line = bf.readLine()) != null) {
            outStr.append(line);
        }

        return outStr.toString();
    }

    public static <T> T xmlToObject(String xml, Class<T> classe) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(classe);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return unmarshaller.unmarshal(new StreamSource(new StringReader(xml)), classe).getValue();
    }


    public static String objectToXml(JAXBContext context, JAXBElement<?> element) {
        StringWriter sw = new StringWriter();
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            marshaller.marshal(element, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return sw.toString();


    }
}
