package com.example.BackSpringBoot.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DossierHomologationRequest {
    private final String appOwner;
    private final String dshReference;
    private final String email;
    private final ClientSite pkcCientSite;
    private final DossierEquivalence pkEquivalence;


}
