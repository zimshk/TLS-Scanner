/**
 * TLS-Scanner - A TLS configuration and analysis tool based on TLS-Attacker.
 *
 * Copyright 2017-2019 Ruhr University Bochum / Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.tlsscanner.serverscanner.rating;

import de.rub.nds.tlsscanner.serverscanner.report.AnalyzedProperty;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "analyzedProperty", "shortName", "shortDescription", "detailedDescription", "testDocumentation",
        "links", "propertyRecommendations" })
public class Recommendation {

    static final String NO_INFORMATION_FOUND = "No detailed information available";

    static final String NO_RECOMMENDATION_FOUND = "No recommendation available";

    private AnalyzedProperty analyzedProperty;

    private String shortName;

    private String shortDescription;

    private String detailedDescription;

    private String testDocumentation;

    private List<String> links;

    private List<PropertyResultRecommendation> propertyRecommendations;

    public Recommendation() {
        propertyRecommendations = new LinkedList<>();
        links = new LinkedList<>();
    }

    public Recommendation(AnalyzedProperty analyzedProperty, List<PropertyResultRecommendation> propertyRecommendations) {
        this.analyzedProperty = analyzedProperty;
        this.propertyRecommendations = propertyRecommendations;
    }

    public Recommendation(AnalyzedProperty analyzedProperty, String shortName) {
        this();
        this.shortName = shortName;
    }

    public Recommendation(AnalyzedProperty analyzedProperty, String shortName, String shortDescription,
            String detailedDescription, String... links) {
        this();
        this.analyzedProperty = analyzedProperty;
        this.shortName = shortName;
        this.shortDescription = shortDescription;
        this.detailedDescription = detailedDescription;
        this.links.addAll(Arrays.asList(links));
    }

    public Recommendation(AnalyzedProperty analyzedProperty, String shortName, String shortDescription,
            PropertyResultRecommendation propertyRecommendation, String... links) {
        this();
        this.analyzedProperty = analyzedProperty;
        this.shortName = shortName;
        this.shortDescription = shortDescription;
        propertyRecommendations.add(propertyRecommendation);
        this.links.addAll(Arrays.asList(links));
    }

    public Recommendation(AnalyzedProperty analyzedProperty, String shortName, String shortDescription,
            String detailedDescription, PropertyResultRecommendation propertyRecommendation, String... links) {
        this();
        this.analyzedProperty = analyzedProperty;
        this.shortName = shortName;
        this.shortDescription = shortDescription;
        this.detailedDescription = detailedDescription;
        propertyRecommendations.add(propertyRecommendation);
        this.links.addAll(Arrays.asList(links));
    }

    public Recommendation(AnalyzedProperty analyzedProperty, String shortName, String shortDescription,
            String detailedDescription, String testDocumentation, List<String> links,
            List<PropertyResultRecommendation> propertyRecommendations) {
        this.analyzedProperty = analyzedProperty;
        this.shortName = shortName;
        this.shortDescription = shortDescription;
        this.detailedDescription = detailedDescription;
        this.testDocumentation = testDocumentation;
        this.links = links;
        this.propertyRecommendations = propertyRecommendations;
    }

    public AnalyzedProperty getAnalyzedProperty() {
        return analyzedProperty;
    }

    public void setAnalyzedProperty(AnalyzedProperty analyzedProperty) {
        this.analyzedProperty = analyzedProperty;
    }

    public String getShortName() {
        if (shortName == null || shortName.equals("")) {
            return analyzedProperty.toString();
        } else {
            return shortName;
        }
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public String getTestDocumentation() {
        return testDocumentation;
    }

    public void setTestDocumentation(String testDocumentation) {
        this.testDocumentation = testDocumentation;
    }

    @XmlElement(name = "resultingRecommendation")
    @XmlElementWrapper(name = "resultingRecommendations")
    public List<PropertyResultRecommendation> getPropertyRecommendations() {
        return propertyRecommendations;
    }

    public void setPropertyRecommendations(List<PropertyResultRecommendation> propertyRecommendations) {
        this.propertyRecommendations = propertyRecommendations;
    }

    public PropertyResultRecommendation getPropertyResultRecommendation(TestResult result) {
        for (PropertyResultRecommendation r : propertyRecommendations) {
            if (r.getResult() == result) {
                return r;
            }
        }
        return new PropertyResultRecommendation(result, NO_INFORMATION_FOUND, NO_RECOMMENDATION_FOUND);
    }

    @XmlElement(name = "link")
    @XmlElementWrapper(name = "links")
    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }
}
