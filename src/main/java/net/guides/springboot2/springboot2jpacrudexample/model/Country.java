package net.guides.springboot2.springboot2jpacrudexample.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "countries")

public class Country {
	
	private long id;
    private String countryName;
    private String presidentName;
    private String description;
 
    public Country() {
  
    }
 
    public Country(String countryName, String presidentName, String description) {
         this.countryName = countryName;
         this.presidentName = presidentName;
         this.description = description;
    }
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
 
    @Column(name = "country_name", nullable = false)
    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
 
    @Column(name = "president_name", nullable = false)
    public String getPresidentName() {
        return presidentName;
    }
    public void setPresidentName(String presidentName) {
        this.presidentName = presidentName;
    }
 
    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Country [id=" + id + ", countryName=" + countryName + ", presidentName=" + presidentName + ", description=" + description
       + "]";
    }

}
