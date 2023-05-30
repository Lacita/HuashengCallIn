
package com.huasheng.dingding.oaService;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>kmReviewParamterForm complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="kmReviewParamterForm"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="attachmentForms" type="{http://webservice.review.km.kmss.landray.com/}attachmentForm" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="attachmentValues" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="authAreaId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="docContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="docCreator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="docProperty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="docStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="docSubject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fdId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fdKeyword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fdSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fdTemplateId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="flowParam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="formValues" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "kmReviewParamterForm", propOrder = {
    "attachmentForms",
    "attachmentValues",
    "authAreaId",
    "docContent",
    "docCreator",
    "docProperty",
    "docStatus",
    "docSubject",
    "fdId",
    "fdKeyword",
    "fdSource",
    "fdTemplateId",
    "flowParam",
    "formValues"
})
public class KmReviewParamterForm {

    @XmlElement(nillable = true)
    protected List<AttachmentForm> attachmentForms;
    protected String attachmentValues;
    protected String authAreaId;
    protected String docContent;
    protected String docCreator;
    protected String docProperty;
    protected String docStatus;
    protected String docSubject;
    protected String fdId;
    protected String fdKeyword;
    protected String fdSource;
    protected String fdTemplateId;
    protected String flowParam;
    protected String formValues;

    /**
     * Gets the value of the attachmentForms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attachmentForms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttachmentForms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttachmentForm }
     * 
     * 
     */
    public List<AttachmentForm> getAttachmentForms() {
        if (attachmentForms == null) {
            attachmentForms = new ArrayList<AttachmentForm>();
        }
        return this.attachmentForms;
    }

    /**
     * ��ȡattachmentValues���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttachmentValues() {
        return attachmentValues;
    }

    /**
     * ����attachmentValues���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttachmentValues(String value) {
        this.attachmentValues = value;
    }

    /**
     * ��ȡauthAreaId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthAreaId() {
        return authAreaId;
    }

    /**
     * ����authAreaId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthAreaId(String value) {
        this.authAreaId = value;
    }

    /**
     * ��ȡdocContent���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocContent() {
        return docContent;
    }

    /**
     * ����docContent���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocContent(String value) {
        this.docContent = value;
    }

    /**
     * ��ȡdocCreator���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocCreator() {
        return docCreator;
    }

    /**
     * ����docCreator���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocCreator(String value) {
        this.docCreator = value;
    }

    /**
     * ��ȡdocProperty���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocProperty() {
        return docProperty;
    }

    /**
     * ����docProperty���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocProperty(String value) {
        this.docProperty = value;
    }

    /**
     * ��ȡdocStatus���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocStatus() {
        return docStatus;
    }

    /**
     * ����docStatus���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocStatus(String value) {
        this.docStatus = value;
    }

    /**
     * ��ȡdocSubject���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocSubject() {
        return docSubject;
    }

    /**
     * ����docSubject���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocSubject(String value) {
        this.docSubject = value;
    }

    /**
     * ��ȡfdId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFdId() {
        return fdId;
    }

    /**
     * ����fdId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFdId(String value) {
        this.fdId = value;
    }

    /**
     * ��ȡfdKeyword���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFdKeyword() {
        return fdKeyword;
    }

    /**
     * ����fdKeyword���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFdKeyword(String value) {
        this.fdKeyword = value;
    }

    /**
     * ��ȡfdSource���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFdSource() {
        return fdSource;
    }

    /**
     * ����fdSource���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFdSource(String value) {
        this.fdSource = value;
    }

    /**
     * ��ȡfdTemplateId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFdTemplateId() {
        return fdTemplateId;
    }

    /**
     * ����fdTemplateId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFdTemplateId(String value) {
        this.fdTemplateId = value;
    }

    /**
     * ��ȡflowParam���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlowParam() {
        return flowParam;
    }

    /**
     * ����flowParam���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlowParam(String value) {
        this.flowParam = value;
    }

    /**
     * ��ȡformValues���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormValues() {
        return formValues;
    }

    /**
     * ����formValues���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormValues(String value) {
        this.formValues = value;
    }

}
