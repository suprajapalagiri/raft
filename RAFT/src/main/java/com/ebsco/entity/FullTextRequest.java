package com.ebsco.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Entity
@Data
@Table(name = "fulltextrequestinfo")
@NoArgsConstructor
public class FullTextRequest {
	@Id
	@Column(name = "pmid")
	private Integer pmId;

	@Column(name = "journaltitle")
	private String journalTitle;

	private String title;

	private String citation;

	private String instructions;

	private String priority;

	@Column(name = "creationdate")
	private Instant creationDate;

	@Column(name = "requestedby")
	private String requestedBy;

	@Column(name = "requestid")
	private Integer requestID;

	@Column(name = "nesid")
	private String nesId;

	private String status;

	@Column(name = "ftretrievedfrom")
	private String ftRetrievedFrom;

	@Column(name = "fulltexturl")
	private String fullTextURL;

	@Column(name = "fulltextfile")
	private String fullTextFile;

	@Column(name = "supplementmaterial")
	private String supplementMaterial;

	private String notes;

	@Column(name = "isreviewed")
	private Boolean isReviewed;

	@Column(name = "reviewedby")
	private String reviewedBy;

	@Column(name = "reviewedon")
	private Instant reviewedOn;

	@Column(name = "isdeleted")
	private Boolean isDeleted;

	@Column(name = "deletedon")
	private Instant deletedOn;

	@Column(name = "deletedby")
	private String deletedBy;

	@Column(name = "deleterequestfrom")
	private String deleteRequestFrom;

	@Column(name = "isrequested")
	private Boolean isRequested;

	@Column(name = "purchaseorsubscriptiontype")
	private String purchaseOrSubscriptionType;

	private String cost;

	@Column(name = "autorenewalurl")
	private String autoRenewalURL;

	@Column(name = "autorenewal")
	private Boolean autoRenewal;

	@Column(name = "pubmedfturl")
	private String pubMedFTURL;

	@Column(name = "ehostftlink")
	private String ehostFTLink;

	@Column(name = "isslamrecord")
	private Boolean isSlamRecord;

	@Column(name = "costcenter")
	private String costCenter;

	@Column(name = "reopened")
	private Boolean reOpened;

	@Column(name = "firstcreationdate")
	private Instant firstCreationDate;

	@Column(name = "firstrequestedby")
	private String firstRequestedBy;

	@Column(name = "firstreviewedon")
	private Instant firstReviewedOn;

	@Column(name = "firstreviewedby")
	private String firstReviewedBy;

	@Column(name = "apirequest")
	private Boolean apiRequest;
}
