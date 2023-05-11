package it.maggioli.eldasoft.sil.pagopa.ws;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PagamentiPaginationType", propOrder = {
		"content",
		"totalpage",
		"totalelements",
		"page",
		"pagesize"
})
public class PagamentiPaginationType {
	List<PagamentiOutType> content;
	private Long totalelements;
	private Long totalpage;
	private Long page;
	private Long pagesize;
	/**
	 * @return the content
	 */
	public List<PagamentiOutType> getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(List<PagamentiOutType> content) {
		this.content = content;
	}
	/**
	 * @return the page
	 */
	public Long getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Long page) {
		this.page = page;
	}
	/**
	 * @return the pagesize
	 */
	public Long getPagesize() {
		return pagesize;
	}
	/**
	 * @param pagesize the pagesize to set
	 */
	public void setPagesize(Long pagesize) {
		this.pagesize = pagesize;
	}
	/**
	 * @return the totalpage
	 */
	public Long getTotalpage() {
		return totalpage;
	}
	/**
	 * @param totalpage the totalpage to set
	 */
	public void setTotalpage(Long totalpage) {
		this.totalpage = totalpage;
	}
	/**
	 * @return the totalelements
	 */
	public Long getTotalelements() {
		return totalelements;
	}
	/**
	 * @param totalelements the totalelements to set
	 */
	public void setTotalelements(Long totalelements) {
		this.totalelements = totalelements;
	}
	
	
}
