package pt.fv.services.converters;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.convert.converter.Converter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import pt.fv.services.api.bean.WeatherReport;

/**
 * Conversor de String resultante do objecto GetWeatherResponse do serviço http://www.webservicex.net/globalweather.asmx para a entidade WeatherReport
 *
 * @author fvicente
 */
public class GetWeatherString2WeatherReportConverter implements Converter<String, WeatherReport> {

    /**
     * Log Handler
     */
    private final Log log = LogFactory.getLog(getClass());

    /**
     * {@inheritDoc}
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public WeatherReport convert(String src) {
        WeatherReport target = null;

        if (src != null && src.length() > 0) {
            Document doc;

            try {
                doc = createDomDocument(src);
                target = new WeatherReport();

                target.setLocation(getString(doc, "Location"));
                target.setSkyConditions(getString(doc, "SkyConditions"));
                target.setTemperature(getString(doc, "Temperature"));
                target.setTimestamp(getString(doc, "Time"));
                target.setWind(getString(doc, "Wind"));

            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

        return target;
    }

    /*
     * Cria Documento DOM para permitir acesso facilitado aos nós XML
     */
    private Document createDomDocument(String xml) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder builder;
        InputSource inputSource;
        Document doc;

        builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(xml));
        doc = builder.parse(inputSource);

        return doc;
    }

    private String getString(Document doc, String key) {
        String result = null;

        if (doc != null) {
            NodeList nodes = doc.getElementsByTagName(key);
            if (nodes != null && nodes.getLength() > 0) {
                Node node = nodes.item(0);
                if (node != null) {
                    result = node.getTextContent();
                }
            }
        }

        return result;
    }

}
