package lt.javainiai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Candidate_rating_results")
public class CandidatesResultsRatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long numberOfVotes;

    @OneToOne
    @JsonBackReference(value = "candidate-ratingResults")
    private CandidateEntity candidate;
    
    @JsonProperty
    private Long getCandidateId() {
        return candidate == null ? null : candidate.getId();
    }
    
    @ManyToOne
    @JsonBackReference(value = "district-ratingResults")
    @JoinColumn(name = "Party_Id")
    private PollingDistrictEntity district;
    
	//Constructor
    public CandidatesResultsRatingEntity(){
        
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(Long numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public CandidateEntity getCandidate() {
        return candidate;
    }

    public void setCandidate(CandidateEntity candidate) {
        this.candidate = candidate;
    }
    
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CandidatesResultsSingleMandateEntity other = (CandidatesResultsSingleMandateEntity) obj;
		if (candidate.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!candidate.getId().equals(other.getId()))
			return false;
		return true;
	}

    @Override
    public String toString() {
        return "CandidatesResultsEntity [id=" + id + ", numberOfVotes=" + numberOfVotes + "]";
    }
}
