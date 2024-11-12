package com.GreenShadow.WebSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity (name = "crop")
public class CropEntity {
    @Id
    private String cropCode;

    private String cropCommonName;
    private String cropScientificName;

    @Column(columnDefinition = "LONGTEXT")
    private String cropImage;

    private String cropCategory;
    private String cropSeason;

    @ManyToOne
    @JoinColumn(name = "fieldCode")
    private FieldEntity field;

    @OneToMany(mappedBy = "crop")
    private List<CropLogDetails> cropLogDetails
            = new ArrayList<>();

}
