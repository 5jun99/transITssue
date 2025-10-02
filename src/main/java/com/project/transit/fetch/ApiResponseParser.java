package com.project.transit.fetch;

import lombok.Data;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ApiResponseParser {
    @Data
    public static class IssueTypeDto {
        private String accType;     // A01, A02 등
        private String accTypeName; // 교통사고, 차량고장 등
    }
    @Data
    public static class IssueDto {
        private String accId;
        private LocalDate occrDate;
        private LocalTime occrTime;
        private LocalDate expClrDate;
        private LocalTime expClrTime;
        private String accType;
        private String accDtype;
        private String linkId;
        private double grs80tmX;
        private double grs80tmY;
        private String accInfo;
        private String accRoadCode;
    }

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HHmmss");

    public static List<IssueDto> parseIssue(String xml) {
        List<IssueDto> accidents = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));

            NodeList rowList = doc.getElementsByTagName("row");
            for (int i = 0; i < rowList.getLength(); i++) {
                Node row = rowList.item(i);
                if (row.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) row;
                    IssueDto dto = new IssueDto();
                    dto.setAccId(getTagValue(elem, "acc_id"));
                    dto.setOccrDate(parseDate(getTagValue(elem, "occr_date")));
                    dto.setOccrTime(parseTime(getTagValue(elem, "occr_time")));
                    dto.setExpClrDate(parseDate(getTagValue(elem, "exp_clr_date")));
                    dto.setExpClrTime(parseTime(getTagValue(elem, "exp_clr_time")));
                    dto.setAccType(getTagValue(elem, "acc_type"));
                    dto.setAccDtype(getTagValue(elem, "acc_dtype"));
                    dto.setLinkId(getTagValue(elem, "link_id"));
                    dto.setGrs80tmX(parseDouble(getTagValue(elem, "grs80tm_x")));
                    dto.setGrs80tmY(parseDouble(getTagValue(elem, "grs80tm_y")));
                    dto.setAccInfo(getTagValue(elem, "acc_info"));
                    dto.setAccRoadCode(getTagValue(elem, "acc_road_code"));

                    accidents.add(dto);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return accidents;
    }

    public static List<IssueTypeDto> parseIssueTypes(String xml) {
        List<IssueTypeDto> issueTypes = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));

            NodeList rowList = doc.getElementsByTagName("row");
            for (int i = 0; i < rowList.getLength(); i++) {
                Node row = rowList.item(i);
                if (row.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) row;
                    IssueTypeDto dto = new IssueTypeDto();
                    dto.setAccType(getTagValue(elem, "acc_type"));
                    dto.setAccTypeName(getTagValue(elem, "acc_type_nm"));
                    issueTypes.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return issueTypes;
    }

    private static String getTagValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            return node.getTextContent().trim();
        }
        return null;
    }

    private static double parseDouble(String str) {
        if (str == null || str.isEmpty()) return 0.0;
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private static LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) return null;
        return LocalDate.parse(dateStr, DATE_FMT);
    }

    private static LocalTime parseTime(String timeStr) {
        if (timeStr == null || timeStr.isEmpty()) return null;
        if (timeStr.length() == 4) timeStr += "00"; // HHmm -> HHmmss
        return LocalTime.parse(timeStr, TIME_FMT);
    }
}
