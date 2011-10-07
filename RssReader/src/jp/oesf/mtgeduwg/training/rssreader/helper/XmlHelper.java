/*
 * Licensed under the Apache License, Version 2.0 (the "License").
 */
package jp.oesf.mtgeduwg.training.rssreader.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import jp.oesf.mtgeduwg.training.rssreader.entity.RssFeedEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import android.util.Log;

/**
 * XMLパースのヘルパークラス。
 */
public class XmlHelper {
    /**
     * RSSを表すXMLが取得可能かどうか判断する。
     * 
     * @param inputStream
     * @return boolean
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public boolean isRssFeed(InputStream inputStream) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputStream);
        NodeList nodes = document.getChildNodes();
        for (int cnt = 0; cnt < nodes.getLength(); cnt++) {
            Node node = nodes.item(cnt);
            if (node == null) {
                continue;
            }
            if ("rss".equals(node.getNodeName())) {
                return true;
            }
        }
        return false;
    }
    /**
     * XMLのInputStreamからRSSフィードをパースする。
     * 
     * @param inputStream
     * @return List<RssFeedEntity>
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public List<RssFeedEntity> parseRssFeeds(InputStream inputStream) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputStream);
        NodeList nodes = document.getChildNodes();
        List<RssFeedEntity> rssFeedEntities = new ArrayList<RssFeedEntity>();
        for (int cnt = 0; cnt < nodes.getLength(); cnt++) {
            Node node = nodes.item(cnt);
            if (node == null) {
                continue;
            }
            if ("rss".equals(node.getNodeName())) {
                NodeList childNodes = node.getChildNodes();
                for (int i = 0; i < childNodes.getLength(); i++) {
                    Node childNode = childNodes.item(i);
                    if (childNode == null) {
                        continue;
                    }
                    if ("channel".equals(childNode.getNodeName())) {
                        NodeList channelNodes = childNode.getChildNodes();
                        String senderName = null;
                        String url = null;
                        for (int j = 0; j < channelNodes.getLength(); j++) {
                            Node channelNode = channelNodes.item(j);
                            if (channelNode == null) {
                                continue;
                            }
                            if ("title".equals(channelNode.getNodeName())) {
                                senderName = channelNode.getFirstChild().getNodeValue();
                            } else if ("link".equals(channelNode.getNodeName())) {
                                url = channelNode.getFirstChild().getNodeValue();
                            } else if ("item".equals(channelNode.getNodeName())) {
                                RssFeedEntity rssFeedEntity = new RssFeedEntity();
                                rssFeedEntity.setSenderName(senderName);
                                rssFeedEntity.setUrl(url);
                                NodeList itemNodes = channelNode.getChildNodes();
                                for (int k = 0; k < itemNodes.getLength(); k++) {
                                    Node itemNode = itemNodes.item(k);
                                    if (itemNode == null) {
                                        continue;
                                    }
                                    if ("description".equals(itemNode.getNodeName())) {
                                        rssFeedEntity.setDescription(itemNode.getFirstChild().getNodeValue());
                                    } else if ("title".equals(itemNode.getNodeName())) {
                                        rssFeedEntity.setTitle(itemNode.getFirstChild().getNodeValue());
                                    } else if ("link".equals(itemNode.getNodeName())) {
                                        NodeList linkChilds = itemNode.getChildNodes();
                                        StringBuilder link = new StringBuilder();
                                        for (int l = 0; l < linkChilds.getLength(); l++) {
                                            link.append(linkChilds.item(l).getNodeValue() == null ? "" : linkChilds.item(l).getNodeValue());
                                        }
                                        rssFeedEntity.setLink(link.toString());
                                    } else if ("guid".equals(itemNode.getNodeName())) {
                                        NodeList guidChilds = itemNode.getChildNodes();
                                        StringBuilder guid = new StringBuilder();
                                        for (int l = 0; l < guidChilds.getLength(); l++) {
                                            guid.append(guidChilds.item(l).getNodeValue() == null ? "" : guidChilds.item(l).getNodeValue());
                                        }
                                        rssFeedEntity.setGuid(guid.toString());
                                    } else if ("pubDate".equals(itemNode.getNodeName())) {
                                        rssFeedEntity.setPublishDate(itemNode.getFirstChild().getNodeValue());
                                    }
                                }
                                rssFeedEntities.add(rssFeedEntity);
                            }
                        }
                    }
                }
            }
            Log.v("XmlHelper", "Succeeded in retrieving the RssFeedEntity list.");
        }
        return rssFeedEntities;
    }
}
