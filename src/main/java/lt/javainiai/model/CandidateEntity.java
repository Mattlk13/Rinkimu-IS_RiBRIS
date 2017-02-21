package lt.javainiai.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Candidates")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Length(min = 1, max = 40)
    private String name;

    @NotNull
    @Length(min = 1, max = 40)
    private String surname;

    @NotNull
    @Column(name = "Date_of_Birth")
    private Date birthDate;

    @ManyToOne
    @JsonBackReference(value = "candidate-party")
    @JoinColumn(name = "Party_Id")
    private PartyEntity party;

    @JsonProperty
    public String getPartyName() {
        return party == null ? null : party.getName();
    }
    
    @OneToOne(mappedBy = "candidate", cascade=CascadeType.ALL)
    @JsonManagedReference(value = "candidate-resultsRating")
    private CandidatesResultsRatingEntity candidatesResultsRating;
    
    @OneToOne(mappedBy = "candidate", cascade=CascadeType.ALL)
    @JsonManagedReference(value = "candidate-resultsSingleMandate")
    private CandidatesResultsSingleMandateEntity candidatesResultsSingleMandate;
    
    private Boolean singleMandate;
    
    private Boolean multiMandate;

    private String biography;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "Constituency_Id")
    private ConstituencyEntity constituency;

    // Constructor
    public CandidateEntity() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public PartyEntity getParty() {
        return party;
    }

    public void setParty(PartyEntity party) {
        this.party = party;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public CandidatesResultsRatingEntity getCandidatesResultsRating() {
		return candidatesResultsRating;
	}

	public void setCandidatesResultsRating(CandidatesResultsRatingEntity candidatesResultsRating) {
		this.candidatesResultsRating = candidatesResultsRating;
	}

	public CandidatesResultsSingleMandateEntity getCandidatesResultsSingleMandate() {
		return candidatesResultsSingleMandate;
	}

	public void setCandidatesResultsSingleMandate(CandidatesResultsSingleMandateEntity candidatesResultsSingleMandate) {
		this.candidatesResultsSingleMandate = candidatesResultsSingleMandate;
	}

	public Boolean getMultiMandate() {
		return multiMandate;
	}

	public void setMultiMandate(Boolean multiMandate) {
		this.multiMandate = multiMandate;
	}

	public Boolean getSingleMandate() {
		return singleMandate;
	}

	public void setSingleMandate(Boolean singleMandate) {
		this.singleMandate = singleMandate;
	}

	public ConstituencyEntity getConstituency() {
        return constituency;
    }

    public void setConstituency(ConstituencyEntity constituency) {
        this.constituency = constituency;
    }

    @Override
    public String toString() {
        return "CandidateEntity [id=" + id + ", name=" + name + ", surname=" + surname + ", birthDate=" + birthDate
                + ", party=" + party + ", biography=" + biography + ", constituency=" + constituency + "]";
    }

}
