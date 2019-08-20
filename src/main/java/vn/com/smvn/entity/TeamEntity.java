package vn.com.smvn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "team")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamEntity {
    
    @Id
    private String id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;
    
    @Column(name = "short_name", nullable = false, length = 45)
    private String shortName;
    
    @Column(name = "crest_url", nullable = false, length = 45)
    private String crestUrl;
}
