package spaceVivarium.core.maps.xml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import spaceVivarium.core.maps.Board;
import spaceVivarium.core.maps.tiles.ATile;
import spaceVivarium.core.maps.tiles.TileClass;
import spaceVivarium.exception.XmlFailureException;

public class XmlReader {

    private static final String BOARD = "board";
    private static final String SIZEX = "sizex";
    private static final String SIZEY = "sizey";
    private static final String BASETYPE = "basetype";
    private static final String AREA = "area";
    private static final String X = "x";
    private static final String Y = "y";
    private static final String HEIGHT = "height";
    private static final String WIDTH = "width";
    private static final String TYPE = "type";

    public static Board xmlToBoard(String adresse) throws XmlFailureException {
        Board board = null;
        Integer x = null, y = null, height = null, width = null;
        Class<? extends ATile> type = null;
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();

        try {
            InputStream in = new FileInputStream(adresse);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // CREATION DU BOARD
                    if (startElement.getName().getLocalPart() == BOARD) {
                        Iterator<Attribute> attributes = startElement
                                .getAttributes();
                        Integer sizex = null, sizey = null;
                        Class<? extends ATile> basetype = null;
                        while (attributes.hasNext()) {
                            Attribute attribute = attributes.next();
                            switch (attribute.getName().toString()) {
                            case SIZEX:
                                sizex = Integer.parseInt(attribute.getValue());
                                break;
                            case SIZEY:
                                sizey = Integer.parseInt(attribute.getValue());
                                break;
                            case BASETYPE:
                                basetype = TileClass.getTileClass(attribute
                                        .getValue());
                                break;
                            }
                        }
                        if (sizex != null && sizey != null && basetype != null) {
                            board = new Board(sizex, sizey, basetype);
                        }
                    } else if (board != null
                            && startElement.getName().getLocalPart() == AREA) {
                        x = null;
                        y = null;
                        height = null;
                        width = null;
                        type = null;
                    } else if (board != null
                            && startElement.getName().getLocalPart() == X) {
                        event = eventReader.nextEvent();
                        x = Integer.parseInt(event.asCharacters().getData());
                    } else if (board != null
                            && startElement.getName().getLocalPart() == Y) {
                        event = eventReader.nextEvent();
                        y = Integer.parseInt(event.asCharacters().getData());
                    } else if (board != null
                            && startElement.getName().getLocalPart() == HEIGHT) {
                        event = eventReader.nextEvent();
                        height = Integer.parseInt(event.asCharacters()
                                .getData());
                    } else if (board != null
                            && startElement.getName().getLocalPart() == WIDTH) {
                        event = eventReader.nextEvent();
                        width = Integer
                                .parseInt(event.asCharacters().getData());
                    } else if (board != null
                            && startElement.getName().getLocalPart() == TYPE) {
                        event = eventReader.nextEvent();
                        type = TileClass.getTileClass(event.asCharacters()
                                .getData());
                    }

                } else if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (board != null
                            && endElement.getName().getLocalPart() == AREA) {
                        board.fillRect(x, y, width, height, type);
                    }
                }
            }

        } catch (Exception e) {
            throw new XmlFailureException(e.getMessage());
        }

        return board;
    }
}
