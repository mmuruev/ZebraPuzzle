package zebrapuzzle.save;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.Charset;

public class CXmlResultSaver implements IResultSaver {
    private static final String ENCODING_NAME = "UTF-8";
    private static final Charset ENCODING = Charset.forName(ENCODING_NAME);
    private static final String XSL_FILE_NAME = "zebra_puzzle.xsl";

    private OutputStream resultStream;

    public CXmlResultSaver(OutputStream resultStream) {
        this.resultStream = resultStream;
    }

    @Override
    public boolean save(String[] properties, String[][] result) {
        if (resultStream == null) {
            return false;
        }
        Document document = createDocument();
        document.appendChild(document.createProcessingInstruction("xml-stylesheet",
                "type=\"text/xsl\" href=\"" + XSL_FILE_NAME + "\""));
        Element solutions = document.createElement("solutions");
        document.appendChild(solutions);
        Element solution = document.createElement("solution");
        solutions.appendChild(solution);
        for (int houseIndex = 0; houseIndex < result.length; houseIndex++) {
            Element house = document.createElement("house");
            house.setAttribute("position", Integer.toString(houseIndex + 1));
            for (int propertyIndex = 0; propertyIndex < properties.length; propertyIndex++) {
                house.setAttribute(properties[propertyIndex], result[houseIndex][propertyIndex]);
            }
            solution.appendChild(house);
        }
        saveDocument(document);
        return true;
    }

    private Document createDocument() {

        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Can not initialize result transformation to XML", e);
        }
    }

    private void saveDocument(Document document) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, ENCODING_NAME);
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            Writer writer = new BufferedWriter(new OutputStreamWriter(resultStream, ENCODING));
            transformer.transform(new DOMSource(document), new StreamResult(writer));
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException("Can not initialize result saving to XML", e);
        } catch (TransformerException e) {
            throw new RuntimeException("Can not save result to XML", e);
        }
    }

    @Override
    public void close() {
        if (resultStream != null) {
            try {
                resultStream.close();
            } catch (IOException ignore) {
            } finally {
                resultStream = null;
            }
        }
    }
}
