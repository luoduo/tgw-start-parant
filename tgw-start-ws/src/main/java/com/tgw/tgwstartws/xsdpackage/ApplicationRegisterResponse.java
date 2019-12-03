
package com.tgw.tgwstartws.xsdpackage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ApplicationRegisterResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "applicationRegisterResult"
})
@XmlRootElement(name = "ApplicationRegisterResponse", namespace = "http://tempuri.org/")
public class ApplicationRegisterResponse {

    @XmlElement(name = "ApplicationRegisterResult", namespace = "http://tempuri.org/")
    protected String applicationRegisterResult;

    /**
     * 获取applicationRegisterResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationRegisterResult() {
        return applicationRegisterResult;
    }

    /**
     * 设置applicationRegisterResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationRegisterResult(String value) {
        this.applicationRegisterResult = value;
    }

}
